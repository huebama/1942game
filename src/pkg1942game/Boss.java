package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Boss extends Enemy {

    private ImageIcon icon = new ImageIcon("Kingdom/boss.png");
    private int num = 1;
    private boolean change = false;
    private int lives = 30;
    private long lastShot = 0;

    public Boss(int x, int y, int incLives) {
        super(x, y, 1, 1);
        lives += incLives;
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
            objects.add(new EnemyFireBall(x + icon.getIconWidth() / 2 - 50, y + icon.getIconHeight(), 0, 6));
            objects.add(new EnemyFireBall(x + icon.getIconWidth() / 2 - 50, y + icon.getIconHeight(), 6, 6));
            objects.add(new EnemyFireBall(x + icon.getIconWidth() / 2 - 50, y + icon.getIconHeight(), -6, 6));
	    Sound.ENEMY_SHOOT.playSoundEffect();
            lastShot = now;
        }
    }

    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        y += getYSpeed();
        if (y >= 300) {
            setYSpeed(-getYSpeed());
        }
        if (y < 0 - icon.getIconHeight()) {
            setYSpeed(getYSpeed() < 0 ? -getYSpeed() : getYSpeed());
        }

        shoot(objects, System.currentTimeMillis());
        checkHit(objects, delete, score, this, player);
    }
}
