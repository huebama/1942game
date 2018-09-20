package pkg1942game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseControl implements MouseListener, MouseMotionListener {

    private int mouseX;
    private int mouseY;
    private ControlPanel panel;

    public MouseControl(ControlPanel panel) {
        this.panel = panel;
    }

    public void mouseDragged(MouseEvent e) {
        panel.mouseMoved(e);
    }

    public void mouseMoved(MouseEvent e) {
        panel.mouseMoved(e);
    }

    public void mouseClicked(MouseEvent e) {
        panel.mouseMoved(e);
    }

    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (ControlPanel.state == State.MENU) {

            if (mouseX >= 25 && mouseX <= 800) {
                //play button
                if (mouseY >= 425 && mouseY <= 450) {
                    ControlPanel.state = State.CHOOSE;
                    Sound.SELECT.playSoundEffect();
                } else if (mouseY >= 500 && mouseY <= 525) {
                    //closes the screen when quit is pressed
                    System.exit(0);
                    Sound.SELECT.playSoundEffect();
                } else if (mouseY >= 575 && mouseY <= 600) {
                    ControlPanel.state = State.HELP;
                    Sound.SELECT.playSoundEffect();
                }
            }
        } else if (ControlPanel.state == State.CHOOSE) {
            if (mouseY >= 250 && mouseY <= 450) {
                //mouse or keyboard               
                if (mouseX >= 75 && mouseX <= 275) {
                    ControlPanel.state = State.GAME;                    
                    panel.mouse = true;
                    Sound.SELECT.playSoundEffect();
                } else if (mouseX >= 525 && mouseX <= 725) {
                    ControlPanel.state = State.GAME;
                    Sound.SELECT.playSoundEffect();
                }
            } else if (mouseX >= 10 && mouseX <= 60 && mouseY >= 10 && mouseY <= 60) {
                ControlPanel.state = State.MENU;
                Sound.SELECT.playSoundEffect();
            }
        } else if (ControlPanel.state == State.GAME_OVER) {
            if (mouseX >= 0 && mouseX <= 780) {
                if (mouseY >= 610 && mouseY <= 620) {
                    ControlPanel.state = State.CHOOSE;
                    Sound.GAME_OVER.stopMusic();
                    Sound.MENU.playMusic();
                    panel.init();
                    Sound.SELECT.playSoundEffect();
                } else if (mouseY >= 670 && mouseY <= 680) {
                    System.exit(0);
                    Sound.SELECT.playSoundEffect();
                }
            }
        } else if (ControlPanel.state == State.HELP) {
            if (mouseX >= 10 && mouseX <= 60 && mouseY >= 10 && mouseY <= 60) {
                ControlPanel.state = State.MENU;
                Sound.SELECT.playSoundEffect();
            }
        } else {
            panel.mousePressed(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        panel.mouseMoved(e);
    }

    public void mouseEntered(MouseEvent e) {
        panel.mouseMoved(e);
    }

    public void mouseExited(MouseEvent e) {
        panel.mouseMoved(e);
    }
}
