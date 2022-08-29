package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Components.Player;
import com.BowesGaming.Events.DuckSkinUpEvent;
import com.BowesGaming.Loaders.Builder;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;


public class SkinUpButton extends Button implements DuckSkinUpEvent {
    private SkinDisplay skinDisplay;
    public SkinUpButton(int positionX, int positionY, SkinDisplay v) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "->", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 50, positionX, positionY);
        this.skinDisplay = v;
    }

    private void clicked()
    {
        int currentSkin = skinDisplay.getCurrentSkin();
        if (currentSkin + 1 == Player.get().availableSkins.size()) {
            currentSkin = 0;
        } else {
            currentSkin += 1;
        }
        Builder.setProperty("assets/Properties/Properties.properties", "skinID", Integer.toString(currentSkin));
        Cnst.skinID = currentSkin;
        Player.get().changeSkin();
        skinDisplay.setCurrentSkin(currentSkin);
    }

    @Override
    public void onDuckSkinUp() {
        if (clickable) {
            clicked();
        }
    }
}