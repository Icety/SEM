package application.controllers;

import application.Main;
import application.core.Score;
import org.newdawn.slick.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * Class HighScoreBoard
 * Created by Daphne van Tetering on 17-9-2015.
 */
public class HighScoreBoard extends BasicGameState {
    protected Main tMain;
    protected int tId;
    protected Image tBackground;
    protected String tBackgroundString = "moving.jpg";
    protected boolean tPause = false;


    public HighScoreBoard(int id) {
        tId = id;
    }


    /**
     * init-method
     * @param gameContainer
     * @param stateBasedGame
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        tMain = (Main) stateBasedGame;
        tBackground = new Image("src/main/java/application/images/"+ tBackgroundString);
    }

    /**
     * render method
     * @param gameContainer
     * @param stateBasedGame
     * @param graphics
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        gameContainer.setPaused(tPause);
        if(!tPause) {
            tBackground.draw(0, 0, gameContainer.getWidth(), gameContainer.getHeight());

            graphics.setColor(Color.white);
            graphics.drawString("HIGH SCORE BOARD", 600, 100);

            ArrayList<Score> list = tMain.getGame().getHighScoreManager().getScores();
            int x = 400;
            int y = 300;
            int j = 1;

            for (int i = 0; i < list.size(); i++ ) {
                    graphics.drawString(j+ ". " + list.get(i).getPlayer().toString() + ": " + Integer.toString(list.get(i).getScore()) + "\n", x,y);
                    y = y + 25;
                    j = j + 1;

                }

           }

    }

    /**
     * update-method
     * @param gameContainer
     * @param stateBasedGame
     * @param i
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    /**
     * method which returns ID of the HighScoreBoard
     * @return ID
     */
    @Override
    public int getID() {
        return tId;
    }
}
