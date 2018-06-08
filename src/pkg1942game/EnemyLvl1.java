package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class EnemyLvl1 extends Enemy {

    private ImageIcon icon = new ImageIcon("Kingdom/enemy_1.png");
    private int num = 1;
    private int movement;
    private int lives = 1;

    public EnemyLvl1(int x, int y, int movement) {
        super(x, y, 5, 5);
        this.movement = movement;
    }

    public int getLives() {
        return lives;
    }

    //decreases enemy lives
    public void setLives() {
        lives--;
    }

    public Image getEnemyImage() {
        return icon.getImage();
    }

    //animates the enemy vanishing
    public void setImage() {        
        icon = new ImageIcon("Kingdom/player fire_0" + num + ".png");
    }
 
    //creates rectangle for the icon so collisions can be detected
    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getEnemyImage(), getXPos(), getYPos(), null);
    }

    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        //based on the randomized movement variable, enemy moves in different paths
        switch (movement) {
            //all cases move diagonally at different speeds or from different directions
            case 1:
                x += xSpeed;
                y += ySpeed;
                break;
            case 2:
                x += xSpeed;
                y += ySpeed - 1;
                break;
            case 3:
                x += xSpeed;
                y += ySpeed - 2;
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

        if (movement <= 3) {
            if (x > panel.getWidth() || y > panel.getHeight()) {
                x = y = -500;
            }
        } else {
            if (x < 0 - icon.getIconWidth() || y > panel.getHeight()) {
                x = panel.getWidth() + 500;
                y = -500;
            }
        }

        checkHit(objects, delete, score, this, player);
    }
}
