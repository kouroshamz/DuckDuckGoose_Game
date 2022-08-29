package com.BowesGaming.Events;

import com.BowesGaming.Engine.Engine;

public interface SpriteEntersFrameEvent {
    void onSpriteEntersFrame(int viewLowerBound, int viewUpperBound, Engine engine);
}
