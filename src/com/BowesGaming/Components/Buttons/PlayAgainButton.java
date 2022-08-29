package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Components.Player;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Levels.Level_1_Library;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Events.StartButtonClickedEvent;
import com.BowesGaming.Scenes.GameScene;
import com.BowesGaming.Scenes.Scene;

public class PlayAgainButton extends Button implements StartButtonClickedEvent, ScreenUpdateEvent {
    public PlayAgainButton(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "Play Again?", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 30, positionX, positionY);
    }

    @Override
    public void onStartButtonClicked(Scene scene) {
        if (clickable) {
            GameScene gc = new GameScene();
            gc.currentLevel = Level_1_Library.get();
            Engine.get().currentScene.music.pause();
            Engine.get().setScene(new GameScene());
            Player.get().currentLives = 5;
            Player.get().setPosition(Engine.get().getView().getCenter().x - Engine.get().getView().getSize().x + 10, Cnst.floorPosition);
            clickable = false;
        }
    }
}
