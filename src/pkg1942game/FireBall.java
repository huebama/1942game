
package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class FireBall extends GameObject {

    private double ySpeed;
    private ImageIcon icon = new ImageIcon("Kingdom/attack_175.png");

    public FireBall(int x, int y, double ySpeed) {
        super(x, y);
        this.ySpeed = ySpeed;
    }

    public Image getFireBallImage() {
        return icon.getImage();
    }

    public void setImage() {
        icon = new ImageIcon("");
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    //override
    public void setXSpeed(double xSpeed) {
    }

    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    //paints image to screen
    public void paint(Graphics2D g2d) {
        g2d.drawImage(getFireBallImage(), getXPos(), getYPos(), null);
    }

    //add varying movement for if boolean enemy = true
    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        y -= ySpeed;
    }
}
