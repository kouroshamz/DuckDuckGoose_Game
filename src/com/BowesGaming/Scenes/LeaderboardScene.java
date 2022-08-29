package com.BowesGaming.Scenes;

import com.BowesGaming.Components.Buttons.BackButton;
import com.BowesGaming.Components.Leaderboard;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

public class LeaderboardScene extends Scene {

    /**
     * this class is used to set the features of the leaderboard menu such as background, music and sound effects, ...
     * also includes the buttons and adds them to the screen
     */
    public LeaderboardScene(int finalScore) {
        super();

        //Define background, platform, player skin etc.
        setBackground(StaticHelper.getTextureFromPath("Backgrounds/MainMenuBackground.png") );

        //Define each component of the scene (Int positionX, Int position Y).
        Leaderboard leaderboard = new Leaderboard(0, Cnst.resX /2, Cnst.resY /2);
        BackButton backButton = new BackButton(Cnst.resX / 6 , Cnst.resY * 1/6);

        //Add component to the scenes button list for drawing.
        addEntity(leaderboard);
        addEntity(backButton);

        addBackButtonClickedListener(backButton);
    }
}
