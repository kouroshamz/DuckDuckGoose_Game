package com.BowesGaming.Components;

import com.BowesGaming.Components.HudElements.ScoreCounter;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import org.jsfml.graphics.Texture;

public class BreadPowerup extends PowerUp {
    public BreadPowerup() {
        super("Powerups/BreadPickup.png");
        setEntityAnimation(new Animation(0.1f, new Texture(getTexture()), 20, 20));
        getEntityAnimation().changeAnimation(0, 7, 0.1f);
        setTextureRect(getEntityAnimation().getNext());
    }

    /**
     * method is called when the player and bread hit-boxes collide
     * @param collidedEntity
     */
    @Override
    public void onCollision(Entity collidedEntity) {
        switch (collidedEntity.getEntityType()) {
            case Player:
                Audio pickupSound = new Audio("Pickup.wav");
                pickupSound.play();
                ScoreCounter.score += (1000 * Engine.get().currentScene.currentLevel.scoreMultiplier);
                delete();
                break;
        }
    }
}
