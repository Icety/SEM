package application.controllers;

import application.core.Player;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Controller class for Players in the game.
 * Created by Daphne van Tetering.
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class PlayerController {

    private ArrayList<Player> tPlayers;
    private int tPlayerCount;

    /**
     * Constructor for PlayerController.
     * @param numOfPlayers given Number of Players active in the game.
     */
    public PlayerController(int numOfPlayers) {
        tPlayerCount = numOfPlayers;

        setPlayers(tPlayerCount);

    }

    /**
     * Setter method for the players.
     * Also, the width and height of the player-image is set.
     * @param size number of players active in the game.
     */
    public void setPlayers(int size) {
        tPlayerCount = size;
        tPlayers = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            tPlayers.add(i - 1, new Player());
        }
        int j = 0;
        for (int k = 0; k <= size - 1; k++) {
            Player p = tPlayers.get(k);
            p.setX(1400 / 4 + j);
            p.setY(1080 - (p.getHeight() + 50));
            j = j + 100;
        }
    }

    /**
     * Setter method used for testing.
     * @param size number of player active in the game.
     * @param players players that we want to be added.
     */
    public void setPlayers(int size, ArrayList<Player> players) {
        tPlayers = players;
        int j = 0;
        for (int k = 0; k <= size - 1; k++) {
            Player p = tPlayers.get(k);
            p.setX(1400 / 4 + j);
            p.setY(1080 - (p.getHeight() + 50));
            j = j + 100;
        }
    }

    /**
     * Getter method for players in the game.
     * @return players in the game.
     */
    public ArrayList<Player> getPlayers() {
        return tPlayers;
    }

    /**
     * Update method for each the players.
     * @throws SlickException possible Exception.
     */
    public void update() throws SlickException {
        for (Player player : tPlayers) {
            player.update();
        }
    }

    /**
     * Getter method for number of players in the game.
     * @return number of players in the game.
     */
    public int getNumPlayers() {
        return tPlayerCount;
    }
}
