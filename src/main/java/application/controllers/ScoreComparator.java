package application.controllers;

import java.util.Comparator;
import application.core.Score;

/**
 * Controller class for the score comparator.
 * @author Daphne van Tetering
 */
public class ScoreComparator implements Comparator<Score> {

    /**
     * Compare two integers.
     * @param x integer to be compared.
     * @param y integer to be compared.
     * @return value to indicate which integer is bigger.
     */
    public int compare(Score x, Score y) {
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