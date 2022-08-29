package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ExitButtonClickedEvent;
import com.BowesGaming.Events.ScreenUpdateEvent;

public class ExitButton extends Button implements ExitButtonClickedEvent, ScreenUpdateEvent {
    public ExitButton(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "Exit", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 30, positionX, positionY);
    }

    @Override
    public void onExitButtonClicked() {
        if (clickable) {
            Engine.get().currentScene.music.pause();
            Engine.get().close();
            clickable = false;
        }
    }

}
