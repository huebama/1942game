package pkg1942game;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class GameObject {

    protected int x;
    protected int y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getXPos() {
        return this.x;
    }

    public int getYPos() {
        return this.y;
    }

    public void setXPos(int x) {
        this.x = x;
    }

    public void setYPos(int y) {
        this.y = y;
    }

    public boolean checkCollision(GameObject object) {
        return getRectangle().intersects(object.getRectangle().getBounds());
    }

    public abstract Rectangle2D getRectangle();

    public abstract void setXSpeed(double xSpeed);

    public abstract void setYSpeed(double xSpeed);

    public abstract void paint(Graphics2D g2d);

    public abstract void update(ControlPanel panel, GameObject object, ArrayList<GameObject> objects);
}
