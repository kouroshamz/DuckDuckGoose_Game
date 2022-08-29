package com.BowesGaming.Events;

import com.BowesGaming.Scenes.Scene;

/**
 * interface called when the back button is clicked by the player
 */
public interface BackButtonClickedEvent {
    void onBackButtonClicked(Scene scene);
}
