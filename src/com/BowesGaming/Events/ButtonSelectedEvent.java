package com.BowesGaming.Events;

import org.jsfml.system.Vector2i;

/**
 * interface called when a button is clicked by the user
 */
public interface ButtonSelectedEvent {
    void onButtonSelection(Vector2i vector2i);
}
