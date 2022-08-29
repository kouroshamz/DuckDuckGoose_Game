package com.BowesGaming.Components;

import com.BowesGaming.Components.Buttons.Sign;
import com.BowesGaming.Loaders.StaticHelper;

public class LevelIndicator extends Sign {
    public LevelIndicator(int signTextSize, int signPositionX, int signPositionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/wideLargeButton.png"), "", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), signTextSize, signPositionX, signPositionY);
    }

    public void update(int level, String label) {
        String signText = String.format("  Level %d:\n%s",level+1, label);
        changeText(signText);
        getText().move(getText().getLocalBounds().width/3, 0);
    }
}
