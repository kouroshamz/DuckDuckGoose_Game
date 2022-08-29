package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Events.PlusButtonClickedEvent;
import com.BowesGaming.Loaders.Builder;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Scenes.Scene;


public class PlusButton extends Button implements PlusButtonClickedEvent {
    private int volumeLevel;
    private VolumeButton volumeButton;
    public PlusButton(int positionX, int positionY, VolumeButton v) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "+", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 50, positionX, positionY);
        this.volumeButton = v;
    }

    private void clicked()
    {
        volumeLevel += 10;
        if(volumeLevel >100){
            volumeLevel = 100;
        }
    }


    @Override
    public void onPlusButtonClicked(Scene scene) {
        if (clickable) {
            volumeLevel = volumeButton.getVolumeLevel();
            clicked();
            volumeButton.setVolumeLevel(volumeLevel);
            Builder.setProperty("assets/Properties/Properties.properties", "volumeLevel", Integer.toString(volumeLevel));
            clickable = false;
        }
    }
}