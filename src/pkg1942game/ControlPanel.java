package pkg1942game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements Runnable {

    private JFrame frame = new JFrame();
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<GameObject> delete = new ArrayList<GameObject>();
    private Player player = new Player(350, 500, 0, 0);
    private boolean run = false;
    int positioning = 0;

    public ControlPanel() {
        objects.add(new EnemyLvl1(0, 0));

        frame.addKeyListener(new KeyControl(this));
        this.requestFocus(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);
        this.setPreferredSize(new Dimension(800, 700));
        frame.pack();
        start();
    }

    //paints image 
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        player.paint(g2d);
        for (GameObject object : objects) {
            object.paint(g2d);
        }
    }

    //creates the thread object
    public void start() {
        Thread thread = new Thread(this);
        run = true;
        thread.start();
    }

    //stops thread
    public void stop() {
        run = false;
    }

    //updates animations
    public void run() {
        while (run) {
            for (GameObject object : objects) {
                if (!(object instanceof Enemy) && (object.getXPos() < 0 || object.getXPos() > frame.getWidth() || object.getYPos() < 0 || object.getYPos() > frame.getHeight())) {
                    //delete.add(object);
                }
                object.update(this, object, objects);
                player.update(this, object, objects);
            }
            //objects.removeAll(delete);
            repaint();
            try {
                Thread.sleep(15);
            } catch (Exception e) {
            }
        }
    }

    //detects keyboard input
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_DOWN:
                player.setYSpeed(5);
                break;
            case KeyEvent.VK_UP:
                player.setYSpeed(-5);
                break;
            case KeyEvent.VK_RIGHT:
                player.setXSpeed(5);
                break;
            case KeyEvent.VK_LEFT:
                player.setXSpeed(-5);
                break;
            //adjust this based on the player level (higher levels shoot more fireballs, positioning is different)
            case KeyEvent.VK_SPACE:
                if (player.getLevel() == 1) {
                    positioning = 25;
                    objects.add(new FireBall(player.getXPos() + positioning, player.getYPos() - 20, 0, 10, false, 1));
                } else if (player.getLevel() == 2) {
                    positioning = 10;
                    objects.add(new FireBall(player.getXPos() + positioning, player.getYPos() - 20, 0, 10, false, 2));
                    objects.add(new FireBall(player.getXPos() + 25 + positioning, player.getYPos() - 20, 0, 10, false, 2));
                }
                break;
        }
    }

    //detects if a key is released and stops movement
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP) {
            player.setYSpeed(0);
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            player.setXSpeed(0);
        }
    }

    public static void main(String[] args) {
        ControlPanel controlPanel = new ControlPanel();

    }

}
