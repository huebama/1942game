package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Player extends GameObject {

    private int numLives;
    private double xSpeed;
    private double ySpeed;
    private int level;
    private ImageIcon icon = new ImageIcon("Sprites/orange dragon_16.png");

    public Player(int x, int y, double xSpeed, double ySpeed) {
        super(x, y);
        numLives = 3;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        level = 1;
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getPlayerImage(), getXPos(), getYPos(), null);
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

    //
    public void update(ControlPanel panel, GameObject object, ArrayList<GameObject> objects) {
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
    }
}
