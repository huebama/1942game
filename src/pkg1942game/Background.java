package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Background {

    private ImageIcon icon = new ImageIcon("Kingdom/sky2.v1.jpg");
    private ImageIcon icon2 = new ImageIcon("Kingdom/sky3.v1.jpg");
    private int x;
    private int y;
    private int y2 = -icon.getIconHeight();
    private int y3 = 2 * -icon.getIconHeight();

    public Background(int x, int y) {
        this.x = 0;
        this.y = 0;
    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(getBackgroundImage(icon2), x, y, null);
        g2d.drawImage(getBackgroundImage(icon), x, y2, null);
        g2d.drawImage(getBackgroundImage(icon2), x, y3, null);
    }

    public Image getBackgroundImage(ImageIcon icon) {
        return icon.getImage();
    }

    public void update() {
        
        y += 5;
        y2 += 5;
        y3 += 5;
        if (y >= 2 * icon.getIconHeight()) {
            y = 0;
        }
        if (y2 >= icon.getIconHeight()) {
            y2 = -icon.getIconHeight();
        }
        if (y3 >= 0) {
            y3 = 2 * -icon.getIconHeight();
        }
    }
}
