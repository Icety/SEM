package application.core;

import application.Main;
import application.controllers.HighScoreForm;
import application.controllers.ScoreComparator;
import org.newdawn.slick.state.StateBasedGame;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Daphne van Tetering on 17-9-2015.
 */
public class HighScoreManager {
    private ArrayList<Score> scores;
    private static final String highScoreFile = "highScores.dat";
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighScoreManager(){
        scores = new ArrayList<Score>();
    }

    public ArrayList<Score> getScores(){
        loadScoreFile();
        sort();
        return scores;
    }

    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
        Collections.reverse(scores);
    }

    public void addScores(String newPlayer, int newScore) {
        loadScoreFile();
        scores.add(new Score(newPlayer, newScore));
        sort();
        updateScoreFile();
    }

    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(highScoreFile));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(highScoreFile));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
