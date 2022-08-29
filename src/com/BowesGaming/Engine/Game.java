package com.BowesGaming.Engine;

import com.BowesGaming.Components.Entity;
import com.BowesGaming.Components.HudElements.ScoreCounter;
import com.BowesGaming.Components.Platform;
import com.BowesGaming.Components.Player;
import com.BowesGaming.Events.RefreshEvent;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.EntityGenerator;
import com.BowesGaming.Scenes.GameOverScene;
import com.BowesGaming.Scenes.GameScene;
import com.BowesGaming.Scenes.MainMenuScene;
import com.BowesGaming.Scenes.SettingsScene;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.View;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        Cnst.getConstants();

        Engine engine = Engine.get();
        engine.create();
        Player player = Player.get();
        Platform platform = Platform.get();
        player.create();
        platform.create();

        MainMenuScene menuScene = new MainMenuScene();
        GameScene gameScene = new GameScene();
        SettingsScene settingsScene = new SettingsScene();

        engine.setScene(menuScene);

        Clock viewMovementClock = new Clock();
        Clock activeEntityClock = new Clock();
        Clock entitySpawningClock = new Clock();
        Clock screenUpdateClock = new Clock();
        Clock bossActionClock = new Clock();
        Clock refreshClock = new Clock();
        Clock popupClock = new Clock();

        int viewLowerBound = (int) (engine.getView().getCenter().x - (engine.getView().getSize().x / 2) - 100);

        while (engine.isOpen()) {
            System.gc();
            engine.clear(new Color(Color.WHITE, 5));
            if (refreshClock.getElapsedTime().asSeconds() > Cnst.MOVEPERIOD) {
                engine.refresh();
                for (RefreshEvent actionToRemove : engine.actionsToRemove) {
                    engine.currentScene.removeAction(actionToRemove);
                }
                for (RefreshEvent actionToAdd : engine.actionsToAdd) {
                    engine.currentScene.addAction(actionToAdd);
                }
                engine.actionsToAdd.clear();
                engine.actionsToRemove.clear();
                refreshClock.restart();
            }

            for (Event inputEvent : engine.pollEvents()) {
                controlHandler(engine, menuScene, gameScene, settingsScene, inputEvent);
            }

            if (entitySpawningClock.getElapsedTime().asSeconds() > 0.1 && engine.currentScene.gameScene) {
                EntityGenerator.spawn();
                if (player.levelPosition > Cnst.levelRunoffZone && !EntityGenerator.active) {
                    EntityGenerator.start();
                } else if (player.levelPosition > gameScene.currentLevel.levelLength - Cnst.levelRunoffZone && EntityGenerator.active) {
                    EntityGenerator.stop();
                }
                entitySpawningClock.restart();
            }

            if (viewMovementClock.getElapsedTime().asSeconds() > Cnst.MOVEPERIOD) {
                int moveAmount = engine.currentScene.getViewMoveSpeed();
                engine.viewMove(moveAmount);
                viewLowerBound += moveAmount;
                viewMovementClock.restart();

                if (player.levelPosition >= gameScene.currentLevel.levelLength && !gameScene.currentLevel.isEndless) {
                    levelChangeReset(engine, player, viewMovementClock, activeEntityClock, entitySpawningClock, screenUpdateClock, bossActionClock, refreshClock);
                }
            }
            if (bossActionClock.getElapsedTime().asSeconds() > 5) {
                engine.bossAction();
                bossActionClock.restart();
            }
            if (activeEntityClock.getElapsedTime().asSeconds() > 0.3) {
                stagnantEntityRemover(engine, activeEntityClock, viewLowerBound);
                int score = ScoreCounter.score;
                if (player.currentLives < 1 && !engine.currentScene.gameOverScene) {
                    engine.setScene(new GameOverScene(score));
                }
            }
            if (screenUpdateClock.getElapsedTime().asSeconds() > Cnst.MOVEPERIOD) {
                collision(engine);
                engine.clear();
                engine.animationFired();
                engine.screenUpdate();
                engine.display();
                if (engine.currentScene.forceSceneSwap) {
                    levelChangeReset(engine, player, viewMovementClock, activeEntityClock, entitySpawningClock, screenUpdateClock, bossActionClock, refreshClock);
                    engine.currentScene.forceSceneSwap = false;
                }
                screenUpdateClock.restart();
            }
        }
    }

    private static void levelChangeReset(Engine engine, Player player, Clock viewMovementClock, Clock activeEntityClock, Clock entitySpawningClock, Clock screenUpdateClock, Clock bossActionClock, Clock refreshClock) {
        engine.levelChanged();
        viewMovementClock.restart();
        activeEntityClock.restart();
        entitySpawningClock.restart();
        screenUpdateClock.restart();
        bossActionClock.restart();
        refreshClock.restart();

        player.levelPosition = 0;

        float EngineViewX = Engine.get().getView().getCenter().x;
        float EngineViewY = Engine.get().getView().getCenter().y;
        Vector2f EngineViewSize = Engine.get().getView().getSize();

        Engine.get().setView( new View( new Vector2f(EngineViewX, EngineViewY), EngineViewSize));
        Player.get().setPosition(EngineViewX - EngineViewSize.x/2 + 10, Cnst.floorPosition);
    }

    private static void controlHandler(Engine engine, MainMenuScene menuScene, GameScene gameScene, SettingsScene settingsScene, Event inputEvent) {
        if (inputEvent.type.equals(Type.MOUSE_MOVED)) {
            engine.buttonSelected(inputEvent.asMouseEvent().position);
        }
        if (inputEvent.type.equals(Type.MOUSE_BUTTON_PRESSED)) {
            engine.exitButtonClicked();
            engine.startButtonClicked(gameScene);
            engine.settingsButtonClicked(settingsScene);
            engine.aboutButtonClicked(menuScene);
            engine.volumeButtonClicked(menuScene);
            engine.plusButtonClicked(menuScene);
            engine.minusButtonClicked(menuScene);
            engine.difficultyButtonClicked(menuScene);
            engine.easyButtonClicked(menuScene);
            engine.moderateButtonClicked(menuScene);
            engine.hardButtonClicked(menuScene);
            engine.backButtonClicked(menuScene);
            engine.controlsButtonClicked(menuScene);
            engine.skinDownButtonClicked(menuScene);
            engine.skinUpButtonClicked(menuScene);
            engine.highScoreButtonClicked();
        }
        if (inputEvent.type.equals(Type.KEY_PRESSED)) {

            switch (inputEvent.asKeyEvent().key) {
                case LSHIFT:
                case RSHIFT:
                    engine.playerCrouch();
                    engine.animationStateUpdate();
                    break;
                case SPACE:
                    engine.playerJump();
                    engine.animationStateUpdate();
                    break;
                case ESCAPE:
                    engine.setScene(menuScene);
                    break;
                case RETURN:
                    engine.featherFired();
                    break;
                case S:
                    engine.currentScene.viewMoveSpeed = 1000;
                    break;
            }
        } else if (inputEvent.type.equals(Type.KEY_RELEASED)) {
            switch (inputEvent.asKeyEvent().key) {
                case LSHIFT:
                case RSHIFT:
                    engine.playerStand();
                    engine.animationStateUpdate();
                    break;
                case SPACE:
                    engine.animationStateUpdate();
                    break;
                case S:
                    engine.currentScene.viewMoveSpeed = 20;
                    break;
            }
        }
    }

    private static void stagnantEntityRemover(Engine engine, Clock activeEntityClock, int viewLowerBound) {
        List<Entity> entitiesToRemove = new ArrayList<>();
        for (Entity entity : engine.getEntities()) {
            if (entity.getPosition().x < viewLowerBound - Cnst.resX / 2) {
                switch (entity.getEntityType()) {
                    case BossBody:
                    case BossHead:
                    case Boss:
                        break;
                    default:
                        entitiesToRemove.add(entity);
                        break;
                }
            } else if (entity.getPosition().x > viewLowerBound + Cnst.resX) {
                switch (entity.getEntityType()) {
                    case FriendlyProjectile:
                        entitiesToRemove.add(entity);
                        break;
                }
            }
        }
        for (Entity entity : entitiesToRemove) {
            entity.delete();
        }
        activeEntityClock.restart();
    }

    private static void collision(Engine engine) {
        for (Entity friendlyEntity : engine.getPlayerCollidables()) {
            friendlyEntity.setCollisionBoxPosition();
            Vector2f fColBoxSize = friendlyEntity.getCollisionBox().getSize();
            Vector2f fColBoxBounds = friendlyEntity.getCollisionBox().getPosition();
            FloatRect fColBoxDetector = new FloatRect(fColBoxBounds, fColBoxSize);

            for (int i = 0; i < engine.getActiveEntityListSize(); i++) {
                Entity entity = engine.getActive(i);
                entity.setCollisionBoxPosition();
                Vector2f eColBoxSize = entity.getCollisionBox().getSize();
                Vector2f eColBoxBounds = entity.getCollisionBox().getPosition();
                FloatRect eColBoxDetector = new FloatRect(eColBoxBounds, eColBoxSize);

                if (fColBoxDetector.intersection(eColBoxDetector) != null) {
                    entity.onCollision(friendlyEntity);
                }
            }
        }
    }
}
