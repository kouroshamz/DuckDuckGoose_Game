package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Events.MinusButtonClickedEvent;
import com.BowesGaming.Loaders.Builder;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Scenes.Scene;


public class MinusButton extends Button implements MinusButtonClickedEvent {
    private int volumeLevel;
    private VolumeButton volumeButton;
    public MinusButton(int positionX, int positionY, VolumeButton v) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "-", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 50, positionX, positionY);
        this.volumeButton = v;
    }

    private void clicked()
    {
        volumeLevel -= 10;
        if(volumeLevel <0){
            volumeLevel = 0;
        }
    }

    @Override
    public void onMinusButtonClicked(Scene scene) {
        if (clickable) {
            volumeLevel = volumeButton.getVolumeLevel();
            clicked();
            volumeButton.setVolumeLevel(volumeLevel);
            Builder.setProperty("assets/Properties/Properties.properties", "volumeLevel", Integer.toString(volumeLevel));
            clickable = false;
        }
    }
}
