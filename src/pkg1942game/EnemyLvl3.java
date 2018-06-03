package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class EnemyLvl3 extends Enemy {

    private ImageIcon icon = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/brown dragon_01.png");
    private int num = 1;
    private boolean change = false;
    private int movement;
    private int orgX;
    private int lives = 3;

    public EnemyLvl3(int x, int y, int movement) {
        super(x, y, 3, 3);
        this.movement = movement;
        orgX = x;
    }

    public int getLives() {
        return lives;
    }

    public void setLives() {
        lives--;
    }

    public Image getEnemyImage() {
        return icon.getImage();
    }

    public void setImage() {
        icon = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/player fire_0" + num + ".png");
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getEnemyImage(), getXPos(), getYPos(), null);
    }

    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        //zigzagging movement 
        y += getYSpeed();
        if ((x - orgX) >= panel.getWidth() / 6) {
            change = true;
        } else if (x == orgX) {
            change = false;
        }

        if (change) {
            x -= getXSpeed();
        } else {
            x += getXSpeed();
        }
        
        if (y > panel.getHeight() + icon.getIconHeight()) {
            y = -100;
        }
        checkHit(objects, delete, score, this, player);
    }
}
