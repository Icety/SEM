package application.controllers;

import application.Main;
import application.core.HighScoreManager;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daphne van Tetering on 22-9-2015.
 */
public class HighScoreForm extends BasicGameState {

    protected HighScoreManager highScoreManager;
    protected Main tMain;
    protected int tId;
    protected Image tBackground;
    protected String tBackgroundString = "moving.jpg";
    protected boolean tPause = false;
    protected TextField tTextField;
    public String tName;



    public HighScoreForm (int id) {
        tId = id;
        highScoreManager = new HighScoreManager();
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        tMain = (Main) stateBasedGame;
        tBackground = new Image("src/main/java/application/images/"+ tBackgroundString);

        tTextField = new TextField(gameContainer,gameContainer.getDefaultFont(),420,400,500,80);
        tTextField.setBorderColor(Color.transparent);
        tTextField.setAcceptingInput(true);

    }


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

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void keyReleased(int key, char c) {
        switch(key) {
            case Input.KEY_ENTER:
                highScoreManager.addScores(tTextField.getText(),tMain.getGame().getScore());
                tMain.enterState(4, new FadeInTransition(Color.black), new FadeOutTransition(Color.black));
        }
    }

    @Override
    public int getID() {
        return 5;
    }

    public String getName() {
        return tName;
    }
}
