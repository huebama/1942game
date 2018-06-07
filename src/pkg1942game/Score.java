package pkg1942game;

import java.awt.Graphics2D;

//write a change listener for a Score, everytime the score value is changed, update the text in a JLabel?

public class Score {

    int score;

    public Score(int score) {
        this.score = score;
    }

    public void setScore(int add) {
        this.score += add;
    }
    
    public int getScore() {
        return score;
    }

    public void paint(Graphics2D g2d) {
        //something about this statement is messing the other paints up....
        //g2d.drawString(Integer.toString(score), 50, 50);
    }
}
