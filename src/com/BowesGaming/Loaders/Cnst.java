package com.BowesGaming.Loaders;

public class Cnst {
    public static String propertiesLocation = "assets/Properties/Properties.properties";
    public static Builder builder = new Builder(propertiesLocation);

    public static String baseAssetsLocation;
    public static String fontsLocation;
    public static String texturesLocation;
    public static String soundsLocation;

    public static int thisRoundScore = 0;

    //Display Constants
    public static int resX;
    public static int resY;
    public static int screenRefreshRate;
    public static float screenUpdatePeriod;

    public static int skinID;

    //Audio constants
    public static int volumeLevel;

    //Level Mapping Constants
    public static int platformWidth;
    public static int entityScale;
    public static int baseSpriteWidth;
    public static int floorPosition;
    public static int obstacleBuffer;
    public static int platformLength;
    public static int levelRunoffZone;
    public static int levelSpawnPointZone;

    //Gameplay Constants
    public static int playerMovementSpeed;
    public static int baseViewMoveSpeed;
    public static double baseScoreMultiplier;
    public static int featherTimeInSeconds;

    //Obstacle spawning constants
    /*Distribution- for a random index x, y will be returned:
     * 0 20%
     * 1 10%
     * 2 20%
     * 3 30%
     * 4 20%
     * of the time.
     * */
    public static int[] distributionTable = {0,0,1,2,2,3,3,3,4,4,0,0,1,2,2,3,3,3,4,4,0,0,1,2,2,3,3,3,4,4,0,0,1,2,2,3,3,3,4,4,0,0,1,2,2,3,3,3,4,4,0,0,1,2,2,3,3,3,4,4,0,0,1,2,2,3,3,3,4,4,0,0,1,2,2,3,3,3,4,4};
    public static int distributionSegments = 5;
    public static float[] regionWeighting = {0.2f, 0.1f, 0.2f, 0.3f, 0.2f};

    //Gameplay Points Rewards
    public static int successfulJumpReward;
    public static int breadScoreValue;
    public static int difficultyLevel;

    public static float MOVESPEED;
    public static float MOVEPERIOD;
    public static int duckX;
    public static int jumpHeight;
    public static int velocity;
    public static int duckScale;

    /**
     * this class is used to get the values for all constants in the properties.properties file based on their name
     */
    public static void getConstants() {
        baseAssetsLocation = Builder.getString("baseAssetsLocation");
        fontsLocation = baseAssetsLocation + "/" + Builder.getString("fontsLocation");
        propertiesLocation = baseAssetsLocation + "/" + Builder.getString("propertiesLocation");
        texturesLocation = baseAssetsLocation + "/" + Builder.getString("texturesLocation");
        soundsLocation = baseAssetsLocation + "/" + Builder.getString("soundsLocation");

        resX = Builder.getInt("screenResolutionX");
        resY = (int) (resX * Builder.getFloat("screenResolutionY"));
        screenRefreshRate = Builder.getInt("screenRefreshRate");
        screenUpdatePeriod = Builder.getFloat("screenUpdatePeriod");

        volumeLevel = Builder.getInt("volumeLevel");

        platformWidth = Builder.getInt("platformWidth");
        entityScale = resX / Builder.getInt("entityScale");
        baseSpriteWidth = Builder.getInt("baseSpriteWidth");
        floorPosition = (int) (resY * Builder.getFloat("floorPosition"));
        obstacleBuffer = Builder.getInt("obstacleBuffer");
        platformLength = Builder.getInt("platformLength");

        playerMovementSpeed = Builder.getInt("playerMovementSpeed");
        baseViewMoveSpeed = Builder.getInt("baseViewMoveSpeed");
        baseScoreMultiplier = Builder.getInt("baseScoreMultiplier");
        featherTimeInSeconds = Builder.getInt("featherTimeInSeconds");

        successfulJumpReward = Builder.getInt("successfulJumpReward");
        breadScoreValue = Builder.getInt("breadScoreValue");
        difficultyLevel = Builder.getInt("difficultyLevel");

        MOVESPEED = Builder.getInt("MOVESPEED");
        MOVEPERIOD = Builder.getFloat("MOVEPERIOD");
        duckX = Builder.getInt("duckX");
        jumpHeight = Builder.getInt("jumpHeight");
        velocity = Builder.getInt("velocity");
        duckScale = Builder.getInt("duckScale");
        skinID = Builder.getInt("skinID");

        levelRunoffZone = Builder.getInt("levelRunoffZone");
        levelSpawnPointZone = Builder.getInt("levelSpawnPointZone");
    }

    /**
     * this method is used to set the float type properties
     * @param key
     * @param value
     */
    public static void setFloat(String key, float value) {
        Builder.setProperty(propertiesLocation, key, String.format("%f", value));
    }

    /**
     * this method is used to set the Int type properties
     * @param key
     * @param value
     */
    public static void setInt(String key, int value) {
        Builder.setProperty(propertiesLocation, key, String.format("%d", value));
    }

    /**
     * this method is used to set the string type properties
     * @param key
     * @param value
     */
    public static void setString(String key, String value) {
        Builder.setProperty(propertiesLocation, key, String.format("%s", value));
    }

    /**
     * this method is used to set the Double type properties
     * @param key
     * @param value
     */
    public static void setDouble(String key, Double value) {
        Builder.setProperty(propertiesLocation, key, String.format("%f", value));
    }
}