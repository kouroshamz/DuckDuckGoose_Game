package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Loaders.Builder;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.DifficultyButtonClickedEvent;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Scenes.Scene;

public class DifficultyButton extends Button implements ScreenUpdateEvent {
    private int difficultyLevel;
    private String diffString;
    public DifficultyButton(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "Difficulty", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 31, positionX, positionY);
        this.difficultyLevel = Builder.getInt("difficultyLevel");
        if(difficultyLevel == 0){changeText("Difficulty: " + "E");}
        if(difficultyLevel == 1){changeText("Difficulty: " + "M");}
        if(difficultyLevel == 2){changeText("Difficulty: " + "H");}
    }

    public int getDifficultyLevel(){
        return difficultyLevel;
    }
    public void setDifficultyLevel(int difficult) {
        difficultyLevel = difficult;
    }
    public void setDiffString(String s){
        diffString = s;
        changeText("Difficulty: " + s);
    }
}
