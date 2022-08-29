package com.BowesGaming.Components;

import com.BowesGaming.Components.Buttons.Button;
import com.BowesGaming.Loaders.Builder;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.Collections;
import java.util.List;

public class Leaderboard extends Button {
    private List<Integer> highScores;

    /**
     * this class is for the leader board shown in the game gets the score and calculates where to place it in the list
     * @param positionX
     * @param positionY
     */
    public Leaderboard(int score, int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonLarge.png"), "", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 50, positionX, positionY);
        showLeaderboard(score);
    }

    public void showLeaderboard(int score) {
        highScores = Builder.getHighScores();

        Collections.sort(highScores);
        if (score > highScores.get(0)) {
            highScores.set(0, score);
        }
        Collections.sort(highScores, Collections.reverseOrder());

        String data = " High Scores: \n";
        for (int i = 0; i < highScores.size(); i++) {
            data += String.format("\t%d).   %d\n", i+1, highScores.get(i));
        }

        staticTextChange(data);
        getText().setPosition((Cnst.resX /2)-getText().getLocalBounds().width/2, Cnst.resY * 1/3);
        Builder.setProperty("assets/Properties/Properties.properties", "highScore1", String.format("%d", score));
        Builder.setProperty("assets/Properties/Properties.properties", "highScore2", String.format("%d", highScores.get(1)));
        Builder.setProperty("assets/Properties/Properties.properties", "highScore3", String.format("%d", highScores.get(2)));
        Builder.setProperty("assets/Properties/Properties.properties", "highScore4", String.format("%d", highScores.get(3)));
        Builder.setProperty("assets/Properties/Properties.properties", "highScore5", String.format("%d", highScores.get(4)));

    }
}