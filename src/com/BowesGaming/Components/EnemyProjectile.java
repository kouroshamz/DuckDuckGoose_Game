package com.BowesGaming.Components;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ViewMoveEvent;
import org.jsfml.graphics.Texture;

public class EnemyProjectile extends Entity implements ViewMoveEvent {
    public EnemyProjectile(float x, float y) {
        Texture featherTexture = StaticHelper.getTextureFromPath("Player/FeatherProjectile.png");
        setEntityType(EntityType.EnemyProjectile);
        setTexture( featherTexture );
        setEntityAnimation(new Animation(0.1f, featherTexture, 16, 16));
        getEntityAnimation().changeAnimation(0, 0, 0.1f);
        scale(Cnst.entityScale, Cnst.entityScale);
        setTextureRect( getEntityAnimation().getNext() );
        setCollisionBox(StaticHelper.getCollisionBox("Player/FeatherProjectile.png"));
        setPosition(x, y + (featherTexture.getSize().y * Cnst.entityScale));
        rotate(180);
        setCollisionBoxPosition();
        Audio shootSound = new Audio("Shoot1.wav");
        shootSound.play();
    }

    /**
     * this method is called when two hit-boxes collide in a game
     * @param collidedEntity
     */
    @Override
    public void onCollision(Entity collidedEntity) {
        switch (collidedEntity.getEntityType()) {
            case Player:
                if (Player.get().isCollidable()) {
                    Engine.get().livesLost();
                    Audio collidedSound = new Audio("Hit2.wav");
                    collidedSound.play();
                    delete();
                }
                break;
            case FriendlyProjectile:
                delete();
                break;
        }
    }

    /**
     * shifts the background each time it gets a call from the engine
     * @param viewMoveAmount
     */
    @Override
    public void onViewMove(int viewMoveAmount) {
        move(-viewMoveAmount, 0);
        setCollisionBoxPosition();
    }
}
