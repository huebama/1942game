package pkg1942game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;

public class HelpMenu {

    private ImageIcon keybladeUp = new ImageIcon("Kingdom/keyblade_01.png");
    private ImageIcon plusBombs = new ImageIcon("Kingdom/keyblade_16.png");
    private ImageIcon plusLife = new ImageIcon("Kingdom/keyblade_11.png");
    private ImageIcon enemy1 = new ImageIcon("Kingdom/enemy_1.png");
    private ImageIcon enemy2 = new ImageIcon("Kingdom/pirate_03.png");
    private ImageIcon enemy3 = new ImageIcon("Kingdom/big_13.png");
    private ImageIcon boss = new ImageIcon("Kingdom/boss resized.png");
    private ImageIcon playerBall = new ImageIcon("Kingdom/attack_175.png");
    private ImageIcon enemyBall = new ImageIcon("Kingdom/attack_78.png");
    private ImageIcon bomb = new ImageIcon("Kingdom/attack_155.png");
    private ImageIcon bg = new ImageIcon("Kingdom/riku.png");
    private ImageIcon bg2 = new ImageIcon("Kingdom/white.jpg");
    private ImageIcon backspace = new ImageIcon("Kingdom/back.png");

    private Font font;

    public void paint(Graphics2D g2d) {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("Kingdom/Vecna Bold.otf"))).deriveFont(Font.PLAIN, 20);
            g2d.setFont(font);
        } catch (FontFormatException e) {
        } catch (IOException e) {
        }

        g2d.drawImage(bg2.getImage(), 0, 0, null);
        g2d.drawImage(bg.getImage(), 400, 400, null);
        g2d.drawImage(backspace.getImage(), 10, 0, null);

        g2d.drawString("P O W E R  U P S", 120, 50);
        g2d.drawImage(keybladeUp.getImage(), 50, 70, null);
        g2d.drawString("powers up the keyblade (shooting)", 80, 90);
        g2d.drawImage(plusBombs.getImage(), 50, 120, null);
        g2d.drawString("adds three bombs to inventory", 80, 140);
        g2d.drawImage(plusLife.getImage(), 50, 170, null);
        g2d.drawString("adds one life", 80, 190);

        g2d.drawString("E N E M I E S", 140, 250);
        g2d.drawImage(enemy1.getImage(), 50, 270, null);
        g2d.drawString("level one", 110, 290);
        g2d.drawString("one health", 110, 310);
        g2d.drawImage(enemy2.getImage(), 45, 350, null);
        g2d.drawString("level two", 110, 370);
        g2d.drawString("three health", 110, 390);
        g2d.drawImage(enemy3.getImage(), 40, 420, null);
        g2d.drawString("level three", 110, 440);
        g2d.drawString("five health", 110, 460);
        g2d.drawString("shoots dark balls", 110, 480);
        g2d.drawImage(boss.getImage(), 10, 520, null);
        g2d.drawString("boss", 150, 540);
        g2d.drawString("one at end of each level", 150, 560);
        g2d.drawString("increasing health with level", 150, 580);
        g2d.drawString("shoots dark balls", 150, 600);

        g2d.drawString("E N T I T I E S", 450, 50);
        g2d.drawImage(playerBall.getImage(), 420, 70, null);
        g2d.drawString("player fire", 450, 82);
        g2d.drawImage(enemyBall.getImage(), 400, 100, null);
        g2d.drawString("enemy fire (dark balls)", 450, 120);
        g2d.drawImage(bomb.getImage(), 410, 150, null);
        g2d.drawString("player bombs", 450, 160);
        g2d.drawString("worth five damage", 450, 180);

        g2d.drawString("C O N T R O L S", 450, 250);
        g2d.drawString("keyboard", 450, 280);
        g2d.drawString("mouse", 600, 280);
        g2d.drawString("movement", 320, 310);
        g2d.drawString("ARROW KEYS", 450, 310);
        g2d.drawString("FOLLOW MOUSE", 600, 310);
        g2d.drawString("shooting", 320, 350);
        g2d.drawString("SPACE", 450, 350);
        g2d.drawString("LEFT CLICK", 600, 350);
        g2d.drawString("fire bombs", 320, 400);
        g2d.drawString("B", 450, 400);
        g2d.drawString("RIGHT CLICK", 600, 400);
        

    }
}
