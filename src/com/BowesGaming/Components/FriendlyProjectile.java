package com.BowesGaming.Components;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ViewMoveEvent;
import org.jsfml.graphics.Texture;

public class FriendlyProjectile extends Entity implements ViewMoveEvent {
    public FriendlyProjectile() {
        Texture featherTexture = StaticHelper.getTextureFromPath("Player/FeatherProjectile.png");
        setEntityType(EntityType.FriendlyProjectile);
        setTexture( featherTexture );
        setEntityAnimation(new Animation(0.1f, featherTexture, 16, 16));
        getEntityAnimation().changeAnimation(0, 0, 0.1f);
        scale(Cnst.entityScale, Cnst.entityScale);
        setPosition(Player.get().getPosition().x, Player.get().getPosition().y);
        setTextureRect( getEntityAnimation().getNext() );
        setCollisionBox(StaticHelper.getCollisionBox("Player/FeatherProjectile.png"));
        setCollisionBoxPosition();
        Audio shootSound = new Audio("Shoot1.wav");
        shootSound.play();
    }

    /**
     * moves the projectile with respect to background
     * @param viewMoveAmount
     */
    @Override
    public void onViewMove(int viewMoveAmount) {
        move(viewMoveAmount*2, 0);
    }
}
