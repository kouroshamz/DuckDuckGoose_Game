package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.BackButtonClickedEvent;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Scenes.Scene;

public class BackButton extends Button implements BackButtonClickedEvent, ScreenUpdateEvent {
    public BackButton(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "Back", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 50, positionX, positionY);
    }

    @Override
    public void onBackButtonClicked(Scene scene) {
        if (clickable) {
            Engine.get().setScene(scene);
//            System.out.println("Back button pressed");
            clickable = false;
        }
    }
}
