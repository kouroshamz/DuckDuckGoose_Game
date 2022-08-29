package com.BowesGaming.Components;

import com.BowesGaming.Components.HudElements.ScoreCounter;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ViewMoveEvent;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;

public class Enemy extends Entity implements ViewMoveEvent {
    private Clock projectileClock = new Clock();

    public Enemy(Texture texture, String collisionBoxTexture) {
        setEntityType(EntityType.Enemy);
        setTexture(texture);
        setEntityAnimation(new Animation(0.5f, texture, 18, 16));
        getEntityAnimation().changeAnimation(14, getEntityAnimation().activeFrames.size()-1, 0.1f);
        scale(Cnst.entityScale, Cnst.entityScale);
        setTextureRect( getEntityAnimation().getNext() );
        setCollisionBox(StaticHelper.getCollisionBox(collisionBoxTexture));
        setCollisionBoxPosition();
    }

    /**
     * shifts the background each time it gets a call from the engine
     * @param viewMoveAmount
     */
    @Override
    public void onViewMove(int viewMoveAmount) {
        move(-viewMoveAmount/16, 0);
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
                    if (Player.get().isCollidable() && Player.get().playerState != Player.PlayerState.CROUCHING) {
                        Engine.get().livesLost();
                        Audio hitsound = new Audio("Hit2.wav");
                        hitsound.play();
                        delete();
                    }
                    break;
                case FriendlyProjectile:
                    ScoreCounter.score +=  (50 * Engine.get().currentScene.currentLevel.scoreMultiplier);
                    collidedEntity.delete();
                    delete();
                    break;
            }
        }
        setCollidable(false);
    }

    /**
     * makes the enemy shoot when a projectile is passed
     */
    @Override
    public void refresh() {
        if (projectileClock.getElapsedTime().asSeconds() > 1) {
            EnemyProjectile enemyProjectile = new EnemyProjectile(this.getPosition().x, this.getPosition().y);
            Engine.get().spawnEnemyProjectile(enemyProjectile);
            projectileClock.restart();
        }
    }
}
