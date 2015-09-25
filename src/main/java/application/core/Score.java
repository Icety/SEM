package application.core;

import java.io.Serializable;

/**
 * Class Score
 * Created by Daphne van Tetering on 25-9-2015.
 */
public class Score implements Serializable{
    protected int tScore;
    protected String tPlayer;

    /**
     * Constructor
     * @param player name of player the score belongs to
     * @param score score
     */
    public Score(String player, int score){
        tPlayer = player;
        tScore = score;
    }


    /**
     * method to get score-value of a Score
     * @return score
     */
    public int getScore() {
        return tScore;
    }

    /**
     * method to get name of player which the Score belongs to
     * @return name of player
     */
    public String getPlayer() {
        return tPlayer;
    }
}

