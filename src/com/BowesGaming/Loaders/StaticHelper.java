package com.BowesGaming.Loaders;

import com.BowesGaming.Engine.Engine;
import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

public class StaticHelper {
    public static Texture getTextureFromPath(String fileName) {
        Texture texture = new Texture();
        try {
            texture.loadFromFile(Paths.get(Cnst.texturesLocation+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texture;
    }

    public static Font getFontFromPath(String fileName) {
        Font font = new Font();
        try {
            font.loadFromFile( Paths.get(Cnst.fontsLocation+fileName) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return font;
    }

    public static Image getImageFromPath(String fileName) {
        Image image = new Image();
        try {
            image.loadFromFile( Paths.get( Cnst.texturesLocation+fileName ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Music getSoundFromPath(String fileName) {
        Music music = new Music();
        try {
            music.openFromFile( Paths.get(Cnst.soundsLocation+fileName) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return music;
    }

    public static RectangleShape getCollisionBox(String filename) {
        String hitbox = "Hitboxes/";
        String png = ".png";
        Texture getSizeOfMe;
//        System.out.println(filename);
        switch (filename) {
            case "Player/Default.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Duck"+png);
                break;
            case "Obstacles/Banana.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Banana"+png);
                break;
            case "Obstacles/Bin_1.png":
            case "Obstacles/Bin_2.png":
            case "Obstacles/Bin_3.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Bin"+png);
                break;
            case "Obstacles/BlueTulip.png":
            case "Obstacles/RedTulip.png":
            case "Obstacles/OrangeTulip.png":
            case "Obstacles/YellowTulip.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Tulip"+png);
                break;
            case "Obstacles/Books.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Books"+png);
                break;
            case "Obstacles/Chair.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Chair"+png);
                break;
            case "Obstacles/GoldCandle.png":
            case "Obstacles/SilverCandle.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Candle"+png);
                break;
            case "Obstacles/Kettlebell.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Kettlebell"+png);
                break;
            case "Obstacles/NoDucks.png":
                getSizeOfMe = getTextureFromPath(hitbox+"NoDucks"+png);
                break;
            case "Obstacles/NoDucksBig.png":
                getSizeOfMe = getTextureFromPath(hitbox+"NoDucksBig"+png);
                break;
            case "Obstacles/StoneCross.png":
            case "Obstacles/WoodCross.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Cross"+png);
                break;
            case "Obstacles/Sunflower.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Sunflower"+png);
                break;
            case "Obstacles/TrafficCone.png":
                getSizeOfMe = getTextureFromPath(hitbox+"TrafficCone"+png);
                break;
            case "Obstacles/TyreDown.png":
                getSizeOfMe = getTextureFromPath(hitbox+"TyreDown"+png);
                break;
            case "Obstacles/TyreUp.png":
                getSizeOfMe = getTextureFromPath(hitbox+"TyreUp"+png);
                break;
            case "Obstacles/WaterBottle.png":
                getSizeOfMe = getTextureFromPath(hitbox+"WaterBottle"+png);
                break;
            case "Obstacles/Weight.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Weight"+png);
                break;
            case "Obstacles/WetFloorSign.png":
                getSizeOfMe = getTextureFromPath(hitbox+"WetFloorSign"+png);
                break;
            case "Obstacles/BlackGoose.png":
            case "Obstacles/WhiteGoose.png":
                getSizeOfMe = getTextureFromPath(hitbox+"FlyingGoose"+png);
                break;
            case "Enemies/BigGooseHead.png":
                getSizeOfMe = getTextureFromPath(hitbox+"GooseHead"+png);
                break;
            case "Powerups/BreadPickup.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Bread"+png);
                break;
            case "Powerups/FeatherPickup.png":
                getSizeOfMe = getTextureFromPath(hitbox+"FeatherPickup"+png);
                break;
            case "Powerups/HeartPickup.png":
                getSizeOfMe = getTextureFromPath(hitbox+"Heart"+png);
                break;
            case "Player/FeatherProjectile.png":
                getSizeOfMe = getTextureFromPath(hitbox+"FeatherProjectile"+png);
                break;
            case "CrouchedDuck":
                getSizeOfMe = getTextureFromPath(hitbox+"DuckCrouched"+png);
                break;
            default:
                getSizeOfMe = getTextureFromPath(hitbox+"Books"+png);
                break;
        }
        return new RectangleShape(new Vector2f( getSizeOfMe.getSize().x* Cnst.entityScale, getSizeOfMe.getSize().y* Cnst.entityScale ));
    }

    public static int getRandom(int lower, int upper) {
        return (int) ((Math.random() * (upper-lower)) + lower);
    }

    public static float getRandom(int upper) {
        return (float) (Math.random() * (upper + 1 ));
    }

    public static Text makeCentreText(String textString, int fontSize) {
        Text textToReturn = new Text(textString, getFontFromPath("EndlessBossBattle.ttf"));
        textToReturn.setCharacterSize(fontSize);
        FloatRect text3bounds = textToReturn.getLocalBounds();
        textToReturn.setOrigin(text3bounds.width / 2, text3bounds.height / 2);
        textToReturn.setPosition(Engine.get().getView().getCenter().x, Engine.get().getView().getCenter().y);
        textToReturn.setColor(Color.WHITE);
        return textToReturn;
    }
}
