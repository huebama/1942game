package pkg1942game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;

public class ChooseMenu {

    private Rectangle mouse = new Rectangle(75, 250, 200, 200);
    private Rectangle keyboard = new Rectangle(525, 250, 200, 200);
    private ImageIcon bg = new ImageIcon("Kingdom/cover.png");
    private ImageIcon backspace = new ImageIcon("Kingdom/back.png");

    public void paint(Graphics2D g2d) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("Kingdom/Vecna Bold.otf"))).deriveFont(Font.PLAIN, 30);
            g2d.setFont(font);
        } catch (FontFormatException e) {
        } catch (IOException e) {
        }
        g2d.setColor(Color.darkGray);
        g2d.drawImage(bg.getImage(), 0, -25, null);
        g2d.draw(mouse);
        g2d.draw(keyboard);
        g2d.drawString("MOUSE", 125, 360);
        g2d.drawString("KEYBOARD", 550, 360);
        g2d.drawImage(backspace.getImage(), 10, 0, null);

    }
}
