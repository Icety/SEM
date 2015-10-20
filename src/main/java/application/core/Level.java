package application.core;

import application.Barier;
import application.Main;
import application.controllers.PlayerController;
import application.core.aliens.Alien;
import java.util.ArrayList;

/**
 * Class for Level.
 * @author Thomas Oomens.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier"
})
public class Level {
    protected ArrayList<Alien> tAliens;
    protected ArrayList<Player> tPlayers;
    protected int tNumOfPlayers;
    protected String tBackground;
    protected PlayerController playerController;

    public Level (int numOfPlayers, PlayerController controller){
        tNumOfPlayers = numOfPlayers;
        playerController = controller;
        tBariers = new ArrayList<>();
        tBariers.add(new Barier(200, 900));
        tBariers.add(new Barier(Main.WIDTH/2-35, 900));
        tBariers.add(new Barier(Main.WIDTH-200-70, 900));
    }
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

    protected ArrayList<Barier> tBariers;

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
    public void setStartPlayers() {
        playerController.setPlayers(tNumOfPlayers);
    }

    /**
     * Create a representable String for the Level.
     * @return the String value.
     */
    public String toString() {
        String result = "Level with the following aliens: \n";
        for (Alien alien: tAliens) {
            result += alien.toString() + "\n";
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
     * @return the corresponding background.
     */
    public String getBackground() {
        return tBackground;
    }

    /**
     * Remove an Alien from the Level.
     * @param a the Alien to remove from the level.
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

    public ArrayList<Barier> getBariers() {
        return tBariers;
    }
    public void settAliens(ArrayList<Alien> list) {
        tAliens = list;
    }

    public String getMusic() {
        return tMusic;
    }

    public void setMusic(String music) {
        this.tMusic = music.replaceAll("\t", "").trim().replaceAll("\n ", "");
    }
}
