package application.core;

import java.util.ArrayList;

/**
 * Created by Thomas on 01-09-15.
 */
public class Level {
    protected ArrayList<Alien> tAliens;
    protected Player tPlayer;

    public ArrayList<Alien> getAliens() {
        return tAliens;
    }

    public void addAliens(ArrayList<Alien> aliens) {
        tAliens = aliens;
    }

    public void setStartPlayer() {
        tPlayer = new Player();
    }

    public String toString() {
        String result = "Level with the following aliens: \n";
        for (Alien alien: tAliens) {
            result += alien.toString() +"\n";
        }
        return result;
    }


    public void removeAlien(Alien a) {
        tAliens.remove(a);
    }
}
