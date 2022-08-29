package com.BowesGaming.Events;

/**
 * tells all assets what speed and position to be in at all times
 */
public interface ViewMoveEvent {
    void onViewMove(int viewMoveAmount);
}
