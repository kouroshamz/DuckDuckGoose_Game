package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Components.Player;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

public class SkinDisplay extends Button implements ScreenUpdateEvent {
    private int currentSkinID;

    public SkinDisplay(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "Skin: ", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 40, positionX, positionY);
        this.currentSkinID = Cnst.skinID;
        setCurrentSkin(currentSkinID);
    }

    public int getCurrentSkin(){
        return currentSkinID;
    }
    public void setCurrentSkin(int skinID)
    {
        currentSkinID = skinID;
        String text = Player.get().availableSkins.get(currentSkinID);
        String skinFile = text.split("/")[1];
        changeText("Skin: " + skinFile.split("\\.")[0]);
    }


}
