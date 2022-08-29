package com.BowesGaming.Levels;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.ArrayList;
import java.util.List;

public class Level_2_Alex_Square extends Level {
    private static final Level_2_Alex_Square Level_2 = new Level_2_Alex_Square();

    public static Level_2_Alex_Square get() {
        return Level_2;
    }

    /**
     * features( e.g. obstacles, platform, background, ... ) for the second level
     */
    private Level_2_Alex_Square() {
        super();

        //Define level identifier
        levelIdentifier = 1;
        label = "Alexandra Square";

        //Load textures and load them into the list.
        List<String> obstacleTextures = new ArrayList<>();
        obstacleTextures.add("Obstacles/Banana.png");
        obstacleTextures.add("Obstacles/Bin_1.png");
        obstacleTextures.add("Obstacles/Bin_2.png");
        List<String> enemyTextures = new ArrayList<>();
        enemyTextures.add("Enemies/BlackGoose.png");
        enemyTextures.add("Enemies/WhiteGoose.png");
        loadTextures(obstacleTextures, enemyTextures);

        //Define gameplay specifics
        viewMoveSpeed = Cnst.baseViewMoveSpeed + 2 + Cnst.difficultyLevel;
        scoreMultiplier = Cnst.baseScoreMultiplier + 0.1 + (Cnst.difficultyLevel * 0.6);

        //Define level start and end points, for obstacle spawning.
        startPoint = Level_1_Library.get().startPoint + Level_1_Library.get().levelLength;
        levelLength = 3000;

        //Platform texture
        platform = StaticHelper.getTextureFromPath("Platforms/StoneFloor.png");

        //Background texture path
        background = StaticHelper.getTextureFromPath("Backgrounds/Level_2_Alex_Square.png");
    }
}
