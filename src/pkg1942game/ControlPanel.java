package pkg1942game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements Runnable {

    private JFrame frame = new JFrame();
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<GameObject> delete = new ArrayList<GameObject>();
    private Player player = new Player(350, 500, 0, 0);
    private Score score = new Score(0);
    private boolean run = false;
    private boolean clickedR = false;
    private boolean clickedL = false;
    private boolean clickedU = false;
    private boolean clickedD = false;
    private int positioning = 0;
    private JLabel scoreLabel = new JLabel(Integer.toString(score.getScore()));

    public ControlPanel() {
        //objects.add(new EnemyLvl1(0, 0));
        //objects.add(new EnemyLvl1(400, 0));
        //objects.add(new EnemyLvl2(0, 0));
        this.add(scoreLabel);

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
        score.paint(g2d);
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
            try { //moved some code into try to prevent some threading exceptions
                player.update(this, objects, score, delete, player);
                for (GameObject object : objects) {
                    if (!(object instanceof Enemy) && (object.getXPos() < 0 || object.getXPos() > frame.getWidth() || object.getYPos() < 0 || object.getYPos() > frame.getHeight())) {
                        delete.add(object);
                    }
                    object.update(this, objects, score, delete, player);
                }
                scoreLabel.setText(Integer.toString(score.getScore()));
                objects.removeAll(delete);
                repaint();
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
                clickedD = true;
                break;
            case KeyEvent.VK_UP:
                player.setYSpeed(-5);
                clickedU = true;
                break;
            case KeyEvent.VK_RIGHT:
                player.setXSpeed(5);
                clickedR = true;
                break;
            case KeyEvent.VK_LEFT:
                player.setXSpeed(-5);
                clickedL = true;
                break;
            //adjust this based on the player level (higher levels shoot more fireballs, positioning is different)
            case KeyEvent.VK_SPACE:
                if (player.getLevel() == 1) {
                    positioning = player.getPlayerIcon().getIconWidth() / 2;
                    //take out the number 20 and replace with variable that can be changed
                    objects.add(new FireBall(player.getXPos() + positioning, player.getYPos() - 20, 0, 10, false, 1));
                } else if (player.getLevel() == 2) {
                    positioning = player.getPlayerIcon().getIconWidth() / 4;
                    objects.add(new FireBall(player.getXPos() + positioning, player.getYPos() - 20, 0, 10, false, 2));
                    objects.add(new FireBall(player.getXPos() + 25 + positioning, player.getYPos() - 20, 0, 10, false, 2));
                }
                break;
            case KeyEvent.VK_C:
                positioning = player.getPlayerIcon().getIconWidth() / 2;
                objects.add(new Bomb(player.getXPos() + positioning, player.getYPos() - 20, 10));

        }
    }

    //detects if a key is released and stops movement
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        //clicked booleans reduces lag with player movement
        if (key == KeyEvent.VK_DOWN) {
            player.setYSpeed(clickedU ? -5 : 0);
            clickedD = false;
        } else if (key == KeyEvent.VK_UP) {
            player.setYSpeed(clickedD ? 5 : 0);            
            clickedU = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            player.setXSpeed(clickedL ? -5 : 0);
            clickedR = false;
        } else if (key == KeyEvent.VK_LEFT) {
            player.setXSpeed(clickedR ? 5 : 0);
            clickedL = false;
        }
    }

    public static void main(String[] args) {
        ControlPanel controlPanel = new ControlPanel();

    }

}
