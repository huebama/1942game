package pkg1942game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;

//need to fix State.GAME to reset 

public class GameOverMenu {

    private ImageIcon icon = new ImageIcon("Kingdom/game over_02.png");
    private ImageIcon heart = new ImageIcon("Kingdom/goheart.png");
    private ImageIcon bg = new ImageIcon("Kingdom/bg_2.jpg");
    private Font font;
    private Font font2;
    private Font font3;

    public void paint(Graphics2D g2d, Score score, ControlPanel panel) {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("Kingdom/Vecna Bold.otf"))).deriveFont(Font.PLAIN, 100);
            g2d.setFont(font);
            font2 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("Kingdom/Vecna Bold.otf"))).deriveFont(Font.PLAIN, 70);
            font3 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("Kingdom/Vecna Bold.otf"))).deriveFont(Font.PLAIN, 40);
        } catch (FontFormatException e) {
        } catch (IOException e) {
        }
        g2d.setColor(Color.DARK_GRAY);

        int width = g2d.getFontMetrics().stringWidth(Integer.toString(score.getScore()));

        g2d.drawImage(bg.getImage(), 0, 0, null);
        g2d.drawImage(icon.getImage(), 100, 150, null);
        g2d.drawImage(heart.getImage(), 325, 25, null);
        g2d.drawString("g a m e  o v e r", 55, 450);
        g2d.setFont(font2);
        g2d.drawString(Integer.toString(score.getScore()), panel.getWidth() / 2 - width / 2, 550);
        g2d.setFont(font3);
        g2d.drawString("p l a y  a g a i n", 490, 610);
        g2d.drawString("q u i t", 670, 670);                
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0, 620, 780, 620);
        g2d.drawLine(0, 680,780, 680);
    }
}
