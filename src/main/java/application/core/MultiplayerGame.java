package application.core;

import application.logger.Logger;

/**
 * Created by Daphne van Tetering on 6-10-2015.
 */
public class MultiplayerGame extends Game {

    protected Player tPlayer1;
    protected Player tPlayer2;


    /**
     * Constructor for Game.
     *
     * @param width  the width of the game.
     * @param height the height of the game.
     * @param logger the Logger to be bound to the game.
     */
    public MultiplayerGame(int width, int height, Logger logger) {
        super(width, height, logger);
        tPlayer1 = new Player();
        tPlayer2 = new Player();

    }

    public Player getPlayer1() {
        return tPlayer1;
    }

    public void setPlayer1(Player player1) {
        tPlayer1 = player1;
    }

    public Player getPlayer2() {
        return tPlayer2;
    }

    public void setPlayer2(Player player2) {
        tPlayer2 = player2;
    }
}
