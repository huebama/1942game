package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

//increase enemy health (allow collisions twice, have variable and increment it)

public class EnemyLvl2 extends Enemy {

    private ImageIcon icon = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/red dragon_01.png");
    private ImageIcon iconBack = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/red dragon_11.png");
    private boolean forward = true;
    private int num = 1;
    private boolean hit = false;
    private int score = 0;
    private boolean change = false;

    public EnemyLvl2(int x, int y) {
        super(x, y, 3, 3);
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
        y += getYSpeed();
        
        //zigzagging movement {
        if (x >= panel.getWidth() / 8) {
            change = true;
        } else if (x == 0) {
            change = false;
        }
        
        if (change) {
            x -= getXSpeed();
        } else {
            x += getXSpeed();
        }
        //}                
        
        if (y > panel.getHeight()) {
            y = -50;
        }
        
        //left or right wall impact
        if (x < 0 || x > panel.getWidth() - icon.getIconWidth()) {
            setXSpeed(-getXSpeed());
        }

        //checks if fireball hits enemy or player object 
        //need to add these objects to the delete array list
        for (GameObject check : objects) {
            if (checkCollision(check) && check instanceof FireBall) {
                this.setXSpeed(0);
                this.setYSpeed(0);
                check.setImage();
                check.setXSpeed(0);
                check.setYSpeed(0);
                this.setImage();
                hit = true;
                score.setScore(200);
            }
        }
        if (hit) {
            num = num < 10 ? num + 1 : 100;
            this.setImage();
        }
    }
}
