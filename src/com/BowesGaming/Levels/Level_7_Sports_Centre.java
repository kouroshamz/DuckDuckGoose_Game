package com.BowesGaming.Levels;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.ArrayList;
import java.util.List;

public class Level_7_Sports_Centre extends Level {
    private static final Level_7_Sports_Centre Level_7 = new Level_7_Sports_Centre();

    public static Level_7_Sports_Centre get() {
        return Level_7;
    }

    /**
     * features( e.g. obstacles, platform, background, ... ) for the seventh level
     */
    private Level_7_Sports_Centre() {
        super();

        //Define level identifier
        levelIdentifier = 6;
        label = "Sports Centre";

        //Load textures and load them into the list.
        List<String> obstacleTextures = new ArrayList<>();
        obstacleTextures.add("Obstacles/Kettlebell.png");
        obstacleTextures.add("Obstacles/WaterBottle.png");
        obstacleTextures.add("Obstacles/Weight.png");
        List<String> enemyTextures = new ArrayList<>();
        enemyTextures.add("Enemies/BlackGoose.png");
        enemyTextures.add("Enemies/WhiteGoose.png");
        loadTextures(obstacleTextures, enemyTextures);

        //Define gameplay specifics
        viewMoveSpeed = Cnst.baseViewMoveSpeed + 12 + Cnst.difficultyLevel;
        scoreMultiplier = Cnst.baseScoreMultiplier + 1.1 + (Cnst.difficultyLevel * 0.6);

        //Define level start and end points, for obstacle spawning.
        startPoint = Level_6_Sports_Fields.get().startPoint + Level_6_Sports_Fields.get().levelLength;
        levelLength = 3000;

        //Platform texture
        platform = StaticHelper.getTextureFromPath("Platforms/MetalFloor.png");

        //Background texture path
        background = StaticHelper.getTextureFromPath("Backgrounds/Level_7_Sports_Centre.png");
    }
}
