package application.core;

import java.io.Serializable;

/**
 * Class for Score.
 * @author Daphne van Tetering.
 */
public class Score implements Serializable{
    protected int tScore;
    protected String tPlayer;

    /**
     * Constructor for Score.
     * @param player name of player the score belongs to.
     * @param score score.
     */
    public Score(String player, int score){
        tPlayer = player;
        tScore = score;
    }


    /**
     * Method to get score-value of a Score.
     * @return score.
     */
    public int getScore() {
        return tScore;
    }

    /**
     * Method to get name of player which the Score belongs to.
     * @return name of player.
     */
    public String getPlayer() {
        return tPlayer;
    }
}

