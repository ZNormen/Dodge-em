package com.tutorial.main;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class AudioPlayer {

    private static Clip play;

    //Plays the background music
    public static void playMenuMusic() {
        try {
            AudioInputStream menuSound = AudioSystem.getAudioInputStream(new File("res/background_music.wav"));

            play = AudioSystem.getClip();
            play.open(menuSound);

            FloatControl volume = (FloatControl) play.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(0.5f);

            play.loop(Clip.LOOP_CONTINUOUSLY);

        }catch (LineUnavailableException | IOException | UnsupportedAudioFileException e){
            e.printStackTrace();
        }
    }

    //Plays the button pressing sound effect
    public static void playMenuSound() {
        try {
            AudioInputStream menuSound = AudioSystem.getAudioInputStream(new File("res/click_sfx.wav"));

            play = AudioSystem.getClip();
            play.open(menuSound);

            FloatControl volume = (FloatControl) play.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(1.0f);

            play.loop(1/2);

        }catch (LineUnavailableException | IOException | UnsupportedAudioFileException e){
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        play.close();
    }

}