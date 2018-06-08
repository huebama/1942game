package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class EnemyFireBall extends GameObject {
        
    private ImageIcon iconEnemy = new ImageIcon("Kingdom/attack_78.png");
    private double xSpeed;
    private double ySpeed;

    public EnemyFireBall(int x, int y, double xSpeed, double ySpeed) {
        super(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public Image getFireBallImage() {
        return iconEnemy.getImage();
    }

    //when colliding with another object, fireball disappears
    public void setImage() {
        iconEnemy = new ImageIcon("");
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, iconEnemy.getIconWidth(), iconEnemy.getIconHeight());
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

    //fireball may move diagonally or vertically
    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        x += xSpeed;
        y += ySpeed;
    }
}


