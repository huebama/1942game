package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

//CONTROL SPAWNING IN THIS CLASS? create method called spawn, depending on player level randomizes wave of enemies of all unlocked levels
// if lvl = 1 then create all new enemylvl1 objects
// if lvl = 5 create new enemylvl2 and 1 objects, ...
//spawn method should randomize number to return to a parameter int movement when creating any enemy object
// in the specific enemy update classes, use the movement int to go through a switch statement for which movement path to follow

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
    
    public abstract void setImage();
    
    public abstract Image getEnemyImage();
    
    public abstract void paint(Graphics2D g2d);

    public abstract void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player);
}
