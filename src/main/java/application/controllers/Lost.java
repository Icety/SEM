package application.controllers;

import application.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Controller class for Lost.
 * @author Thomas Oomens
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier",
        "checkstyle:linelength"
})
public class Lost extends BasicGameState {
    protected Main tMain; // stored for later use
    protected int tId;
    protected Image tBackground;
    protected Image tWon;
    protected boolean tExit;

    /**
     * Constructor method for Lost.
     * @param id ID for the Lost controller.
     */
    public Lost(int id) {
        tId = id;
    }

    /**
     * Init method for Lost.
     * @param container GameContainer used by the program.
     * @param game being played at the moment.
     * @throws SlickException
     */
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        tMain = (Main) game;
        tBackground = new Image("images/backgrounds/moving.jpg");
        tWon = new Image("images/gameover.png");
        tExit = false;
    }

    /**
     * Render method for Lost.
     * @param container GameContainer used by the program.
     * @param game being played at the moment.
     * @param g Graphics used by the program.
     * @throws SlickException
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        tBackground.draw(0, 0, container.getWidth(), container.getHeight());
        tWon.draw(0, 0, container.getWidth(), container.getHeight());

        g.setColor(Color.white);

        g.drawString("1. Play Game", 50, 100);
        g.drawString("2. High Scores", 50, 120);
        g.drawString("3. Quit", 50, 140);
        g.drawString("Press Space to submit score", 50, 160);
    }

    /**
     * Update method for Lost.
     * @param container GameContainer used by the program.
     * @param game being played at the moment.
     * @param delta an integer value.
     * @throws SlickException
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (tExit) {
            container.exit();
        }
    }

    /**
     * Getter method for the ID of the controller.
     * @return the controller ID.
     */
    @Override
    public int getID() {
        return tId;
    }

    /**
     * Method to check whether a key is being released.
     * @param key integer value for the key.
     * @param c character value for the key.
     */
    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_1:
                tMain.newGame(tMain.getGame().getPlayerController().getNumPlayers());
                tMain.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                break;
            case Input.KEY_2:
                tMain.enterState(6, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                break;
            case Input.KEY_3:
                tExit = true;
                break;
            case Input.KEY_SPACE:
                tMain.enterState(5);
                break;
            default:
                break;
        }
    }
}