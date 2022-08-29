package com.BowesGaming.Levels;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.ArrayList;
import java.util.List;

public class Level_5_Chaplaincy extends Level {
    private static final Level_5_Chaplaincy Level_5 = new Level_5_Chaplaincy();

    public static Level_5_Chaplaincy get() {
        return Level_5;
    }

    /**
     * features( e.g. obstacles, platform, background, ... ) for the fifth level
     */
    private Level_5_Chaplaincy() {
        super();

        //Define level identifier
        levelIdentifier = 4;
        label = "Chaplaincy";

        //Load textures and load them into the list.
        List<String> obstacleTextures = new ArrayList<>();
        obstacleTextures.add("Obstacles/SilverCandle.png");
        obstacleTextures.add("Obstacles/GoldCandle.png");
        obstacleTextures.add("Obstacles/StoneCross.png");
        obstacleTextures.add("Obstacles/WoodCross.png");
        List<String> enemyTextures = new ArrayList<>();
        enemyTextures.add("Enemies/BlackGoose.png");
        enemyTextures.add("Enemies/WhiteGoose.png");
        loadTextures(obstacleTextures, enemyTextures);

        //Define gameplay specifics
        viewMoveSpeed = Cnst.baseViewMoveSpeed + 8 + Cnst.difficultyLevel;
        scoreMultiplier = Cnst.baseScoreMultiplier + 0.6 + (Cnst.difficultyLevel * 0.6);

        //Define level start and end points, for obstacle spawning.
        startPoint = Level_4_LICA.get().startPoint + Level_4_LICA.get().levelLength;
        levelLength = 3000;

        //Platform texture
        platform = StaticHelper.getTextureFromPath("Platforms/CarpetFloor.png");

        //Background texture path
        background = StaticHelper.getTextureFromPath("Backgrounds/Level_5_Chaplaincy.png");
    }
}
