package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class EnemyLvl1 extends Enemy {

    private ImageIcon icon = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/black dragon_01.png");
    private ImageIcon iconBack = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/black dragon_12.png");
    private boolean forward = true;
    private int num = 1;
    private boolean hit = false;
    private int score = 0;
    private int movement;
    private int orgX;
    private int orgY;

    //ADD PARAMETER INT MOVEMENT once the spawn method in Enemy is made
    public EnemyLvl1(int x, int y, int movement) {
        super(x, y, 5, 5);
        this.movement = movement;
        orgX = x;
        orgY = y;
    }

    public Image getEnemyImage() {
        return forward ? icon.getImage() : iconBack.getImage();
    }

    public void setImage() {
        if (forward) {
            icon = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/player fire_0" + num + ".png");
        } else {
            iconBack = new ImageIcon("/Users/stephaniegu/Desktop/Sprites/player fire_0" + num + ".png");
        }
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getEnemyImage(), getXPos(), getYPos(), null);
    }

    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        //add switch statement with different enemy path options with variable movement
        //need to add spawning, randomized spawning from top or side of page (spawning based on which enemy path as well)
        switch (movement) {
            case 1:
                x += xSpeed;
                y += ySpeed;
                break;
            case 2:
                x += xSpeed;
                y += ySpeed - 1;
                break;
            case 3:
                x += xSpeed - 1;
                y += ySpeed;
                break;
            case 4:
                x -= xSpeed;
                y += ySpeed;
                break;
            case 5:
                x -= xSpeed;
                y += ySpeed - 1;
                break;
            case 6:
                x -= xSpeed;
                y += ySpeed - 2;
                break;
        }
        //can reduce this
        if (movement <= 3) {
            if (x > panel.getWidth() || y > panel.getHeight()) {
                x = orgX;
                y = orgY;
            }
        } else {
            if (x < 0 - icon.getIconWidth()|| y > panel.getHeight()) {
                x = orgX;
                y = orgY;
            }
        }

        //HAVE TO CHANGE POSITION OF THIS CODE BELOW (different enemy objects, so would have to repeat code for every enemy if placed here)
        //checks if fireball hits enemy or player object 
        for (GameObject check : objects) {
            if (checkCollision(check) && (check instanceof FireBall || check instanceof Bomb)) {
                this.setXSpeed(0);
                this.setYSpeed(0);
                check.setImage();
                check.setXSpeed(0);
                check.setYSpeed(0);
                this.setImage();
                hit = true;
                score.setScore(100);
            }
        }
        if (hit) {
            num = num < 10 ? num + 1 : 100;
            if (num == 100) {
                player.incNumKilled();
                delete.add(this);
            }
            this.setImage();
        }
    }
}
