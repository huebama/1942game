package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class FireBallUp extends PowerUp {

    private ImageIcon icon = new ImageIcon("Kingdom/keyblade_01.png");

    public FireBallUp(int x, int y) {
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

    //when player collides with this powerup, level up weapon 
    public void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player) {
        if (checkCollision(player)) {
            setImage();
            player.setFireballLevel();
            delete.add(this);
            Sound.FIREBALL_PWRUP.playSoundEffect();
        }
    }
}
