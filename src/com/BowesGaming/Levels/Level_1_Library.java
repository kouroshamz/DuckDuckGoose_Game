package com.BowesGaming.Levels;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.ArrayList;
import java.util.List;

public class Level_1_Library extends Level {
    private static final Level_1_Library LEVEL_1 = new Level_1_Library();

    public static Level_1_Library get() {
        return LEVEL_1;
    }

    /**
     * features( e.g. obstacles, platform, background, ... ) for the first level
     */
    private Level_1_Library() {
        super();

        //Define level identifier
        levelIdentifier = 0;
        label = "University Library";

        //Load textures and load them into the list.
        List<String> obstacleTextures = new ArrayList<>();
        obstacleTextures.add("Obstacles/Books.png");
        obstacleTextures.add("Obstacles/Chair.png");
        obstacleTextures.add("Obstacles/WetFloorSign.png");
        List<String> enemyTextures = new ArrayList<>();
        enemyTextures.add("Enemies/BlackGoose.png");
        enemyTextures.add("Enemies/WhiteGoose.png");
        loadTextures(obstacleTextures, enemyTextures);

        //Define gameplay specifics
        viewMoveSpeed = Cnst.baseViewMoveSpeed + Cnst.difficultyLevel;
        scoreMultiplier = Cnst.baseScoreMultiplier + (Cnst.difficultyLevel * 0.6);

        //Define level start and end points, for obstacle spawning.
        startPoint = 0;
        levelLength =  3000;

        //Platform texture
        platform = StaticHelper.getTextureFromPath("Platforms/CarpetFloor.png");

        //Background texture path
        background = StaticHelper.getTextureFromPath("Backgrounds/Level_1_Library.png");
    }
}
