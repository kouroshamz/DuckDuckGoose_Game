package com.BowesGaming.Events;

import com.BowesGaming.Scenes.Scene;

/**
 *  interface for calling the start button
 */
public interface StartButtonClickedEvent {
    void onStartButtonClicked(Scene scene);
}
