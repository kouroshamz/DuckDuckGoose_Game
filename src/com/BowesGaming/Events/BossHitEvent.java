package com.BowesGaming.Events;

import com.BowesGaming.Components.Entity;

public interface BossHitEvent {
    void onBossHit(Entity friendlyEntity, Entity entity);
}
