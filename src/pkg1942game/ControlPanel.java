package pkg1942game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements Runnable {

    private JFrame frame = new JFrame();
    public static State state = State.MENU;
    protected boolean mouse = false;
    private Menu menu = new Menu();
    private ChooseMenu chooseMenu = new ChooseMenu();
    private GameOverMenu gameOverMenu = new GameOverMenu();
    private HelpMenu helpMenu = new HelpMenu();
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<GameObject> delete = new ArrayList<GameObject>();
    private Player player;
    private Score score;
    private boolean run = false;
    private boolean clickedR = false;
    private boolean clickedL = false;
    private boolean clickedU = false;
    private boolean clickedD = false;
    private int positioning = 0;
    private Background background = new Background(0, 0);
    private JLabel scoreLabel = new JLabel("");
    private Font font;

    public ControlPanel() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("Kingdom/Vecna Bold.otf"))).deriveFont(Font.PLAIN, 15);
        } catch (IOException e) {
        } catch (FontFormatException e) {
        }
        init();

        scoreLabel.setFont(font);
        scoreLabel.setForeground(Color.WHITE);
        this.add(scoreLabel);

        this.setBackground(Color.WHITE);

        frame.addKeyListener(new KeyControl(this));
        this.requestFocus(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);
        this.setPreferredSize(new Dimension(800, 700));
        frame.pack();
        frame.addMouseMotionListener(new MouseControl(this));
        frame.addMouseListener(new MouseControl(this));

        start();
    }

    //initializes variables for a new game
    public void init() {
        player = new Player(350, 500, 0, 0);
        score = new Score(0);
        objects.clear();
    }

    //paints different screens depending on state enum
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (state == State.MENU) {
            Sound.MENU.playMusic();
            menu.paint(g2d, this);
        } else if (state == State.CHOOSE) {
            chooseMenu.paint(g2d);
        } else if (state == State.GAME_OVER) {
            Sound.BACKGROUND.stopMusic();
            Sound.GAME_OVER.playMusic();
            scoreLabel.setText("");
            gameOverMenu.paint(g2d, score, this);
        } else if (state == State.HELP) {
            helpMenu.paint(g2d);
        } else if (state == State.GAME) {
            background.paint(g2d);
            this.add(scoreLabel);
            player.paint(g2d);
            scoreLabel.setText("SCORE: " + score.getScore());
            for (GameObject object : objects) {
                object.paint(g2d);
            }
            Sound.MENU.stopMusic();
            Sound.BACKGROUND.playMusic();
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
                if (state == State.GAME) {
                    player.update(this, objects, score, delete, player);
                    for (GameObject object : objects) {
                        if (!(object instanceof Enemy) && (object.getXPos() < 0 || object.getXPos() > frame.getWidth() || object.getYPos() < 0 || object.getYPos() > frame.getHeight())) {
                            delete.add(object);
                        }
                        object.update(this, objects, score, delete, player);
                    }
                    objects.removeAll(delete);
                    background.update();
                }
                repaint();
                Thread.sleep(15);
            } catch (Exception e) {
            }
        }
    }

    //detects keyboard input
    public void keyPressed(KeyEvent e) {
        if (!mouse) {
            int key = e.getKeyCode();
            if (state == State.GAME) {
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
                    case KeyEvent.VK_SPACE:
                        shootFireball();
                        break;
                    case KeyEvent.VK_B:
                        shootBomb();
                        break;
                }
            } else {
                //add any startpage key presses if needed? 
            }
        }
    }

    public void shootBomb() {
        //makes sure player has enough bombs in inventory
        if (player.getBombs() > 0) {
            positioning = player.getPlayerIcon().getIconWidth() / 2;
            objects.add(new Bomb(player.getXPos() + positioning, player.getYPos() - 20, 10));
            player.loseBomb();
            Sound.BOMB.playSoundEffect();
        }
    }

    //shoots fireballs based on the player fireball level - 5 being max level
    public void shootFireball() {
        positioning = player.getPlayerIcon().getIconWidth() / 2;

        if (player.getFireballLevel() == 1) {
            //take out the number 20 and replace with variable that can be changed
            objects.add(new FireBall(player.getXPos() + positioning, player.getYPos() - 20, 10));
            //THIS BLOCK OF CODE SHOULD ACTUALLY BE FOR POWERUPS --> if a certain powerup is added, player gunfire is increased
        } else if (player.getFireballLevel() == 2) {
            objects.add(new FireBall(player.getXPos() + positioning, player.getYPos() - 20, 10));
            objects.add(new FireBall(player.getXPos() + 25 + positioning, player.getYPos() - 20, 10));
        } else if (player.getFireballLevel() == 3) {
            objects.add(new FireBall(player.getXPos() + positioning, player.getYPos() - 20, 10));
            objects.add(new FireBall(player.getXPos() + positioning + 15, player.getYPos() - 20, 10));
            objects.add(new FireBall(player.getXPos() + positioning - 15, player.getYPos() - 20, 10));
        } else if (player.getFireballLevel() == 4) {
            objects.add(new FireBall(player.getXPos() + positioning + 5, player.getYPos() - 20, 10));
            objects.add(new FireBall(player.getXPos() + positioning + 15, player.getYPos() - 20, 10));
            objects.add(new FireBall(player.getXPos() + positioning - 15, player.getYPos() - 20, 10));
            objects.add(new FireBall(player.getXPos() + positioning - 5, player.getYPos() - 20, 10));
        } else {
            objects.add(new FireBall(player.getXPos() + positioning, player.getYPos() - 20, 10));
            objects.add(new FireBall(player.getXPos() + positioning + 15, player.getYPos() - 20, 10));
            objects.add(new FireBall(player.getXPos() + positioning - 15, player.getYPos() - 20, 10));
            objects.add(new FireBall(player.getXPos() + positioning + 30, player.getYPos() - 20, 10));
            objects.add(new FireBall(player.getXPos() + positioning - 30, player.getYPos() - 20, 10));
        }
        Sound.SHOOT.playSoundEffect();
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

    //controls mouse shooting 
    public void mousePressed(MouseEvent e) {
        if (mouse) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                shootFireball();
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                shootBomb();
            }
        }
    }

    //allows player to play game using the mouse
    //changes the cursor to hand cursor when hovering over a button 
    public void mouseMoved(MouseEvent e) {
        if (state == State.GAME && mouse) {
            player.setXPos(e.getX());
            player.setYPos(e.getY());
        }

        if (state == State.MENU) {
            if ((e.getX() >= 25 && e.getX() <= 800) && ((e.getY() >= 425 && e.getY() <= 475) || (e.getY() >= 500 && e.getY() <= 550) || (e.getY() >= 575 && e.getY() <= 625))) {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        } else if (state == State.CHOOSE) {
            if (((e.getY() >= 270 && e.getY() <= 470) && ((e.getX() >= 75 && e.getX() <= 275) || (e.getX() >= 525 && e.getX() <= 725))) || (e.getX() >= 10 && e.getX() <= 60 && e.getY() >= 10 && e.getY() <= 60)) {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        } else if (state == State.HELP) {
            if (e.getX() >= 10 && e.getX() <= 60 && e.getY() >= 10 && e.getY() <= 60) {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        } else if (state == State.GAME_OVER) {
            if ((e.getX() >= 0 && e.getX() <= 780) && ((e.getY() >= 610 && e.getY() <= 640) || e.getY() >= 670 && e.getY() <= 700)) {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    public static void main(String[] args) {
        ControlPanel controlPanel = new ControlPanel();

    }

}
