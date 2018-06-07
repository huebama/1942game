package pkg1942game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyControl implements KeyListener {

    ControlPanel panel;

    public KeyControl(ControlPanel panel) {
        this.panel = panel;
    }

    public void keyPressed(KeyEvent e) {
        panel.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        panel.keyReleased(e);
    }

    public void keyTyped(KeyEvent e) {

    }
}

