package application.controllers;

import application.Main;
import application.core.HighScoreManager;
import application.core.Score;
import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;

/**
 * Controller class for HighScoreBoard.
 * @author Daphne van Tetering.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier",
        "checkstyle:magicnumber",
        "checkstyle:linelength"
})
public class HighScoreBoard extends BasicGameState {
    protected Main tMain;
    protected HighScoreManager tHighScoreManager;
    protected int tId;
    protected Image tBackground;
    protected String tBackgroundString = "moving.jpg";
    protected boolean tPause = false;

    /**
     * Constructor method for HighScoreBoard.
     * @param id controller ID.
     */
    public HighScoreBoard(int id) {
        tId = id;
    }


    /**
     * Init method for the HighScoreBoard.
     * @param gameContainer used by the program.
     * @param stateBasedGame being played at the moment.
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        tMain = (Main) stateBasedGame;

        tBackground = new Image("src/main/java/application/images/backgrounds/" + tBackgroundString);
    }

    /**
     * Rendering method for the HighScoreBoard.
     * @param gameContainer used by the program.
     * @param stateBasedGame being played at the moment.
     * @param graphics used by the game.
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        gameContainer.setPaused(tPause);
        if (!tPause) {
            tBackground.draw(0, 0, gameContainer.getWidth(), gameContainer.getHeight());

            graphics.setColor(Color.white);
            graphics.drawString("HIGH SCORE BOARD", 600, 100);
            graphics.drawString("Press Enter to return to menu", 550, 150);

            if (tMain.getGame() == null) {
                tHighScoreManager = new HighScoreManager();
            } else {
                tHighScoreManager = tMain.getGame().getHighScoreManager();
            }

            ArrayList<Score> list = tHighScoreManager.getScores();
            int x = 400;
            int y = 300;
            int j = 1;

            for (Score aList : list) {
                graphics.drawString(j + ". " + aList.getPlayer() + ": " + Integer.toString(aList.getScore()) + "\n", x, y);
                y = y + 25;
                j = j + 1;
            }
           }

    }

    /**
     * Method to check whether a key is released.
     * @param key integer value for a key.
     * @param c character value for a key.
     */
    public void keyReleased(int key, char c) {
            switch (key) {
            default: break;
            case Input.KEY_ENTER:
                tMain.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }

    /**
     * Update method for the HighScoreBoard.
     * @param gameContainer used by the program.
     * @param stateBasedGame being played at the moment.
     * @param i an integer value.
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    /**
     * Method which returns ID of the HighScoreBoard.
     * @return ID of the HighScoreBoard.
     */
    @Override
    public int getID() {
        return tId;
    }
}
