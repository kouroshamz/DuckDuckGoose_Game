package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Events.EasyButtonClickedEvent;
import com.BowesGaming.Loaders.Builder;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Scenes.Scene;

public class EasyButton extends Button implements EasyButtonClickedEvent {
    private int difficultyLevel;
    private DifficultyButton difficultyButton;
    public EasyButton(int positionX, int positionY, DifficultyButton d) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMediumGreen.png"), "Easy", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 40, positionX, positionY);
        this.difficultyButton = d;
    }

    private void clicked() {
        difficultyLevel = 0;
        difficultyButton.setDiffString("E");
    }

    @Override
    public void onEasyButtonClicked(Scene scene) {
        if (clickable) {
            difficultyLevel = difficultyButton.getDifficultyLevel();
            clicked();
            difficultyButton.setDifficultyLevel(difficultyLevel);
            Builder.setProperty("assets/Properties/Properties.properties", "difficultyLevel", Integer.toString(difficultyLevel));
        }
    }
}
