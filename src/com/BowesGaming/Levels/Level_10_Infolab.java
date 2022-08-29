package com.BowesGaming.Levels;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.ArrayList;
import java.util.List;

public class Level_10_Infolab extends Level {
    private static final Level_10_Infolab Level_10 = new Level_10_Infolab();

    public static Level_10_Infolab get() {
        return Level_10;
    }

    /**
     * features( e.g. obstacles, platform, background, ... ) for the tenth level
     */
    private Level_10_Infolab() {
        super();

        //Define level identifier
        levelIdentifier = 9;
        label = "Infolab21";
        isEndless = true;
        bossActive = true;

        //Load textures and load them into the list.
        List<String> obstacleTextures = new ArrayList<>();
        obstacleTextures.add("Obstacles/Banana.png");
        List<String> enemyTextures = new ArrayList<>();
        enemyTextures.add("Enemies/BlackGoose.png");
        enemyTextures.add("Enemies/WhiteGoose.png");
        loadTextures(obstacleTextures, enemyTextures);

        //Define gameplay specifics
        viewMoveSpeed = Cnst.baseViewMoveSpeed + 20 + Cnst.difficultyLevel;
        scoreMultiplier = Cnst.baseScoreMultiplier + 2.1 + (Cnst.difficultyLevel * 0.6);

        //Define level start and end points, for obstacle spawning.
        startPoint = Level_9_Cartmel_College.get().startPoint + Level_9_Cartmel_College.get().levelLength;
        levelLength = 10000;

        //Platform texture
        platform = StaticHelper.getTextureFromPath("Platforms/StoneFloor.png");

        //Background texture path
        background = StaticHelper.getTextureFromPath("Backgrounds/Level_10_Infolab.png");
    }
}
