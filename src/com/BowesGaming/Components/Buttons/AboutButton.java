package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.AboutButtonClickedEvent;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Scenes.Scene;

public class AboutButton extends Button implements AboutButtonClickedEvent, ScreenUpdateEvent {

    public AboutButton(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "About", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 50, positionX, positionY);
    }

    @Override
    public void onAboutButtonClicked(Scene scene) {
        if (clickable){
            Engine.get().setScene(scene);
//            System.out.println("About button pressed");
            clickable = false;
        }
    }
}
