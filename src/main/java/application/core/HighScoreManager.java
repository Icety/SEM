package application.core;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Daphne van Tetering on 17-9-2015.
 */
public class HighScoreManager {

    private ArrayList<Integer> scores;
    private static final String highScoreFile = "scores.dat";
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighScoreManager(){
        scores = new ArrayList<Integer>();
    }

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0,6);
        list.add(1,4);
        list.add(2,39);
        list.add(3, 1);
    }


    public int compare (int x, int y) {
        if (x > y) {
            return -1;
        } else if (x < y) {
            return 1;
        } else {
            return 0;
        }
    }

    public ArrayList<Integer> getScores(){
        loadScoreFile();
        sort();
        return scores;
    }

    private void sort() {
        Collections.sort(scores);
        Collections.reverse(scores);
    }

    public void addScores(int newScore) {
        loadScoreFile();
        scores.add(newScore);
        updateScoreFile();
    }

    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(highScoreFile));
            scores = (ArrayList<Integer>) inputStream.readObject();
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

    public String toString(){
       return getScores().toString();

    }
}
