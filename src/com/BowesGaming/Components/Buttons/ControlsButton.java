package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ControlsButtonClickedEvent;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Scenes.Scene;

public class ControlsButton extends Button implements ControlsButtonClickedEvent, ScreenUpdateEvent {
    public ControlsButton(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "Controls", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 50, positionX, positionY);
    }

    @Override
    public void onControlsButtonClicked(Scene scene) {
        if (clickable) {
            Engine.get().setScene(scene);
//            System.out.println("Controls button pressed");
            clickable = false;
        }
    }
}
