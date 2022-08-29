package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Events.ModerateButtonClickedEvent;
import com.BowesGaming.Loaders.Builder;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Scenes.Scene;

public class ModerateButton extends Button implements ModerateButtonClickedEvent {
    private int difficultyLevel;
    private DifficultyButton difficultyButton;
    public ModerateButton(int positionX, int positionY, DifficultyButton d) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMediumYellow.png"), "Moderate", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 40, positionX, positionY);
        this.difficultyButton = d;
    }

    private void clicked() {
        difficultyLevel = 1;
        difficultyButton.setDiffString("M");
    }

    @Override
    public void onModerateButtonClicked(Scene scene) {
        if (clickable) {
            difficultyLevel = difficultyButton.getDifficultyLevel();
            clicked();
            difficultyButton.setDifficultyLevel(difficultyLevel);
            Builder.setProperty("assets/Properties/Properties.properties", "difficultyLevel", Integer.toString(difficultyLevel));
        }
    }
}
