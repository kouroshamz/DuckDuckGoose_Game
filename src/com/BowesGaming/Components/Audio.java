package com.BowesGaming.Components;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;
import org.jsfml.audio.Music;

public class Audio {
    private Music sound;

    public Audio(String audioPath) {
        sound = StaticHelper.getSoundFromPath(audioPath);
    }

    public void play() {
        try {
            sound.setVolume(Cnst.volumeLevel);
            sound.play();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        sound.pause();
    }

    public void setLoop(boolean loop) {
        sound.setLoop(loop);
    }
}
