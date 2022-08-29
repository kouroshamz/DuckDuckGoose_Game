package com.BowesGaming.Components.HudElements;

import com.BowesGaming.Components.Player;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Events.AnimationEvent;
import com.BowesGaming.Events.LivesGainedEvent;
import com.BowesGaming.Events.LivesLostEvent;
import com.BowesGaming.Events.ViewMoveEvent;
import org.jsfml.system.Clock;

import java.util.ArrayList;
import java.util.List;

public class Heartmeter implements LivesLostEvent, ViewMoveEvent, AnimationEvent, LivesGainedEvent {
    private List<Icon> hearts = new ArrayList<>();
    private List<Clock> brokenHeartTimer = new ArrayList<>();

    public Heartmeter(int positionX, int positionY) {
        for (int i = 0; i < Player.get().currentLives; i++) {
            Icon heart = new Icon("UserInterface/Heart.png");
            int calculatedX = (int) (positionX + (i * (Cnst.entityScale/2) * Cnst.baseSpriteWidth));
            heart.setPosition(calculatedX, positionY);
            heart.setScale(Cnst.entityScale/2, Cnst.entityScale/2);
            heart.createAnimationState("stationary", 0, 3);
            heart.createAnimationState("breaking", 0, 13);
            heart.createAnimationState("broken", 13, 14);
            heart.setAnimationState("stationary", 10);
            brokenHeartTimer.add(new Clock());
            hearts.add( heart );
        }
    }

    public List<Icon> getHearts() {
        return hearts;
    }

    @Override
    public void onLivesLost() {
        if (Player.get().currentLives >= 0) {
            Icon heart = hearts.get(Player.get().currentLives);
            if (!(heart.currentAnimationLabel.equals("breaking"))) {
                heart.setAnimationState("breaking", 0.04f);
                brokenHeartTimer.get(hearts.indexOf(heart)).restart();
            }
        }
    }

    @Override
    public void onLivesGained() {
        int playerLives = Player.get().currentLives-1;
        if (playerLives <= 5) {
            if (hearts.get(playerLives).currentAnimationLabel.equals("breaking") || hearts.get(playerLives).currentAnimationLabel.equals("broken")) {
                hearts.get(playerLives).setAnimationState("stationary", 10);
            }
        }
    }

    @Override
    public void onViewMove(int viewMoveAmount) {
        for (Icon heart : hearts) {
            heart.onViewMove(viewMoveAmount);
            if (heart.currentAnimationLabel.equals("breaking")) {
                if (brokenHeartTimer.get(hearts.indexOf(heart)).getElapsedTime().asSeconds() > (0.04f * 14)) {
                    heart.setAnimationState("broken", 10);
                }
            }
            heart.onScreenUpdate();
        }
    }

    @Override
    public void onAnimationEvent() {
        for (Icon heartIcons : hearts) {
            heartIcons.onAnimationEvent();
        }
    }
}
