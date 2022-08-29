package com.BowesGaming.Scenes;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Events.ScreenUpdateEvent;
import com.BowesGaming.Events.ViewMoveEvent;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

public class Background extends Sprite implements ScreenUpdateEvent, ViewMoveEvent {
    public Background() {
    }

    public void setBackground(Texture texture) {
        setTexture(texture);
        setScale(1.5f, 1.5f);
    }
    @Override
    public void onScreenUpdate() {
        Engine.get().draw(this);
    }

    @Override
    public void onViewMove(int viewMoveAmount) {
        move(viewMoveAmount, 0);
    }
}
