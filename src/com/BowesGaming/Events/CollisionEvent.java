package com.BowesGaming.Events;

import com.BowesGaming.Components.Entity;

/**
 * interface called at point of collision of hit-boxes
 */
public interface CollisionEvent {
    default void onCollision(Entity collidedEntity) {
    }
}
