package com.BowesGaming.Levels;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.ArrayList;
import java.util.List;

public class Level_6_Sports_Fields extends Level {
    private static final Level_6_Sports_Fields Level_6 = new Level_6_Sports_Fields();

    public static Level_6_Sports_Fields get() {
        return Level_6;
    }

    /**
     * features( e.g. obstacles, platform, background, ... ) for the sixth level
     */
    private Level_6_Sports_Fields() {
        super();

        //Define level identifier
        levelIdentifier = 5;
        label = "Sports Fields";
        isEndless = true;
        bossActive = true;

        //Load textures and load them into the list.
        List<String> obstacleTextures = new ArrayList<>();
        obstacleTextures.add("Obstacles/RedTulip.png");
        List<String> enemyTextures = new ArrayList<>();
        enemyTextures.add("Enemies/BlackGoose.png");
        enemyTextures.add("Enemies/WhiteGoose.png");
        loadTextures(obstacleTextures, enemyTextures);

        //Define gameplay specifics
        viewMoveSpeed = Cnst.baseViewMoveSpeed + 10 + Cnst.difficultyLevel;
        scoreMultiplier = Cnst.baseScoreMultiplier + 0.9 + (Cnst.difficultyLevel * 0.6);

        //Define level start and end points, for obstacle spawning.
        startPoint = Level_5_Chaplaincy.get().startPoint + Level_5_Chaplaincy.get().levelLength;
        levelLength = 7000;

        //Platform texture
        platform = StaticHelper.getTextureFromPath("Platforms/Grass.png");

        //Background texture path
        background = StaticHelper.getTextureFromPath("Backgrounds/Level_8_Sports_Fields.png");
    }
}
