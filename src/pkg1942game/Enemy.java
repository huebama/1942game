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
    private boolean hit = false;
    private int num = 0;

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
    
    //checks to see if fireball hits enemy
    public void checkHit(ArrayList<GameObject> objects, ArrayList<GameObject> delete, Score score, Enemy enemy, Player player) {
                //checks if fireball hits enemy or player object 
        //need to add these objects to the delete array list
        for (GameObject check : objects) {
            if (checkCollision(check) && (check instanceof FireBall || check instanceof Bomb)) {
                check.setImage();
                check.setXSpeed(0);
                check.setYSpeed(0);
                delete.add(check);
                //bomb deals 2 damage
                if (check instanceof Bomb) {
                    enemy.setLives();
                }
                enemy.setLives();
                score.setScore(100);
            }
        }

        hit = enemy.getLives() <= 0 ? true : false;

        if (hit) {
            enemy.setImage();
            num = num < 10 ? num + 1 : 100;
            if (num == 100) {
                player.incNumKilled();
                delete.add(this);
            }
        }
    }
    
    public abstract void setImage();
    
    public abstract int getLives();
    
    public abstract void setLives();
    
    public abstract Image getEnemyImage();
    
    public abstract void paint(Graphics2D g2d);

    public abstract void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player);
}
