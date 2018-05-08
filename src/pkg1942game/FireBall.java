package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class FireBall extends GameObject {

    private boolean enemy;
    private double xSpeed;
    private double ySpeed;
    private int powerLevel;
    private ImageIcon icon = new ImageIcon(enemy ? "Sprites/fireball_56.png" : "Sprites/player fire_14.png");

    public FireBall(int x, int y, double xSpeed, double ySpeed, boolean enemy, int powerLevel) {
        super(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.enemy = enemy;
        this.powerLevel = powerLevel;
    }

    public Image getFireBallImage() {
        return icon.getImage();
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getFireBallImage(), getXPos(), getYPos(), null);
    }

    public void update(ControlPanel panel, GameObject object, ArrayList<GameObject> objects) {
        //the enemy fireball has x component, player doesn't
        if (enemy) {
            x += xSpeed;
            y += ySpeed;
        } else {
            y -= ySpeed;
        }
        
        for (GameObject check : objects) {
            if (checkCollision(check) && !(check instanceof FireBall)) {
                System.out.println("yes");
            }
        }
    }
}
