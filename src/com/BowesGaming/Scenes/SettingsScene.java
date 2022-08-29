package com.BowesGaming.Scenes;

import com.BowesGaming.Components.Buttons.*;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

public class SettingsScene extends Scene {
    static VolumeButton volume;
    static DifficultyButton difficulty;
    static SkinDisplay skinDisplay;

    /**
     * this class is used to set the features of the settings menu such as background, music and sound effects, ...
     * also includes all the buttons and adds them to the screen
     */
    public SettingsScene() {
        super();

        setBackground(StaticHelper.getTextureFromPath("Backgrounds/MainMenuBackground.png"));

        BackButton back = new BackButton( Cnst.resX / 6 , Cnst.resY * 1/6 ); // NEW
        volume = new VolumeButton(Cnst.resX / 4, Cnst.resY * 4/6); // NEW
        difficulty = new DifficultyButton(Cnst.resX / 4, Cnst.resY * 5/6); // NEW
        skinDisplay = new SkinDisplay(Cnst.resX /4, Cnst.resY * 3/6);
        ControlsButton controls = new ControlsButton(Cnst.resX / 4, Cnst.resY * 3/6); // NEW


        //AboutButton about = new AboutButton(Constant.screenResolutionX / 2, Constant.screenResolutionY * 2/6);
        PlusButton plus = new PlusButton(Cnst.resX *2/3, Cnst.resY *4/6, volume);
        MinusButton minus = new MinusButton(Cnst.resX / 2, Cnst.resY *4/6, volume);
        SkinUpButton skinUp = new SkinUpButton(Cnst.resX * 2/3, Cnst.resY *3/6, skinDisplay);
        SkinDownButton skinDown = new SkinDownButton(Cnst.resX / 2, Cnst.resY *3/6, skinDisplay);
        EasyButton easy = new EasyButton(Cnst.resX / 2, Cnst.resY *5/6, difficulty);
        ModerateButton moderate = new ModerateButton(Cnst.resX *2/3, Cnst.resY *5/6, difficulty);
        HardButton hard = new HardButton(Cnst.resX *5/6, Cnst.resY *5/6, difficulty);

        addEntity(back);
        addEntity(volume);
        addEntity(controls);
        addEntity(difficulty);
        addEntity(plus);
        addEntity(minus);
        addEntity(easy);
        addEntity(moderate);
        addEntity(hard);
        addEntity(skinDisplay);
        addEntity(skinDown);
        addEntity(skinUp);

        addBackButtonClickedListener(back);
        addControlsButtonClickedListener(controls);
        addPlusButtonClickedListener(plus);
        addMinusButtonClickedListener(minus);
        addEasyButtonClickedListener(easy);
        addModerateButtonClickedListener(moderate);
        addHardButtonClickedListener(hard);
        addDuckSkinDownListener(skinDown);
        addDuckSkinUpListener(skinUp);
    }
}
