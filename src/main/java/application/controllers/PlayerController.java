package application.controllers;

import application.core.Player;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by Daphne van Tetering on 13-10-2015.
 */
public class PlayerController {

    private ArrayList<Player> tPlayers;
    private int tPlayerCount;


    public PlayerController(int numOfPlayers){
       tPlayerCount = numOfPlayers;

        setPlayers(tPlayerCount);

    }

    public void setPlayers(int size) {
        tPlayerCount = size;
        tPlayers = new ArrayList<>();
       for (int i = 1; i <= size; i++) {
           tPlayers.add(i-1, new Player());
           }
       int j = 0;
       for (int k = 0; k <= size -1; k++) {
           Player p = tPlayers.get(k);
           p.setX(1400 / 4 + j);
           p.setY(1080 - (p.getHeight() + 50));
           j = j + 100;
       }
    }

    public void setPlayers(int size, ArrayList<Player> players) {
        tPlayers = players;
        int j = 0;
        for (int k = 0; k <= size -1; k++) {
            Player p = tPlayers.get(k);
            p.setX(1400 / 4 + j);
            p.setY(1080 - (p.getHeight() + 50));
            j = j + 100;
        }
    }

    public ArrayList<Player> getPlayers() {
        return tPlayers;
    }

    public void update() throws SlickException{
        for (Player player : tPlayers) {
            player.update();
        }
    }

    public int getNumPlayers() {
        return tPlayerCount;
    }

}
