package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class PlusLife extends PowerUp {

    private ImageIcon icon = new ImageIcon("Kingdom/keyblade_11.png");

    public PlusLife(int x, int y) {
        super(x, y);
    }

    public Image getPowerUpImage() {
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
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getPowerUpImage(), getXPos(), getYPos(), null);
    }

    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        if (checkCollision(player)) {
            setImage();
            player.gainLives(1);
            delete.add(this);
            Sound.LIFE_PWRUP.playSoundEffect();
        }
    }
}
