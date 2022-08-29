package com.BowesGaming.Levels;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.ArrayList;
import java.util.List;

public class Level_3_North_Spine extends Level {
    private static final Level_3_North_Spine Level_3 = new Level_3_North_Spine();

    public static Level_3_North_Spine get() {
        return Level_3;
    }

    /**
     * features( e.g. obstacles, platform, background, ... ) for the third level
     */
    private Level_3_North_Spine() {
        super();

        //Define level identifier
        levelIdentifier = 2;
        label = "North Spine";
        isEndless = true;
        bossActive = true;

        //Load textures and load them into the list.
        List<String> obstacleTextures = new ArrayList<>();
        obstacleTextures.add("Obstacles/Bin_3.png");
        List<String> enemyTextures = new ArrayList<>();
        enemyTextures.add("Enemies/BlackGoose.png");
        enemyTextures.add("Enemies/WhiteGoose.png");
        loadTextures(obstacleTextures, enemyTextures);

        //Define gameplay specifics
        viewMoveSpeed = Cnst.baseViewMoveSpeed + 4 + Cnst.difficultyLevel;
        scoreMultiplier = Cnst.baseScoreMultiplier + 0.3  + (Cnst.difficultyLevel * 0.6);

        //Define level start and end points, for obstacle spawning.
        startPoint = Level_2_Alex_Square.get().startPoint + Level_2_Alex_Square.get().levelLength;
        levelLength = 10000;

        //Platform texture
        platform = StaticHelper.getTextureFromPath("Platforms/StoneFloor.png");

        //Background texture path
        background = StaticHelper.getTextureFromPath("Backgrounds/Level_3_North_Spine.png");
    }
}
