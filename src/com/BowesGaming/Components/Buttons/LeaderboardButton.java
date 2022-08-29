package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.HighScoreButtonClickedEvent;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Scenes.LeaderboardScene;

public class LeaderboardButton extends Button implements ScreenUpdateEvent, HighScoreButtonClickedEvent {
    public LeaderboardButton(int positionX, int positionY) {
        super(StaticHelper.getTextureFromPath("UserInterface/buttonMedium.png"), "Leaderboard", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 30, positionX, positionY);
    }

    @Override
    public void onHighScoreButtonClicked() {
        if (clickable) {
            Engine.get().setScene(new LeaderboardScene(0));
            clickable = false;
        }
    }
}
