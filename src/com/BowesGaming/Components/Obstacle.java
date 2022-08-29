package com.BowesGaming.Components;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import org.jsfml.graphics.Texture;

public class Obstacle extends Entity {

    public Obstacle(Texture texture, String collisionBoxTexture) {
        setEntityType(EntityType.Obstacle);
        setTexture(texture);
        setEntityAnimation(new Animation(0.5f, texture, Cnst.baseSpriteWidth, texture.getSize().y));
        getEntityAnimation().changeAnimation(0, getEntityAnimation().activeFrames.size()-1, 0.5f);
        scale(Cnst.entityScale, Cnst.entityScale);
        setTextureRect( getEntityAnimation().getNext() );
        setCollisionBox(StaticHelper.getCollisionBox(collisionBoxTexture));
        calculateCollisionBoxOffset();
    }

    /**
     * this method is called when two hit-boxes collide in a game
     * @param collidedEntity
     */
    @Override
    public void onCollision(Entity collidedEntity) {
        if (collidedEntity.isCollidable()) {
            switch (collidedEntity.getEntityType()) {
                case Player:
                    if (Player.get().isCollidable()) {
                        Engine.get().livesLost();
                        Audio jumpSound = new Audio("Hit4.wav");
                        jumpSound.play();
                        delete();
                    }
                    break;
            }
        }
    }
}
