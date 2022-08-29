package com.BowesGaming.Levels;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;

import java.util.ArrayList;
import java.util.List;

public class Level_4_LICA extends Level {
    private static final Level_4_LICA Level_4 = new Level_4_LICA();

    public static Level_4_LICA get() {
        return Level_4;
    }

    /**
     * features( e.g. obstacles, platform, background, ... ) for the forth level
     */
    private Level_4_LICA() {
        super();

        //Define level identifier
        levelIdentifier = 3;
        label = "LICA Building";

        //Load textures and load them into the list.
        List<String> obstacleTextures = new ArrayList<>();
        obstacleTextures.add("Obstacles/BlueTulip.png");
        obstacleTextures.add("Obstacles/OrangeTulip.png");
        obstacleTextures.add("Obstacles/RedTulip.png");
        obstacleTextures.add("Obstacles/Sunflower.png");
        obstacleTextures.add("Obstacles/YellowTulip.png");
        List<String> enemyTextures = new ArrayList<>();
        enemyTextures.add("Enemies/BlackGoose.png");
        enemyTextures.add("Enemies/WhiteGoose.png");
        loadTextures(obstacleTextures, enemyTextures);

        //Define gameplay specifics
        viewMoveSpeed = Cnst.baseViewMoveSpeed + 5  + Cnst.difficultyLevel;
        scoreMultiplier = Cnst.baseScoreMultiplier + 0.4 + (Cnst.difficultyLevel * 0.6);

        //Define level start and end points, for obstacle spawning.
        startPoint = Level_3_North_Spine.get().startPoint + Level_3_North_Spine.get().levelLength;
        levelLength = 3000;

        //Platform texture
        platform = StaticHelper.getTextureFromPath("Platforms/Grass.png");

        //Background texture path
        background = StaticHelper.getTextureFromPath("Backgrounds/Level_4_LICA.png");
    }
}
