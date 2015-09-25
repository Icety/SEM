package application.controllers;

import application.core.Score;

import java.util.Comparator;

/**
 * Created by Daphne van Tetering on 25-9-2015.
 */
public class ScoreComparator implements Comparator<Score> {

    public int compare (Score x, Score y) {
        int sc1 = x.getScore();
        int sc2 = y.getScore();


        if (sc1 > sc2) {
            return 1;
        } else if (sc1 < sc2) {
            return -1;
        } else {
            return 0;
        }
    }
}