package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

   //fix this so the height and width values aren't hardcoded (the first 2 parameters)
    private Rectangle play = new Rectangle(300, 300, 200, 50);
    private Rectangle quit = new Rectangle(300, 410, 200, 50);
    private Rectangle rules = new Rectangle(300, 520, 200, 50);

    public void paint(Graphics2D g2d) {
        //maybe make a title on a different app and save as an image and g2d.drawImage(...)??        
        
        g2d.drawString("PLAY", 400, 325);

        g2d.draw(play);
        g2d.draw(quit);
        g2d.draw(rules);
    }
    
    
}
