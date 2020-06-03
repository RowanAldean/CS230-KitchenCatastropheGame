package group44.controllers;

import group44.Constants;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * A static class which controls all audio and sounds during runtime.
 */
public class AudioManager {

    //Menu music player set-up
    private static File menuFile = new File(Constants.MENU_MUSIC);
    private static Media menuMusic = new Media(menuFile.toURI().toString());
    private static MediaPlayer menuPlayer = new MediaPlayer(menuMusic);

    //Game music player set-up
    private static File gameFile = new File(Constants.GAME_MUSIC);
    private static Media gameMusic = new Media(gameFile.toURI().toString());
    private static MediaPlayer gamePlayer = new MediaPlayer(gameMusic);

    /**
     * Plays any audio file given, used for playing any sounds during runtime.
     * @param soundPath The path to the audio file for the sound intended to play.
     */
    public static void playSound(String soundPath) {
        File soundFile = new File(soundPath);
        AudioClip sound = new AudioClip(soundFile.toURI().toString());
        sound.play();
    }

    /**
     * Controls the play of the {@link #menuPlayer}.
     */
    public static void playMenuMusic() {
        gamePlayer.stop();
        menuPlayer.setVolume(0.2);
        //If its not playing then play.
        if (!menuPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            menuPlayer.setAutoPlay(true);
            menuPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            menuPlayer.play();
        }
    }

    /**
     * Controls the play of the {@link #gamePlayer}.
     */
    public static void playGameMusic() {
        menuPlayer.stop();
        gamePlayer.setVolume(0.1);

        //If its not playing then play.
        if (!gamePlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            gamePlayer.setAutoPlay(true);
            gamePlayer.setCycleCount(MediaPlayer.INDEFINITE);
            gamePlayer.play();
        }
    }

    /**
     * Pauses the {@link #gamePlayer}.
     */
    public static void pauseGameMusic(){
        gamePlayer.stop();
    }

    /**
     * Sets the audio playback volume for the {@link #gamePlayer}. Its effect will be clamped to the range
     * <code>[0.0,&nbsp;1.0]</code>.
     *
     * @param volumeValue the volume
     */
    public static void setGameVolume(double volumeValue){
        gamePlayer.setVolume(volumeValue);
    }

}
