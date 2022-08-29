package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Events.SettingsButtonClickedEvent;
import com.BowesGaming.Scenes.Scene;

public class SettingsButton extends Button implements ScreenUpdateEvent, SettingsButtonClickedEvent {
    public SettingsButton(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "Settings", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 30, positionX, positionY);
    }

    @Override
    public void onSettingsButtonClicked(Scene scene) {
        if (clickable) {
            Engine.get().setScene(scene);
//            System.out.println("settings button pressed");
            clickable = false;
        }
    }
}
