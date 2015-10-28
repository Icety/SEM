package application.core;

import application.Main;
import application.controllers.PlayerController;
import application.core.aliens.Alien;
import java.util.ArrayList;

/**
 * Class for Level.
 * @author Thomas Oomens.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier",
        "checkstyle:magicnumber"
})
public class Level {
    protected ArrayList<Alien> tAliens;
    protected ArrayList<Player> tPlayers;
    protected int tNumOfPlayers;
    protected int tTime;
    protected String tBackground;
    protected PlayerController playerController;
    protected String tMusic;
    protected String tTheme;
    protected String tStoryLine;
    protected ArrayList<Barrier> tBarriers;

    /**
     * Constructor method for Level.
     * @param numOfPlayers amount of players.
     * @param controller the PlayerController.
     */
    public Level(int numOfPlayers, PlayerController controller) {
        tNumOfPlayers = numOfPlayers;
        playerController = controller;
        tBarriers = new ArrayList<>();
        tBarriers.add(new Barrier(200, 900));
        tBarriers.add(new Barrier(Main.WIDTH / 2 - 35, 900));
        tBarriers.add(new Barrier(Main.WIDTH - 200 - 70, 900));
    }

    /**
     * Getter method for the Time for the level.
     * @return the integer value of time.
     */
    public int getTime() {
        return tTime;
    }

    /**
     * Setter method for the Time for the level.
     * @param tTime the current time.
     */
    public void setTime(int tTime) {
        this.tTime = tTime;
    }

    /**
     * Getter method for the theme.
     * @return the theme.
     */
    public String getTheme() {
        return tTheme;
    }

    /**
     * Setter method for the theme.
     * @param tTheme the wanted theme.
     */
    public void setTheme(String tTheme) {
        this.tTheme = tTheme;
    }

    /**
     * Getter method for the storyLine.
     * @return the storyline.
     */
    public String getStoryLine() {
        return tStoryLine;
    }

    /**
     * Setter for the theme of the storyLine.
     * @param tStoryLine the wanted storyline.
     */
    public void setStoryLine(String tStoryLine) {
        this.tStoryLine = tStoryLine;
    }

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

    /**
     * Getter method for all Barriers in this level.
     * @return a list of Barriers.
     */
    public ArrayList<Barrier> getBariers() {
        return tBarriers;
    }

    /**
     * Setter method to set all Aliens in this level.
     * @param list the list of Aliens.
     */
    public void settAliens(ArrayList<Alien> list) {
        tAliens = list;
    }

    /**
     * Getter method for the music that should be played in this level.
     * @return the Music.
     */
    public String getMusic() {
        return tMusic;
    }

    /**
     * Setter method fot the Music.
     * @param music the String to set tMusic to.
     */
    public void setMusic(String music) {
        this.tMusic = music.replaceAll("\t", "").trim().replaceAll("\n ", "");
    }
}
