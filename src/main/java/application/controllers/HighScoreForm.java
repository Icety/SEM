package application.controllers;

import application.Main;
import org.newdawn.slick.Image;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.SlickException;
import application.core.HighScoreManager;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Controller class for HighScoreForm.
 * @author Daphne van Tetering.
 */
@SuppressWarnings({
        "checkstyle:linelength",
        "checkstyle:magicnumber"
})
public class HighScoreForm extends BasicGameState {

    protected HighScoreManager highScoreManager;
    protected Main tMain;
    protected int tId;
    protected Image tBackground;
    protected String tBackgroundString = "moving.jpg";
    protected boolean tPause = false;
    protected TextField tTextField;
    protected String tName;


    /**
     * Constructor method for HighScoreForm.
     * @param id ID of the HighScoreForm.
     */
    public HighScoreForm(int id) {
        tId = id;
        highScoreManager = new HighScoreManager();
    }

    /**
     * Init method for the HighScoreForm.
     * @param gameContainer current GameContainer.
     * @param stateBasedGame current Game.
     * @throws SlickException possible Exception.
     */
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        tMain = (Main) stateBasedGame;
        tBackground = new Image("src/main/java/application/images/" + tBackgroundString);

        tTextField = new TextField(gameContainer, gameContainer.getDefaultFont(), 420, 400, 500, 80);
        tTextField.setBorderColor(Color.transparent);
        tTextField.setAcceptingInput(true);
    }


    /**
     * Render method for the HighScoreForm.
     * @param gameContainer current GameContainer.
     * @param stateBasedGame current Game.
     * @param graphics required Graphics.
     * @throws SlickException possible Exception.
     */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        gameContainer.setPaused(tPause);
        if (!tPause) {
            tBackground.draw(0, 0, gameContainer.getWidth(), gameContainer.getHeight());

            tTextField.setFocus(true);
            tTextField.render(gameContainer, graphics);

            graphics.setColor(Color.white);
            graphics.drawString("SUBMIT YOUR SCORE", 600, 100);
            graphics.drawString("Please Enter Your Name", 555, 150);


        }
    }

    /**
     * Update method for the HighScoreForm.
     * @param gameContainer used by the program.
     * @param stateBasedGame being played at the moment.
     * @param i an integer value.
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    /**
     * Method to execute action when Enter is pressed.
     * @param key inputKey value.
     * @param c inputCharacter value.
     */
    public void keyReleased(int key, char c) {
        switch (key) {
            default: break;
            case Input.KEY_ENTER:
                highScoreManager.addScores(tTextField.getText(), tMain.getGame().getScore());
                tMain.enterState(6);
        }
    }

    /**
     * Method to get ID of the HighScoreForm.
     * @return the ID of the HighScoreForm.
     */
    @Override
    public int getID() {
        return tId;
    }

    /**
     * Method to get name of the player.
     * @return name of player.
     */
    public String getName() {
        return tName;
    }

    /**
     * Method to set name of the player.
     * @param name the desired name.
     */
    public void setName(String name) {
        tName = name;
    }
}