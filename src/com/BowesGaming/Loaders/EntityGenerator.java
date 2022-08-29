package com.BowesGaming.Loaders;

import com.BowesGaming.Components.*;
import com.BowesGaming.Engine.Engine;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;

import java.util.ArrayList;
import java.util.List;

public class EntityGenerator {
    private static EntityGenerator INSTANCE = new EntityGenerator();
    private static Clock spawnClock = new Clock();

    private static List<Texture> activeObstacleTextures = new ArrayList<>();
    private static List<String> obstacleTextureNames = new ArrayList<>();

    private static List<Texture> activeEnemyTextures = new ArrayList<>();
    private static List<String> enemyTextureNames = new ArrayList<>();

    public static int filter = 0;

    public static boolean active = false;

    private EntityGenerator() {
    }

    public static EntityGenerator get() {
        return INSTANCE;
    }

    public static void configureLevel(List<String> obstacleTextureLocation, List<String> enemyTextureLocation) {
        activeObstacleTextures.clear();
        for (String s : obstacleTextureLocation) {
            activeObstacleTextures.add(StaticHelper.getTextureFromPath(s));
        }
        obstacleTextureNames = obstacleTextureLocation;
        activeEnemyTextures.clear();
        for (String s : enemyTextureLocation) {
            activeEnemyTextures.add(StaticHelper.getTextureFromPath(s));
        }
        enemyTextureNames = enemyTextureLocation;
    }

    public static void start() {
        active = true;
        spawnClock.restart();
    }

    public static void stop() {
        active = false;
    }

    public void applyFilter(int filterToApply) {
        filter = filterToApply;
    }

    public static void spawn() {
        if (filter > 0) {
            if (spawnClock.getElapsedTime().asSeconds() > 3 && active) {
                switch (filter) {
                    case 1:
                        spawnPowerup();
                        break;
                    case 2:
                        spawnObstacle();
                        break;
                    case 3:
                        spawnEnemy();
                        break;
                }
                spawnClock.restart();
            }
        } else {
            if (spawnClock.getElapsedTime().asSeconds() > (4 - (Engine.get().currentScene.currentLevelDifficulty * 0.5)) && active) {
                int idToSpawn = StaticHelper.getRandom(1, 101);
                if (idToSpawn < 25) {
                    spawnPowerup();
                } else if (idToSpawn < 75) {
                    spawnObstacle();
                } else {
                    spawnEnemy();
                }
                spawnClock.restart();
            }
        }
    }

    private static void spawnObstacle() {
        Engine.get().spawnEntity(generateObstacle());
    }

    public static Obstacle generateObstacle() {
        int index = StaticHelper.getRandom(0, activeObstacleTextures.size());
        Texture obstacleTexture = activeObstacleTextures.get(index);
        String textureLocation = obstacleTextureNames.get(index);
        Obstacle obstacle = new Obstacle(obstacleTexture, textureLocation);
        float playerX = Player.get().getPosition().x;
        float positionY = Cnst.floorPosition - ((obstacleTexture.getSize().y - Cnst.baseSpriteWidth) * Cnst.entityScale);
        obstacle.setPosition(playerX + Cnst.resX, positionY);
        Engine.get().queueActionToAdd(obstacle);
        return obstacle;
    }

    private static void spawnEnemy() {
        Engine.get().spawnEnemy(generateEnemy());
    }

    public static Enemy generateEnemy() {
        int index = StaticHelper.getRandom(0, activeEnemyTextures.size());
        Texture enemyTexture = activeEnemyTextures.get(index);
        String textureLocation = enemyTextureNames.get(index);
        Enemy enemy = new Enemy(enemyTexture, textureLocation);
        Engine.get().queueActionToAdd(enemy);
        float playerX = Player.get().getPosition().x;
        float playerY = Player.get().getPosition().y;
        Engine.get().spawnEnemy(enemy);
        enemy.setPosition(playerX + Cnst.resX, playerY - (3f * Cnst.entityScale) - 45);
        return enemy;
    }

    private static void spawnPowerup() {
        Entity powerup = null;
        int powerupToSpawn = StaticHelper.getRandom(0, 1000);
        if (powerupToSpawn < 250) {
            powerup = new FeatherPowerup();
        } else if (powerupToSpawn < 450) {
            powerup = new HeartPowerup();
        } else if (powerupToSpawn < 850) {
            powerup = new BreadPowerup();
        } else {
            powerup = new JumpBoostPowerup();
        }
        float playerX = Player.get().getPosition().x;
        float powerupY = StaticHelper.getRandom(Cnst.resY / 3, (int) powerup.getPosition().y);
        powerup.setPosition(playerX + Cnst.resX, powerupY);
        powerup.setCollisionBoxPosition();
        Engine.get().spawnEntity(powerup);
    }
}
