package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public abstract class Enemy extends GameObject {

    protected double xSpeed;
    protected double ySpeed;
    private boolean hit = false;
    private int num = 0;
    private int powerUp = 0;
    private Random rand = new Random();
    private int type = 0;

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
        for (GameObject check : objects) {
            if (checkCollision(check) && (check instanceof FireBall || check instanceof Bomb)) {
                check.setImage();
                check.setXSpeed(0);
                check.setYSpeed(0);
                delete.add(check);
                Sound.ENEMY_DIE.playSoundEffect();
                //bomb deals 5 damage
                if (check instanceof Bomb) {
                    for (int i = 0; i < 4; i++) {
                        enemy.setLives();
                    }
                }
                //chance enemy will drop powerup when u shoot it
                powerUp = rand.nextInt(20) + 1;
                if (powerUp == 1) {
                    //level cap at 5
                    type = player.getFireballLevel() < 5 ? rand.nextInt(3) + 1 : rand.nextInt(2) + 2;
                    //shooting powerup
                    if (type == 1) {
                        //decreases the chances for this powerup more
                        type = rand.nextInt(3) + 1;
                        if (type == 1) {
                            objects.add(new FireBallUp(x, y));
                        }
                        //extra bombs
                    } else if (type == 2) {
                        objects.add(new PlusBombs(x, y));
                        //plus one life
                    } else if (type == 3) {
                        objects.add(new PlusLife(x, y));
                    }
                }
                enemy.setLives();
                //100 points for every bullet landed on the enemy
                score.setScore(100);
            }
        }

        hit = enemy.getLives() <= 0 ? true : false;

        //animates enemy vanishing
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
