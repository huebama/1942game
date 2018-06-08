package pkg1942game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends GameObject {

    private int numLives;
    private double xSpeed;
    private double ySpeed;
    private int level;
    private ImageIcon icon = new ImageIcon("Kingdom/sora sprites_1.png");
    private ImageIcon heart = new ImageIcon("Kingdom/heart.png");
    private ImageIcon bomb = new ImageIcon("Kingdom/attack_155.png");
    private ImageIcon keyblade = new ImageIcon("Kingdom/keyblade_01.png");
    private ImageIcon levelUp = new ImageIcon("Kingdom/levelup.png");
    private Font font;
    private Font font2;
    private int numKilled = 0;
    private int killCap = 8;
    private boolean waveKilled = true;
    private int levelUpCap = 3200;
    private int numBombs = 3;
    private long lastCollision = 0;
    private int fireballLevel = 1;
    private boolean boss = false;
    private int incLives = 0;
    private boolean playerHit = false;
    private long lastFlash = 0;
    private long now = 0;
    public long duration = 0;
    private int lastLevel = 1;
    private long levelNow = 0;
    private long levelLast = 0;

    public Player(int x, int y, double xSpeed, double ySpeed) {
        super(x, y);
        numLives = 3;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        level = 1;
    }

    //returns player lives
    public int getLives() {
        return numLives;
    }

    //adds lives 
    public void gainLives(int lives) {
        numLives += lives;
    }

    //increases the level of player fire
    public void setFireballLevel() {
        fireballLevel++;
    }

    //returns the level of player fire
    public int getFireballLevel() {
        return fireballLevel;
    }
 
    //returns number of bombs player has
    public int getBombs() {
        return numBombs;
    }

    //decrements amount of bombs
    public void loseBomb() {
        numBombs--;
    }

    //player gains bombs 
    public void gainBomb(int numBombs) {
        this.numBombs += numBombs;
    }

    public void paint(Graphics2D g2d) {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("Kingdom/Vecna Bold.otf"))).deriveFont(Font.PLAIN, 20);
            font2 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("Kingdom/Vecna Bold.otf"))).deriveFont(Font.PLAIN, 15);
        } catch (IOException e) {
        } catch (FontFormatException e) {
        }
        //player image flashes so player knows they have lost a life
        if (playerHit) {
            now = System.currentTimeMillis();
            if (duration >= 15) {
                duration = 0;
                playerHit = false;
            }
        } else {
            lastFlash = 0;
            now = 300;
        }

        g2d.setColor(Color.WHITE);
        //drawing the labels at the top of the screen
        g2d.setFont(font);
        g2d.drawImage(heart.getImage(), 10, 12, null);
        g2d.drawString(Integer.toString(numLives), 30, 25);
        g2d.drawImage(bomb.getImage(), 750, 12, null);
        g2d.drawString(Integer.toString(numBombs), 775, 30);
        g2d.drawImage(keyblade.getImage(), 700, 10, null);
        g2d.drawString(Integer.toString(fireballLevel), 730, 30);

        g2d.setFont(font2);
        g2d.drawString("LEVEL " + level, 50, 25);
        if (now - lastFlash >= 150) {
            g2d.drawImage(getPlayerImage(), getXPos(), getYPos(), null);
            if (playerHit) {
                duration += 1;
                lastFlash = now;
            }
        }

        //level up icon appears for 2 seconds so player knows they have levelled up
        if (lastLevel < level) {
            g2d.drawImage(levelUp.getImage(), x - levelUp.getIconWidth() / 4, y - levelUp.getIconHeight(), null);
            levelNow = System.currentTimeMillis();
            if (levelNow - levelLast >= 2000) {
                lastLevel = level;
            }
        }

    }

    //override
    public void setImage() {
    }

    //returns player icon
    public ImageIcon getPlayerIcon() {
        return icon;
    }

    //returns image of player icon
    public Image getPlayerImage() {
        return icon.getImage();
    }

    //sets speed of player 
    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    //sets player level
    public void setLevel(int level) {
        this.level = level;
    }

    //returns player level
    public int getLevel() {
        return this.level;
    }

    //creates rectangle object for detecting collision
    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    //when player kills entire wave, variable set to true
    public void setWaveKilled() {
        waveKilled = true;
    }

    //checks if the player killed the entire wave
    public boolean getWaveKilled() {
        return waveKilled;
    }

    //increases when player kills enemy
    public void incNumKilled() {
        numKilled++;
    }

    //3 second delay, a player can be hit and lose a life and be immune for 3 seconds
    public void decrementLives(long now) {
        if (now - lastCollision >= 3000) {
            numLives--;
            lastCollision = now;
            playerHit = true;
            Sound.HIT.playSoundEffect();
        }
    }

    //creates enemy waves
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

        //when player level greater than 6, different waves are randomized
        movement = level >= 6 ? rand.nextInt(5) + 1 : level;
        //boss at the end of each level
        movement = boss == true ? 6 : movement;

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
                        xVert = rand.nextInt(500) + 100;
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
                    xVert = rand.nextInt(300);
                    yVert -= 200;
                    objects.add(new EnemyLvl3(xVert, yVert, value));
                    objects.add(new EnemyLvl3(panel.getWidth() - icon.getIconWidth() - 100 - xVert, yVert, value));
                }
                break;
            case 5:
                for (int i = 0; i < killCap; i++) {
                    type = rand.nextInt(3) + 1;
                    xPos = 0;
                    //randomizes enemies from each level in one wave
                    if (type == 1) {
                        value = rand.nextInt(3) + 1;
                        xPos -= 200;
                        yPos -= 150;
                        objects.add(new EnemyLvl1(xPos, yPos, value));
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
                    } else if (type == 3) {
                        xVert = rand.nextInt(600) + 100;
                        yVert -= 200;
                        objects.add(new EnemyLvl3(xVert, yVert, value));
                    }
                }
                break;
            case 6:
                objects.add(new Boss(panel.getWidth() / 2 - 100, -700, incLives));
                //boss level gets harder with each level
                incLives += 60;

                xPos = 0;
                for (int i = 0; i < killCap - 1; i++) {
                    value = rand.nextInt(3) + 1;
                    xPos -= 200;
                    yPos -= 150;
                    objects.add(new EnemyLvl1(xPos, yPos, value));
                }
                boss = false;
                break;
        }
    }
    

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

        //add a level up icon so player knows they levelled up
        //player levels up with each increment in 10000 points
        if (score.getScore() >= levelUpCap) {
            level++;
            levelLast = System.currentTimeMillis();
            boss = true;
            levelUpCap += level * 10000;
        }

        //checks if player collides with fireball or enemy object and decrements life
        for (GameObject object : objects) {
            if (checkCollision(object) && (object instanceof Enemy || object instanceof EnemyFireBall)) {
                decrementLives(System.currentTimeMillis());
            }
        }
        //ends game and sends to game over screen
        if (numLives <= 0) {
            panel.state = State.GAME_OVER;
        }
    }
}
