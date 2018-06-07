package pkg1942game;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;

public class Menu {        

    private ImageIcon icon = new ImageIcon("Kingdom/sora.png");
    private ImageIcon icon2 = new ImageIcon("Kingdom/kh.png");
    private ImageIcon bg = new ImageIcon("Kingdom/white.jpg");
    private Font font;

    public void paint(Graphics2D g2d, ControlPanel panel) {
        //maybe make a title on a different app and save as an image and g2d.drawImage(...)?? 
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("Kingdom/Vecna Bold.otf"))).deriveFont(Font.PLAIN, 30);
            g2d.setFont(font);
        } catch (FontFormatException e) {
        } catch (IOException e) {
        }
        
        g2d.drawImage(bg.getImage(), 0, 0, null);

        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(25, 450, 800, 450);
        g2d.drawLine(25, 525, 800, 525);
        g2d.drawLine(25, 600, 800, 600);

        g2d.drawImage(icon.getImage(), 300, 50, null);
        g2d.drawImage(icon2.getImage(), 5, -20, null);
        g2d.drawString("F L I G H T", 360, 230);

        g2d.drawString("P L A Y", 25, 435);
        g2d.drawString("Q U I T", 25, 510);
        g2d.drawString("H E L P", 25, 585);
    }

}
