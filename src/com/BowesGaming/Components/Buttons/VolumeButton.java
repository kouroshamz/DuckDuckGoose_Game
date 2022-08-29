package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Events.MinusButtonClickedEvent;
import com.BowesGaming.Events.PlusButtonClickedEvent;
import com.BowesGaming.Loaders.Builder;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Events.VolumeButtonClickedEvent;
import com.BowesGaming.Scenes.Scene;

public class VolumeButton  extends Button implements ScreenUpdateEvent {
    private int volumeLevel;

    public VolumeButton(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "Volume", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 40, positionX, positionY);
        this.volumeLevel = Builder.getInt("volumeLevel");
        changeText("Volume: " + volumeLevel);
    }

    public int getVolumeLevel(){
        return volumeLevel;
    }
    public void setVolumeLevel(int volume)
    {
        volumeLevel = volume;
        changeText("Volume: " + volumeLevel);
    }


}
