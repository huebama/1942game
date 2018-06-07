package pkg1942game;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public enum Sound {
    BACKGROUND("Music/background.wav"),
    BOMB("Music/bomb.wav"),
    BOMB_PWRUP("Music/pwrbomb.wav"),
    ENEMY_DIE("Music/enemydie.wav"),
    ENEMY_SHOOT("Music/enemyshoot.wav"),
    FIREBALL_PWRUP("Music/pwrfire.wav"),
    GAME_OVER("Music/gameover.wav"),
    HIT("Music/hit.wav"),
    LIFE_PWRUP("Music/pwrlife.wav"),
    MENU("Music/menu.wav"),
    SHOOT("Music/shoot.wav");

    private Clip clip;

    Sound(String name) {
	try {
	    AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(name));
	    clip = AudioSystem.getClip();
	    clip.open(audioIn);
	} catch (Exception e) {
	}
    }

    // Method for playing sound effects, no looping required
    public void playSoundEffect() {
	clip.setFramePosition(0); // Sets clip to the start so there's no weird clipping noises
	clip.start();
    }

    // Method for looping music
    public void playMusic() {
	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Stops playing music so it's possible to change between tracks when changing game states
    public void stopMusic() {
	clip.stop();
    }
}
