package pkg1942game;

import java.awt.Graphics2D;

public class Score {

    int score;

    public Score(int score) {
        this.score = score;
    }

    //adds to score value
    public void setScore(int add) {
        this.score += add;
    }
    
    public int getScore() {
        return score;
    }
}
