package com.BowesGaming.Levels;

import org.jsfml.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

/**
 * level class is the skeleton for the features of each level
 */
public abstract class Level {
    public String label = "";
    public int levelIdentifier;
    public List<String> obstacleTextures = new ArrayList<>();
    public List<String> enemyTextures = new ArrayList<>();
    public Texture background;
    public Texture platform;
    public int viewMoveSpeed;
    public double scoreMultiplier;
    public int startPoint;
    public int levelLength;
    public Boolean bossActive = false;
    public boolean isEndless = false;

    public Level() {
    }

    /**
     * loads the enemy and obstacle textures in each level
     * @param obstacleTextureList
     * @param enemyTextureList
     */
    public void loadTextures(List<String> obstacleTextureList, List<String> enemyTextureList) {
        obstacleTextures = obstacleTextureList;
        enemyTextures = enemyTextureList;
    }
}
