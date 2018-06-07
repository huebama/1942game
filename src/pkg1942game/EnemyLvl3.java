package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class EnemyLvl3 extends Enemy {

    private ImageIcon icon = new ImageIcon("Kingdom/big_13.png");    
    private int num = 1;
    private boolean change = false;
    private int movement;
    private int orgX;
    private int lives = 5;
    private long lastShot = 0;

    public EnemyLvl3(int x, int y, int movement) {
        super(x, y, 2, 2);
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
        icon = new ImageIcon("Kingdom/player fire_0" + num + ".png");
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getEnemyImage(), getXPos(), getYPos(), null);
    }
    
    public void shoot(ArrayList<GameObject> objects, Long now) {
        if (now - lastShot >= 3000) {
            objects.add(new EnemyFireBall(x + icon.getIconWidth() / 2, y + icon.getIconHeight() / 2, 0, 6));
	    Sound.ENEMY_SHOOT.playSoundEffect();
            lastShot = now;
        }
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
        
        shoot(objects, System.currentTimeMillis());
        checkHit(objects, delete, score, this, player);
    }
}
