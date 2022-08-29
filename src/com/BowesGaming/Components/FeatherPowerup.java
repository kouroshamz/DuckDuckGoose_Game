package com.BowesGaming.Components;

import com.BowesGaming.Loaders.StaticHelper;
import org.jsfml.graphics.Texture;

public class FeatherPowerup extends PowerUp {
    public FeatherPowerup() {
        super("Powerups/FeatherPickup.png");
        setEntityAnimation(new Animation(0.1f, new Texture(getTexture()), 21, 20));
        getEntityAnimation().changeAnimation(0, 7, 0.1f);
        setTextureRect(getEntityAnimation().getNext());
    }

    /**
     * methods is called when the feather collides with entity
     * @param collidedEntity
     */
    @Override
    public void onCollision(Entity collidedEntity) {
        switch (collidedEntity.getEntityType()) {
            case Player:
                Audio shootSound = new Audio("Pickup2.wav");
                shootSound.play();
                delete();
                Player.get().featherEquipped = true;
                Player.get().featherTimer.restart();
                break;
        }
    }
}
