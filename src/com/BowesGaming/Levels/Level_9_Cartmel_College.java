package com.BowesGaming.Levels;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.ArrayList;
import java.util.List;

public class Level_9_Cartmel_College extends Level {
    private static final Level_9_Cartmel_College Level_9 = new Level_9_Cartmel_College();

    public static Level_9_Cartmel_College get() {
        return Level_9;
    }

    /**
     * features( e.g. obstacles, platform, background, ... ) for the ninth level
     */
    private Level_9_Cartmel_College() {
        super();

        //Define level identifier
        levelIdentifier = 8;
        label = "Cartmel College";

        //Load textures and load them into the list.
        List<String> obstacleTextures = new ArrayList<>();
        obstacleTextures.add("Obstacles/NoDucks.png");
        obstacleTextures.add("Obstacles/NoDucksBig.png");
        obstacleTextures.add("Obstacles/Bin_2.png");
        List<String> enemyTextures = new ArrayList<>();
        enemyTextures.add("Enemies/BlackGoose.png");
        enemyTextures.add("Enemies/WhiteGoose.png");
        loadTextures(obstacleTextures, enemyTextures);

        //Define gameplay specifics
        viewMoveSpeed = Cnst.baseViewMoveSpeed + 16 + Cnst.difficultyLevel;
        scoreMultiplier = Cnst.baseScoreMultiplier + 1.7+ (Cnst.difficultyLevel * 0.6);

        //Define level start and end points, for obstacle spawning.
        startPoint = Level_8_Underpass.get().startPoint + Level_8_Underpass.get().levelLength;
        levelLength = 3300;

        //Platform texture
        platform = StaticHelper.getTextureFromPath("Platforms/Road.png");

        //Background texture path
        background = StaticHelper.getTextureFromPath("Backgrounds/Level_9_Cartmel.png");
    }
}
