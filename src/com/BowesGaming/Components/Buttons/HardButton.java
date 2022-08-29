package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Events.EasyButtonClickedEvent;
import com.BowesGaming.Events.HardButtonClickedEvent;
import com.BowesGaming.Loaders.Builder;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Scenes.Scene;

public class HardButton extends Button implements HardButtonClickedEvent {
    private int difficultyLevel;
    private DifficultyButton difficultyButton;
    public HardButton(int positionX, int positionY, DifficultyButton d) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMediumRed.png"), "Hard", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 40, positionX, positionY);
        this.difficultyButton = d;
    }

    private void clicked() {
        difficultyLevel = 2;
        difficultyButton.setDiffString("H");
    }


    @Override
    public void onHardButtonClicked(Scene scene) {
        if (clickable) {
            difficultyLevel = difficultyButton.getDifficultyLevel();
            clicked();
            difficultyButton.setDifficultyLevel(difficultyLevel);
            Builder.setProperty("assets/Properties/Properties.properties", "difficultyLevel", Integer.toString(difficultyLevel));
        }
    }
}
