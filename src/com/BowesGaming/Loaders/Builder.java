package com.BowesGaming.Loaders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//public class Builder extends Entity {
public class Builder {

    static Properties prop;
    static String key;
    static String value;

    /**
     * the Builder class is used for reading and writing into file
     * @param path
     */
    public Builder(String path) {
        prop = new Properties();
        try {
            FileInputStream ip= new FileInputStream( path );
            prop.load(ip);
            ip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * this method is used to get the float type properties
     * @param key
     * @return
     */
    public static float getFloat(String key) {
        return Float.parseFloat(prop.getProperty(key));
    }

    /**
     * this method is used to get the Double type properties
     * @param key
     * @return
     */
    public static Double getDouble(String key) {
        return Double.parseDouble(prop.getProperty(key));
    }

    /**
     * this method is used to get the properties
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    /**
     * this method is used to get the Int type properties
     * @param key
     * @return
     */
    public static int getInt (String key) {
        return Integer.parseInt(prop.getProperty(key));
    }

    /**
     * this method is used to get the string type properties
     * @param key
     * @return
     */
    public static String getString(String key) {
        return prop.getProperty(key);
    }

    /**
     * this method is used to set the properties
     * @param path
     * @param key
     * @param value
     */
    public static void setProperty(String path, String key, String value) {
        try {
            FileOutputStream out = new FileOutputStream(path);
            prop.setProperty(key, value);
            prop.store(out, null);
            out.close();
        } catch (Exception e) {
//            System.out.println(e);
        }
    }

    /**
     * this method adds the top 5 high scores in the game to the list
     * @return returns the list
     */
    public static List<Integer> getHighScores() {
        List<Integer> list = new ArrayList<>();
        list.add(getInt("highScore1"));
        list.add(getInt("highScore2"));
        list.add(getInt("highScore3"));
        list.add(getInt("highScore4"));
        list.add(getInt("highScore5"));
        return list;
    }

    /**
     * this method is used to set the players high score
     * @param highScores
     */
    public static void setHighScores(List<Integer> highScores) {
        for (int counter = 0; counter < highScores.size(); counter++) {
            setProperty("assets/Properties/Properties.properties", String.format("highScore%d", (counter+1)), String.format("%d", highScores.get(counter)));
        }
    }

}
