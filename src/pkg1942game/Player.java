package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends GameObject {

    private int numLives;
    private double xSpeed;
    private double ySpeed;
    private int level;
    private ImageIcon icon = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/orange dragon_16.png");
    private int numKilled = 0;
    private int killCap = 8;
    private boolean waveKilled = true;
    private int levelUpCap = 3200;
    private int numBombs = 3;

    public Player(int x, int y, double xSpeed, double ySpeed) {
        super(x, y);
        numLives = 3;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        level = 1;
    }

    public int getBombs() {
        return numBombs;
    }

    public void loseBomb() {
        numBombs--;
    }

    //this method should be used with powerups, bombs can be picked up
    public void gainBomb(int numBombs) {
        this.numBombs += numBombs;
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getPlayerImage(), getXPos(), getYPos(), null);
    }

    public void setImage() {

    }

    public ImageIcon getPlayerIcon() {
        return icon;
    }

    public Image getPlayerImage() {
        return icon.getImage();
    }

    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    public void setWaveKilled() {
        waveKilled = true;
    }

    public boolean getWaveKilled() {
        return waveKilled;
    }

    public void incNumKilled() {
        numKilled++;
    }

    //create a bunch of enemy objects depending on the number?
    //method is called in the player class?    
    public void spawn(ArrayList<GameObject> objects, ControlPanel panel) {
        Random rand = new Random();
        int xPos;
        int yPos = 0;
        int value = 0;
        int type = 0;
        int xHor = panel.getWidth();
        int yHor = 0;
        int xVert = 0;
        int yVert = 0;
        int movement = 0;
        
        movement = level >= 5 ? rand.nextInt(4) + 1 : level;

        switch (movement) {
            case 1:
                type = rand.nextInt(2) + 1;
                xPos = type == 1 ? 0 : panel.getWidth();

                for (int i = 0; i < killCap; i++) {
                    //movement is randomized from a number 1-3 (inclusive)
                    if (type == 1) {
                        value = rand.nextInt(3) + 1;
                        xPos -= 200;
                        yPos -= 150;
                        objects.add(new EnemyLvl1(xPos, yPos, value));
                    } else if (type == 2) {
                        value = rand.nextInt(3) + 4;
                        xPos += 200;
                        yPos -= 150;
                        objects.add(new EnemyLvl1(xPos, yPos, value));
                    }
                }
                break;
            case 2:
                for (int i = 0; i < killCap; i++) {
                    value = rand.nextInt(3) + 1;

                    //vertical straight down and zigzag
                    if (value == 1 || value == 2) {
                        xVert = rand.nextInt(600) + 100;
                        yVert -= 200;
                        objects.add(new EnemyLvl2(xVert, yVert, value));

                        //horizontal straight across
                    } else if (value == 3) {
                        xHor += 200;
                        yHor = rand.nextInt(200) + 100;
                        objects.add(new EnemyLvl2(xHor, yHor, value));
                    }
                }
                break;
            case 3:
                for (int i = 0; i < killCap; i++) {
                    type = rand.nextInt(2) + 1;
                    //lvl 1 type enemies
                    xPos = 0;
                    if (type == 1) {
                        value = rand.nextInt(3) + 1;
                        xPos -= 200;
                        yPos -= 150;
                        objects.add(new EnemyLvl1(xPos, yPos, value));

                        //lvl 2 type enemies
                    } else if (type == 2) {
                        value = rand.nextInt(3) + 1;
                        if (value == 1 || value == 2) {
                            xVert = rand.nextInt(600) + 100;
                            yVert -= 200;
                            objects.add(new EnemyLvl2(xVert, yVert, value));
                        } else {
                            xHor += 200;
                            yHor = rand.nextInt(200) + 100;
                            objects.add(new EnemyLvl2(xHor, yHor, value));
                        }
                    }
                }
                break;
            case 4:
                for (int i = 0; i < killCap / 2; i++) {
                    xVert = rand.nextInt(600) + 100;
                    yVert -= 200;
                    objects.add(new EnemyLvl3(xVert, yVert, value));
                    objects.add(new EnemyLvl3(panel.getWidth() - xVert, yVert, value));
                }
                break;
        }
    }

    //
    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        x += xSpeed;
        y += ySpeed;
        //values of x and y are based on the image size ~(100 x 75), panel size 800 x 700, so values subtracted
        //case if player impacts the left or right wall        
        if (x <= 0 || x >= panel.getWidth() - 75) {
            x = x <= 0 ? 0 : panel.getWidth() - 75;
        }
        //case if player impacts the bottom or top wall  
        if (y <= 0 || y >= panel.getHeight() - 60) {
            y = y <= 0 ? 0 : panel.getHeight() - 60;
        }

        //when entire wave is killed, waveKilled set to true
        if (numKilled >= killCap) {
            setWaveKilled();
            numKilled = 0;
        }

        //spawns new wave if the wave is killed
        if (waveKilled) {
            spawn(objects, panel);
            waveKilled = false;
        }

        //player levels up with each increment in 3200 points
        if (score.getScore() >= levelUpCap) {
            level++;
            levelUpCap += 10000;
        }

        //checks if player collides with fireball or enemy object and decrements life
        //later make it flash so the player knows they lost a life
        //later add in another if statement checking for instanceof PowerUp collision
        for (GameObject object : objects) {
            if (checkCollision(object) && (object instanceof Enemy || object instanceof FireBall)) {
                numLives--;
                //this ^ causes numLives to decrement with every pixel collision, need way of decrementing only with the first collision?
                //System.out.println(numLives);
            }
        }
    }
}
