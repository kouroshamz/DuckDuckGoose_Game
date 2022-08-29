package com.BowesGaming.Components.HudElements;

import com.BowesGaming.Components.Buttons.Button;
import com.BowesGaming.Events.ScoreChangeEvent;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Events.ViewMoveEvent;
import com.BowesGaming.Loaders.Builder;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.Collections;
import java.util.List;

public class HighScore extends Button implements ScreenUpdateEvent, ViewMoveEvent, ScoreChangeEvent {
    public static int score;

    public HighScore(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/highScoreBox.png"), "placeholder", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 20, positionX, positionY);
        List<Integer> highScores = Builder.getHighScores();
        highScores.sort(Collections.reverseOrder());
        changeText(String.format("High Score: %d", highScores.get(0)));
    }

    @Override
    public void onViewMove(int viewMoveAmount) {
        move(viewMoveAmount, 0);
        getText().move(viewMoveAmount, 0);
    }

    @Override
    public void onScoreChanged(int amount) {
        score+=amount;
    }
}
