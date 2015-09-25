package application.core;

import java.io.Serializable;

/**
 * Created by Daphne van Tetering on 25-9-2015.
 */
public class Score implements Serializable{
    protected int tScore;
    protected String tPlayer;

    public Score() {

    }

    public Score(String player, int score){
        tPlayer = player;
        tScore = score;
    }


    public int getScore() {
        return tScore;
    }

    public String getPlayer() {
        return tPlayer;
    }
}

