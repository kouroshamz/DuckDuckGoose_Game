package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Components.Entity;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Events.StartButtonClickedEvent;
import com.BowesGaming.Scenes.Scene;

public class PlayButton extends Button implements StartButtonClickedEvent, ScreenUpdateEvent {
    public PlayButton(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMediumGreen.png"), "Play", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 30, positionX, positionY);
    }

    @Override
    public void onStartButtonClicked(Scene scene) {
        if (clickable) {
            Engine.get().setScene(scene);
            if (Engine.get().currentScene.gameScene && !Engine.get().currentScene.instructionSceneShownThisSession) {
                Engine.get().clear();
                Entity background = new Entity();
                background.setTexture( StaticHelper.getTextureFromPath("Backgrounds/Instruction.png") );
                Engine.get().draw(background);
                Engine.get().display();
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Engine.get().currentScene.instructionSceneShownThisSession = true;
            }
            clickable = false;
        }
    }
}
