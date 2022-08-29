package com.BowesGaming.Components.HudElements;

import com.BowesGaming.Components.Buttons.Button;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ScoreChangeEvent;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Events.ViewMoveEvent;

public class ScoreCounter extends Button implements ScreenUpdateEvent, ViewMoveEvent, ScoreChangeEvent {
    public static int score;

    public ScoreCounter(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/highScoreBox.png"), "placeholder", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 25, positionX, positionY);
        score = 0;
    }

    @Override
    public void onViewMove(int viewMoveAmount) {
        move(viewMoveAmount, 0);
        getText().move(viewMoveAmount, 0);
        changeText(String.format("Score: %d", score));
        score += 1;
        Cnst.thisRoundScore = score;
    }

    @Override
    public void onScoreChanged(int amount) {
        score+=amount;
    }
}
