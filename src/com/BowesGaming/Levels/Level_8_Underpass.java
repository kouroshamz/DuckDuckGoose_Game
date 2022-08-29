package com.BowesGaming.Levels;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.ArrayList;
import java.util.List;

public class Level_8_Underpass extends Level {
    private static final Level_8_Underpass Level_8 = new Level_8_Underpass();

    public static Level_8_Underpass get() {
        return Level_8;
    }

    /**
     * features( e.g. obstacles, platform, background, ... ) for the eighth level
     */
    private Level_8_Underpass() {
        super();

        //Define level identifier
        levelIdentifier = 7;
        label = "Underpass";

        //Load textures and load them into the list.
        List<String> obstacleTextures = new ArrayList<>();
        obstacleTextures.add("Obstacles/TyreUp.png");
        obstacleTextures.add("Obstacles/TyreDown.png");
        obstacleTextures.add("Obstacles/TrafficCone.png");
        List<String> enemyTextures = new ArrayList<>();
        enemyTextures.add("Enemies/BlackGoose.png");
        enemyTextures.add("Enemies/WhiteGoose.png");
        loadTextures(obstacleTextures, enemyTextures);

        //Define gameplay specifics
        viewMoveSpeed = Cnst.baseViewMoveSpeed + 13 + Cnst.difficultyLevel;
        scoreMultiplier = Cnst.baseScoreMultiplier + 1.3 + (Cnst.difficultyLevel * 0.6);

        //Define level start and end points, for obstacle spawning.
        startPoint = Level_7_Sports_Centre.get().startPoint + Level_7_Sports_Centre.get().levelLength;
        levelLength = 3000;

        //Platform texture
        platform = StaticHelper.getTextureFromPath("Platforms/Road.png");

        //Background texture path
        background = StaticHelper.getTextureFromPath("Backgrounds/Level_6_Underpass.png");
    }
}
