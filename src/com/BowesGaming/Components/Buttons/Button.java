package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Events.ButtonSelectedEvent;
import com.BowesGaming.Events.ScreenUpdateEvent;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

public abstract class Button extends Sign implements ButtonSelectedEvent, ScreenUpdateEvent {
    public boolean clickable = false;

    public Button(Texture buttonTexture, String buttonText, Font buttonFont, int buttonTextSize, int buttonPositionX, int buttonPositionY) {
        super(buttonTexture, buttonText, buttonFont, buttonTextSize, buttonPositionX, buttonPositionY);
    }

    @Override
    public void onButtonSelection(Vector2i mouseCoordinates) {
        clickable = mouseCoordinates.y > getTopLeft().y &&
                mouseCoordinates.y < getBottomRight().y &&
                mouseCoordinates.x > getTopLeft().x &&
                mouseCoordinates.x < getBottomRight().x;
    }

    @Override
    public void onScreenUpdate() {
        Engine.get().draw(this);
        Engine.get().draw(getText());
    }
}
