package com.BowesGaming.Scenes;

import com.BowesGaming.Components.Audio;
import com.BowesGaming.Components.Buttons.ExitButton;
import com.BowesGaming.Components.Buttons.PlayAgainButton;
import com.BowesGaming.Components.Leaderboard;
import com.BowesGaming.Components.Buttons.Sign;
import com.BowesGaming.Events.ViewMoveEvent;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;
import org.jsfml.system.Clock;

public class GameOverScene extends Scene implements ViewMoveEvent {
    private Clock animationClock = new Clock();
    private Sign title = new Sign( StaticHelper.getTextureFromPath("UserInterface/GameOver.png"), "", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 0, Cnst.resX * 7/13, Cnst.resY * 1/7);

    /**
     * this class is used to set the features of the game over menu such as background, music and sound effects, ...
     * also includes the buttons and adds them to the screen
     */
    public GameOverScene(int finalScore) {
        super();

        gameOverScene = true;

        //Define background, platform, player skin etc.
        setBackground(StaticHelper.getTextureFromPath("Backgrounds/MainMenuBackground.png") );

        music = new Audio("GameOverV1.wav");

        //Define each component of the scene (Int positionX, Int position Y).
        PlayAgainButton playButton = new PlayAgainButton(Cnst.resX * 1/3, Cnst.resY * 7 / 8);
        ExitButton exitButton = new ExitButton(Cnst.resX * 2/3, Cnst.resY * 7 / 8);
        Leaderboard leaderboard = new Leaderboard(finalScore, Cnst.resX /2, Cnst.resY /2);

        //Add component to the scenes button list for drawing.
        addEntity(exitButton);
        addEntity(playButton);
        addEntity(leaderboard);
        addEntity(title);

        //Add component-specific listeners for each component.

        //Specific action listeners
        addViewMoveListeners(this);
        addExitButtonClickedListener(exitButton);
        addStartButtonClickedListener(playButton);

        title.setOrigin(title.getLocalBounds().width/2, title.getLocalBounds().height/2);
//        System.out.println(finalScore);
    }

    /**
     * used for the sign that shows the "Game Over" and calculates its movements
     * @param viewMoveAmount
     */
    @Override
    public void onViewMove(int viewMoveAmount) {
        if (animationClock.getElapsedTime().asSeconds() < Math.PI * 4) {
            float val = (float) Math.sin(animationClock.getElapsedTime().asSeconds());
            float scale = (2 * val) + Cnst.entityScale;
            title.setScale(scale, scale);
        } else {
            animationClock.restart();
        }
    }
}
