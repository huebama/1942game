package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Background {
    
    private ImageIcon icon = new ImageIcon("Sprites/black dragon.png");
    private int x;
    private int y;
    private int x2;
    private int y2;
    boolean run;
    
    public Background(int x, int y, int x2, int y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public void paint(Graphics2D g2d) {
        g2d.drawImage(getBackgroundImage(), x, y, null);  
        g2d.drawImage(getBackgroundImage(), x2, y2, null);
    }

    public Image getBackgroundImage() {
        return icon.getImage();
    }
    
    public void update(ControlPanel panel) {
        y -= 1;
    }
}
