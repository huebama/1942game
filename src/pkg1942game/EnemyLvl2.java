package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

//increase enemy health (allow collisions twice, have variable and increment it)
public class EnemyLvl2 extends Enemy {

    private ImageIcon icon = new ImageIcon("Kingdom/pirate_03.png");
    private ImageIcon iconHor = new ImageIcon("Kingdom/pirate_12.png");
    private int num = 1;
    private boolean change = false;
    private int movement;
    private int orgX;
    private int lives = 3;

    public EnemyLvl2(int x, int y, int movement) {
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
        return movement < 3 ? icon.getImage() : iconHor.getImage();
    }

    public void setImage() {
        if (movement < 3) {
            icon = new ImageIcon("Kingdom/player fire_0" + num + ".png");
        } else {
            iconHor = new ImageIcon("Kingdom/player fire_0" + num + ".png");
        }
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getEnemyImage(), getXPos(), getYPos(), null);
    }

    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        switch (movement) {
            case 1:
                y += getYSpeed();
                break;
            case 2:
                //zigzagging movement 
                y += getYSpeed();
                if ((x - orgX) >= panel.getWidth() / 8) {
                    change = true;
                } else if (x == orgX) {
                    change = false;
                }

                if (change) {
                    x -= getXSpeed();
                } else {
                    x += getXSpeed();
                }
                break;
            case 3:
                x -= getXSpeed();
                break;
        }

        if (y > panel.getHeight()) {
            y = -100;
        }

        if (x < 0 - icon.getIconWidth() && movement == 3) {
            x = panel.getWidth() + 100;
        }

        checkHit(objects, delete, score, this, player);
    }
}
