package pkg1942game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class PowerUp extends GameObject {

    public PowerUp(int x, int y) {
        super(x, y);  
    }
    
    public abstract Image getPowerUpImage();
    
    public abstract void setImage();
    
    public abstract Rectangle2D getRectangle();
    
    public abstract void setXSpeed(double xSpeed);
    
    public abstract void setYSpeed(double ySpeed);
    
    public abstract void paint(Graphics2D g2d);
    
    public abstract void update(ControlPanel panel, ArrayList<GameObject> objects, Score score, ArrayList<GameObject> delete, Player player);
}
