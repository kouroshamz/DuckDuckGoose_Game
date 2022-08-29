package com.BowesGaming.Components;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;
import org.jsfml.graphics.Texture;

public abstract class PowerUp extends Entity {
    /**
     * this method is for creating an power up in game
     * @param path
     */
    public PowerUp(String path) {
        Texture texture = StaticHelper.getTextureFromPath(path);
        setCollisionBox(StaticHelper.getCollisionBox(path));
        setTexture(texture);
        setScale(Cnst.entityScale, Cnst.entityScale);
        setPosition(0, Cnst.floorPosition - ((20 - Cnst.baseSpriteWidth) * Cnst.entityScale));
        setCollisionBoxPosition();
    }
}
