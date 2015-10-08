package application.core;

import org.newdawn.slick.Music;

import java.util.ArrayList;

/**
 * Class for Level.
 * @author Thomas Oomens.
 */
public class Level {
    protected ArrayList<Alien> tAliens;
    protected Player tPlayer;
    protected String tBackground;
    protected String tMusic;
    protected String tTheme;

    public String getTheme() {
        return tTheme;
    }

    public void setTheme(String tTheme) {
        this.tTheme = tTheme;
    }

    public String getStoryLine() {
        return tStoryLine;
    }

    public void setStoryLine(String tStoryLine) {
        this.tStoryLine = tStoryLine;
    }

    protected String tStoryLine;

    /**
     * Getter method for the Aliens in the Level.
     * @return an ArrayList of Aliens.
     */
    public ArrayList<Alien> getAliens() {
        return tAliens;
    }

    /**
     * Add Aliens to the Level.
     * @param aliens an ArrayList of Aliens to be added.
     */
    public void addAliens(ArrayList<Alien> aliens) {
        tAliens = aliens;
    }

    /**
     * Setter method for the Player of the Level.
     */
    public void setStartPlayer() {
        tPlayer = new Player();
    }

    /**
     * Create a representable String for the Level.
     * @return the String value.
     */
    public String toString() {
        String result = "Level with the following aliens: \n";
        for (Alien alien: tAliens) {
            result += alien.toString() +"\n";
        }
        return result;
    }

    /**
     * Setter method for the background Image of the Level.
     * @param background String containing name of the background Image.
     */
    public void setBackground(String background) {
        tBackground = background.replaceAll("\t", "").trim().replaceAll("\n ", "");
    }

    /**
     * Getter method for the background of the level.
     * @return
     */
    public String getBackground() {
        return tBackground;
    }

    /**
     * Remove an Alien from the Level.
     * @param a
     */
    public void removeAlien(Sprite a) {
        tAliens.remove(a);
    }

    /**
     * Check whether the Level has been won.
     * @return the boolean value.
     */
    public boolean hasWon() {
        boolean result = true;
        for (Alien alien : tAliens) {
            if (!alien.isDead() && !alien.isBonusAlien()) {
                result = false;
            }
        }
        return result;
    }


    public String getMusic() {
        return tMusic;
    }

    public void setMusic(String music) {
        this.tMusic = music.replaceAll("\t", "").trim().replaceAll("\n ", "");
    }
}
