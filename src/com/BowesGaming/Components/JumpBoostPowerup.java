package com.BowesGaming.Components;

import com.BowesGaming.Components.HudElements.ScoreCounter;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import org.jsfml.graphics.Texture;

public class JumpBoostPowerup extends PowerUp {

    public JumpBoostPowerup() {
        super("Powerups/JumpBoostPowerUps.png");
        setEntityAnimation(new Animation(0.1f, new Texture(getTexture()), 21, 20));
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
                delete();
                Audio jumpSound = new Audio("Powerup.wav");
                jumpSound.play();
                if (Player.get().maxJumps == 3) {
                    ScoreCounter.score += (500 * Engine.get().currentScene.currentLevel.scoreMultiplier);;
                } else {
                    Player.get().maxJumps = 3;
                }
                Player.get().jumpsLeft++;
                break;
        }
    }
}
