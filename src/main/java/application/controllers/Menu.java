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
 * Controller class for Menu.
 * @author Thomas Oomens.
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier",
        "checkstyle:linelength"
})
public class Menu extends BasicGameState {
    private Main tMain;
    protected int tId;
    protected Image tBackground;
    protected boolean tExit;

    /**
     * Constructor method for the Menu.
     * @param id given ID belonging to the controller.
     */
    public Menu(int id) {
        tId = id;
    }

    /**
     * Init method for the Menu.
     * @param container GameContainer used by the program.
     * @param game being played at the moment.
     * @throws SlickException
     */
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        tMain = (Main) game;
        tBackground = new Image("src/main/java/application/images/backgrounds/moving.jpg");
        tExit = false;
    }

    /**
     * Render method for the Menu.
     * @param container GameContainer used by the program.
     * @param game being played at the moment.
     * @param g Graphics used by the program.
     * @throws SlickException
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        tBackground.draw(0, 0, container.getWidth(), container.getHeight());

        g.setColor(Color.white);
        g.drawString("Higher or Lower", 50, 10);

        g.drawString("1. Play Game", 50, 100);
        g.drawString("2. High Scores", 50, 120);
        g.drawString("3. Quit", 50, 140);
        g.drawString("4: Levelbuilder", 50, 160);
        g.drawString("5: Multiplayer", 50, 180);

        //g.drawString("5: Change difficulty", 50, 180);
        //g.drawString("   Current: "+ Main.DIFFICULTY, 50, 200);


    }

    /**
     * Update method for the Menu.
     * @param container GameController used by the program.
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
     * Getter method for the Menu.
     * @return the ID of the controller.
     */
    @Override
    public int getID() {
        return tId;
    }

    /**
     * Method to check whether a key is released.
     * @param key integer value for a key.
     * @param c character value for a key.
     */
    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_1:
                tMain.newGame(false);
                tMain.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                break;
            case Input.KEY_2:
                tMain.enterState(6, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                break;
            case Input.KEY_3:
                tExit = true;
                break;
            case Input.KEY_4:
                tMain.newGame(false);
                tMain.enterState(4, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                break;
            case Input.KEY_5:
               //tMain.changeDifficulty();
               // break;
                tMain.newGame(true);
                tMain.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            default:
                break;
        }
    }
}