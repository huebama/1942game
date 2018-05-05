package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Background {
    
    private ImageIcon icon = new ImageIcon("Sprites/black dragon.png");
    private int x;
    private int y;
    
    public Background(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void paint(Graphics2D g2d) {
        g2d.drawImage(getBackgroundImage(), x, y, null);
    }

    public Image getBackgroundImage() {
        return icon.getImage();
    }
    
    public void update(ControlPanel panel) {
        x += 1;
        y += 1;
    }
}
