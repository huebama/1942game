package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class PlusBombs extends PowerUp {

    private ImageIcon icon = new ImageIcon("Kingdom/keyblade_16.png");

    public PlusBombs(int x, int y) {
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

    //if player collides with powerup, gain 3 bombs
    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        if (checkCollision(player)) {
            setImage();
            player.gainBomb(3);
            delete.add(this);
            Sound.BOMB_PWRUP.playSoundEffect();
        }
    }
}
