package com.BowesGaming.Components;

import com.BowesGaming.Components.HudElements.ScoreCounter;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import org.jsfml.graphics.Texture;

public class HeartPowerup extends PowerUp {

    public HeartPowerup() {
        super("Powerups/HeartPickup.png");
        setEntityAnimation(new Animation(0.1f, new Texture(getTexture()), 23, 20));
        getEntityAnimation().changeAnimation(0, 7, 0.1f);
        setTextureRect(getEntityAnimation().getNext());
    }

    /**
     * this method is called when two hit-boxes collide in a game
     * @param collidedEntity
     */
    @Override
    public void onCollision(Entity collidedEntity) {
        switch (collidedEntity.getEntityType()) {
            case Player:
                Audio jumpSound = new Audio("Powerup.wav");
                jumpSound.play();
                if (Player.get().currentLives < 5) {
                    Player.get().currentLives += 1;
                } else {
                    ScoreCounter.score += (1000 * Engine.get().currentScene.currentLevel.scoreMultiplier);;
                }
                Engine.get().livesGained();
                delete();
                break;
        }
    }
}
