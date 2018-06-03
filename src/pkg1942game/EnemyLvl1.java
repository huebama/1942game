package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class EnemyLvl1 extends Enemy {

    private ImageIcon icon = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/black dragon_01.png");
    private ImageIcon iconBack = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/black dragon_12.png");
    private boolean forward = true;
    private int num = 1;
    private int movement;
    private int orgX;
    private int orgY;
    private int lives = 1;

    public EnemyLvl1(int x, int y, int movement) {
        super(x, y, 5, 5);
        this.movement = movement;
        orgX = x;
        orgY = y;
    }

    public int getLives() {
        return lives;
    }

    public void setLives() {
        lives--;
    }

    public Image getEnemyImage() {
        return forward ? icon.getImage() : iconBack.getImage();
    }

    public void setImage() {
        if (forward) {
            icon = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/player fire_0" + num + ".png");
        } else {
            iconBack = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/player fire_0" + num + ".png");
        }
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getEnemyImage(), getXPos(), getYPos(), null);
    }

    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        //add switch statement with different enemy path options with variable movement
        //need to add spawning, randomized spawning from top or side of page (spawning based on which enemy path as well)
        switch (movement) {
            case 1:
                x += xSpeed;
                y += ySpeed;
                break;
            case 2:
                x += xSpeed;
                y += ySpeed - 1;
                break;
            case 3:
                x += xSpeed;
                y += ySpeed - 2;
                break;
            case 4:
                x -= xSpeed;
                y += ySpeed;
                break;
            case 5:
                x -= xSpeed;
                y += ySpeed - 1;
                break;
            case 6:
                x -= xSpeed;
                y += ySpeed - 2;
                break;
        }
        //can reduce this
        if (movement <= 3) {
            if (x > panel.getWidth() || y > panel.getHeight()) {
                //change these to the same values, time difference already set, maybe start them all at -200, -200?
                x = orgX;
                y = orgY;
            }
        } else {
            if (x < 0 - icon.getIconWidth() || y > panel.getHeight()) {
                x = orgX;
                y = orgY;
            }
        }

        checkHit(objects, delete, score, this, player);
    }
}
