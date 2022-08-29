package com.BowesGaming.Scenes;

import com.BowesGaming.Components.*;
import com.BowesGaming.Components.HudElements.Heartmeter;
import com.BowesGaming.Components.HudElements.HighScore;
import com.BowesGaming.Components.HudElements.Icon;
import com.BowesGaming.Components.HudElements.ScoreCounter;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.EntityGenerator;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.FriendlyFiredEvent;
import com.BowesGaming.Events.GameOverEvent;
import com.BowesGaming.Events.LevelChangeEvent;
import com.BowesGaming.Events.SceneAnimationEvent;
import com.BowesGaming.Levels.*;
import org.jsfml.graphics.*;
import org.jsfml.system.Clock;

public class GameScene extends Scene implements SceneAnimationEvent, LevelChangeEvent, FriendlyFiredEvent, GameOverEvent {
    private Clock featherFiringCooldown = new Clock();

    /**
     * this class is used to set the features of the game scene such as background, music and sound effects, ...
     * also adds the levels and entities and some buttons
     */
    public GameScene() {
        super();

        //Define background, platform, player skin etc.
        drawPlayer = true;
        drawPlatform = true;
        gameScene = true;

        //Add the levels to the gamescene
        levelList.add(Level_1_Library.get());
        levelList.add(Level_2_Alex_Square.get());
        levelList.add(Level_3_North_Spine.get());
        levelList.add(Level_4_LICA.get());
        levelList.add(Level_5_Chaplaincy.get());
        levelList.add(Level_6_Sports_Fields.get());
        levelList.add(Level_7_Sports_Centre.get());
        levelList.add(Level_8_Underpass.get());
        levelList.add(Level_9_Cartmel_College.get());
        levelList.add(Level_10_Infolab.get());

        currentLevel = levelList.get(0);
        setBackground(currentLevel.background);
        setPlatformTexture(currentLevel.platform);

        music = new Audio("acceleration2.wav");

        //Define each component of the scene (Int positionX, Int position Y).
        ScoreCounter scorecounter = new ScoreCounter(Cnst.resX / 6, Cnst.resY / 9);
        HighScore highScore = new HighScore(Cnst.resX * 2 / 6, Cnst.resY / 9);
        Heartmeter heartmeter = new Heartmeter((Cnst.resX / 2) - (3 * Cnst.entityScale), (Cnst.resY / 9) - (3 * Cnst.entityScale));

        levelSwapper();

        //Add component to the scenes button list for drawing.
//        addEntity(obstacles);
        addEntity(scorecounter);
        addEntity(highScore);
        addEntity(Player.get());
        addEntity(Platform.get());
        addPlayerCollidable(Player.get());
        for (Icon heart : heartmeter.getHearts()) {
            addEntity(heart);
        }
        addFeatherFiredListener(this);

        //Specific action listeners
        addAction(Player.get());
        addSceneAnimationListener(this);
        addAnimationListener(Player.get());
        addAnimationListener(heartmeter);
        addLivesLostListener(Player.get());
        addPlayerCrouchListener(Player.get());
        addPlayerJumpListener(Player.get());
        addPlayerStandListener(Player.get());
        addViewMoveListeners(Player.get());
        addViewMoveListeners(Platform.get());
        addViewMoveListeners(background);
        addLevelChangeListener(this);
        addViewMoveListeners(scorecounter);
        addScoreChangedListener(scorecounter);
        addViewMoveListeners(heartmeter);
        addLivesGainedListener(heartmeter);
        addLivesLostListener(heartmeter);
        addViewMoveListeners(highScore);
        addViewMoveListeners(Engine.get());
    }

    /**
     * this method is used to get the current level that is running
     * @return current level
     */
    public Level get() {
        return currentLevel;
    }

    @Override
    public void onSceneAnimate(float timeElapsed) {
    }

    /**
     * swap is used for swapping between the levels
     */
    public void levelSwapper() {
        System.out.printf("View Move Speed: %d Score Multiplier: %f\n", currentLevel.viewMoveSpeed, currentLevel.scoreMultiplier);
        if (currentLevel.isEndless) {
            EntityGenerator.get().applyFilter(1);
            Player.get().infiniteFeather = true;
        } else {
            EntityGenerator.get().applyFilter(0);
            Player.get().infiniteFeather = false;
        }
        if (currentLevel.levelIdentifier > 0) {
            levelTransition();
        }
        for (Entity entity : entityList) {
            if (entity.getEntityType() == entity.getEntityType().Enemy || entity.getEntityType() == entity.getEntityType().Obstacle || entity.getEntityType() == entity.getEntityType().EnemyProjectile) {
                entity.setDrawn(false);
                entity.setCollidable(false);
            }
        }
        Platform.get().setPlatformTexture(currentLevel.platform);
        setBackground(currentLevel.background);
        viewMoveSpeed = currentLevel.viewMoveSpeed;
        currentLevelDifficulty = currentLevel.scoreMultiplier;
        EntityGenerator.configureLevel(
                currentLevel.obstacleTextures,
                currentLevel.enemyTextures
        );
        if (currentLevel.bossActive) {
            if (Engine.get().currentScene.gameScene) {
                music.pause();
                bossMusic.play();
            }
            Boss.setHealthRemaining(100);
            addEntity(Boss.getBody());
            addEntity(Boss.getHead());
            addActive(Boss.getHead());
            addActive(Boss.getBody());
            addAction(Boss.get());
            addViewMoveListeners(Boss.get());
            addAnimationListener(Boss.get());
            addScreenUpdateListeners(Boss.getHead());
            addScreenUpdateListeners(Boss.getBody());
            Boss.get().setPosition(Player.get().getPosition().x + (Cnst.resX * 3/5), 300);

        } else {
            bossMusic.pause();
            removeViewMoveListener(Boss.get());
            removeAnimationListener(Boss.get());
            removeActiveEntity(Boss.getBody());
            removeActiveEntity(Boss.getHead());
            removeEntity(Boss.getBody());
            removeEntity(Boss.getHead());
            removeAction(Boss.get());
        }
        Boss.setIsActive(currentLevel.bossActive);
    }

    /**
     * method used for the transition scene between levels
     * this method affects music, threads, background color for a short amount of time
     */
    private void levelTransition() {
        Engine.get().clear(Color.BLACK);
        Engine.get().display();
        if (Engine.get().currentScene.music != null) {
            Engine.get().currentScene.music.pause();
        }
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        Engine.get().clear(Color.BLACK);
        Text text = StaticHelper.makeCentreText(String.format("Level %d - %s", currentLevel.levelIdentifier+1, currentLevel.label), 70);
        Audio transitionSound = new Audio("Select1.wav");
        transitionSound.play();
        Engine.get().draw(text);
        Engine.get().display();
        try{
            Thread.sleep(2500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        if (Engine.get().currentScene.music != null) {
            Engine.get().currentScene.music.play();
        }
    }

    /**
     * method used to recognize which level the player is on and shows the related scene after
     */
    @Override
    public void onLevelChange() {
        int currentPosition = levelList.indexOf(currentLevel);
        int next = currentPosition + 1;
        if (next == 10) {
            int score = ScoreCounter.score;
            GameOverScene gameOverScene = new GameOverScene(score);
            Engine.get().setScene(gameOverScene);
        } else {
            currentLevel = levelList.get(next);
            levelSwapper();
        }
    }

    /**
     * this method is for when the player that has collected a feather power up presses the shoot button
     */
    @Override
    public void onFeatherFired() {
        if (Player.get().featherEquipped || Player.get().infiniteFeather) {
            if (featherFiringCooldown.getElapsedTime().asSeconds() > 0.5) {
                FriendlyProjectile feather = new FriendlyProjectile();
                addPlayerCollidable(feather);
                addEntity(feather);
                addScreenUpdateListeners(feather);
                addViewMoveListeners(feather);
                featherFiringCooldown.restart();
            }
        }
    }

    /**
     * method is called when player loses all his lives
     */
    @Override
    public void onGameOver() {
        Engine.get().isGameOver = true;
    }
}
