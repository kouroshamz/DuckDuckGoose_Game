package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Components.Player;
import com.BowesGaming.Events.DuckSkinDownEvent;
import com.BowesGaming.Loaders.Builder;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;


public class SkinDownButton extends Button implements DuckSkinDownEvent {
    private SkinDisplay skinDisplay;
    public SkinDownButton(int positionX, int positionY, SkinDisplay v) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "<-", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 50, positionX, positionY);
        this.skinDisplay = v;
    }

    private void clicked()
    {
        int currentSkin = skinDisplay.getCurrentSkin();
        if (currentSkin - 1 == -1) {
            currentSkin = Player.get().availableSkins.size()-1;
        } else {
            currentSkin -= 1;
        }
        Builder.setProperty("assets/Properties/Properties.properties", "skinID", Integer.toString(currentSkin));
        Cnst.skinID = currentSkin;
        Player.get().changeSkin();
        skinDisplay.setCurrentSkin(currentSkin);
    }

    @Override
    public void onDuckSkinDown() {
        if (clickable) {
            clicked();
        }
    }
}