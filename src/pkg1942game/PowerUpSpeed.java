package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class PowerUpSpeed extends PowerUp {

    private ImageIcon icon = new ImageIcon("Sprites/black dragon_01.png");
    private ImageIcon iconBack = new ImageIcon("Sprites/black dragon_12.png");
    private boolean forward = true;

    public PowerUpSpeed(int x, int y) {
        super(x, y, 6, 6);
    }

    public Image getPowerUpImage() {
        return forward ? icon.getImage() : iconBack.getImage();
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getPowerUpImage(), getXPos(), getYPos(), null);
    }

    public void update(ControlPanel panel, GameObject object, ArrayList<GameObject> objects) {
        //left or right wall impact
        if (x < 0 || x > panel.getWidth() - 60) {
            setXSpeed(-getXSpeed());
            x = x < 0 ? 0 : panel.getWidth() - 60;
        }
        //top wall or bottom section impact
        if (y < 0 || y > panel.getHeight() - 200) {
            setYSpeed(-getYSpeed());
            //changes direction of enemy photo
            forward = y < 0 ? true : false;
            y = y < 0 ? 0 : panel.getHeight() - 200;
        }
        x += xSpeed;
        y += ySpeed;
    }
}
