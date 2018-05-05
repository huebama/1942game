package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public abstract class Enemy extends GameObject {

    protected double xSpeed;
    protected double ySpeed;

    public Enemy(int x, int y, double xSpeed, double ySpeed) {
        super(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    
    public double getXSpeed() {
        return xSpeed;
    }
    
    public double getYSpeed() {
        return ySpeed;
    }
    
    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }
    
    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public abstract Image getEnemyImage();
    
    public abstract void paint(Graphics2D g2d);

    public abstract void update(ControlPanel panel, GameObject object, ArrayList<GameObject> objects);
}
