package group44.controllers;

import group44.Constants;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Static class which controls all audio during runtime of game.
 */
public class AudioManager {

    //Menu music player set-up
    static File menuFile = new File(Constants.MENU_MUSIC);
    static Media menuMusic = new Media(menuFile.toURI().toString());
    static MediaPlayer menuPlayer = new MediaPlayer(menuMusic);

    //Game music player set-up
    static File gameFile = new File(Constants.GAME_MUSIC);
    static Media gameMusic = new Media(gameFile.toURI().toString());
    public static MediaPlayer gamePlayer = new MediaPlayer(gameMusic);

    public static void playSound(String soundPath) {
        File soundFile = new File(soundPath);
        AudioClip sound = new AudioClip(soundFile.toURI().toString());
        sound.play();
    }

    public static void playMenuMusic() {
        gamePlayer.stop();
        menuPlayer.setVolume(0.3);
        //If its not playing then play.
        if (!menuPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            menuPlayer.setAutoPlay(true);
            menuPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            menuPlayer.play();
        }
    }

    public static void playGameMusic() {
        menuPlayer.stop();
        gamePlayer.setVolume(0.3);


        gamePlayer.stop();
        gamePlayer.setAutoPlay(true);
        gamePlayer.setCycleCount(MediaPlayer.INDEFINITE);
        gamePlayer.play();


    }


}
