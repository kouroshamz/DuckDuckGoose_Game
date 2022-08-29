package com.BowesGaming.Scenes;

import com.BowesGaming.Components.Audio;
import com.BowesGaming.Components.Buttons.ExitButton;
import com.BowesGaming.Components.Buttons.LeaderboardButton;
import com.BowesGaming.Components.Buttons.PlayButton;
import com.BowesGaming.Components.Buttons.SettingsButton;
import com.BowesGaming.Components.Buttons.Sign;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ViewMoveEvent;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

public class MainMenuScene extends Scene implements ViewMoveEvent {
    private Clock animationClock = new Clock();
    private Sign title = new Sign( StaticHelper.getTextureFromPath("UserInterface/OutlinedTitle.png"), "", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 0, Cnst.resX * 6/11, Cnst.resY * 2/7);
    private Sign icon1 = new Sign(StaticHelper.getTextureFromPath("UserInterface/DuckCostume1.png"), "", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 0, Cnst.resX * 1/9, Cnst.resY * 6/12);
    private Sign icon2 = new Sign(StaticHelper.getTextureFromPath("UserInterface/BlackGoose.png"), "", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 0, Cnst.resX * 8/9, Cnst.resY * 6/12);

    /**
     * this class is used to set the features of the main menu such as background, music and sound effects, ...
     * also adds the buttons and credits
     */
    public MainMenuScene() {
        super();

        //Define background, platform, player skin etc.
        setBackground( StaticHelper.getTextureFromPath("Backgrounds/BlurredMainMenu.png") );
        music = new Audio("MenuMusicV2.wav");
        //Define each component of the scene (Int positionX, Int position Y).
        PlayButton playButton = new PlayButton(Cnst.resX /2, Cnst.resY * 3 / 6);
        LeaderboardButton leaderboardButton = new LeaderboardButton(Cnst.resX /2, Cnst.resY * 4/6);
        SettingsButton settingsButton = new SettingsButton(Cnst.resX * 2/7, Cnst.resY * 4/6);
        ExitButton exitButton = new ExitButton(Cnst.resX * 5/7, Cnst.resY * 4/6);

        Sign creditStefan = new Sign( StaticHelper.getTextureFromPath("UserInterface/Stefan.png"), "", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 0, Cnst.resX * 1/6, Cnst.resY * 11/12);
        Sign creditBen = new Sign( StaticHelper.getTextureFromPath("UserInterface/Ben.png"), "", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 0, Cnst.resX * 2/6, Cnst.resY * 11/12);
        Sign creditKourosh = new Sign( StaticHelper.getTextureFromPath("UserInterface/Kourosh.png"), "", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 0, Cnst.resX * 3/6, Cnst.resY * 11/12);
        Sign creditHuiying = new Sign( StaticHelper.getTextureFromPath("UserInterface/Huiying.png"), "", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 0, Cnst.resX * 4/6, Cnst.resY * 11/12);
        Sign creditKatie = new Sign( StaticHelper.getTextureFromPath("UserInterface/Katie.png"), "", StaticHelper.getFontFromPath("EndlessBossBattle.ttf"), 0, Cnst.resX * 5/6, Cnst.resY * 11/12);

        creditStefan.setScale(0.9f, 0.9f);
        creditBen.setScale(0.9f, 0.9f);
        creditKourosh.setScale(0.9f, 0.9f);
        creditHuiying.setScale(0.9f, 0.9f);
        creditKatie.setScale(0.9f, 0.9f);

        //Add component to the scenes button list for drawing.
        addEntity(exitButton);
        addEntity(playButton);
        addEntity(leaderboardButton);
        addEntity(settingsButton);
        addEntity(title);
        addEntity(creditStefan);
        addEntity(creditBen);
        addEntity(creditKourosh);
        addEntity(creditHuiying);
        addEntity(creditKatie);
        addEntity(icon1);
        addEntity(icon2);

        //Add component-specific listeners for each component.

        //Specific action listeners
        addExitButtonClickedListener(exitButton);
        addStartButtonClickedListener(playButton);
        addSettingsButtonClickedListener(settingsButton);
        addHighScoreButtonClickedListener(leaderboardButton);
        addViewMoveListeners(this);
        title.setOrigin(new Vector2f( title.getLocalBounds().width/2, title.getLocalBounds().height/2 ));
        icon1.setOrigin(new Vector2f( icon1.getLocalBounds().width/2, icon1.getLocalBounds().height/2 ));
        icon2.setOrigin(new Vector2f( icon2.getLocalBounds().width/2, icon2.getLocalBounds().height/2 ));
    }

    /**
     * used for the sign that shows the name of the game and calculates its movements
     * @param viewMoveAmount
     */
    public void onViewMove(int viewMoveAmount) {
        if (animationClock.getElapsedTime().asSeconds() < Math.PI * 4) {
            float val = (float) Math.sin(animationClock.getElapsedTime().asSeconds());
            float scale = (2 * val) + Cnst.entityScale;
            icon1.setScale(scale, scale);
            icon2.setScale(scale, scale);
            title.setScale(scale, scale);
        } else {
            animationClock.restart();
        }
    }
}
