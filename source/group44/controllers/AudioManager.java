package group44.controllers;

import group44.Constants;
import javafx.scene.media.AudioClip;

import java.io.File;

/**
 * Static class which controls all audio during runtime of game.
 */
public class AudioManager {

    public static void playSound(String soundPath) {
        File soundFile = new File(soundPath);
        AudioClip sound = new AudioClip(soundFile.toURI().toString());
        sound.play();
    }

}
