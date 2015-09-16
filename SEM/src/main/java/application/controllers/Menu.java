package application.controllers;

/**
 * Created by Thomas on 08-09-15.
 */
import application.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.io.File;

public class Menu extends BasicGameState{
    private Main tMain;
    protected int tId;
    protected Image tBackground;

    public Menu(int id) {
        tId = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        tMain = (Main) game;
        tBackground = new Image("src/main/java/application/images/moving.jpg");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        tBackground.draw(0, 0, container.getWidth(), container.getHeight());

        g.setColor(Color.white);
        g.drawString("Higher or Lower", 50, 10);

        g.drawString("1. Play Game", 50, 100);
        g.drawString("2. High Scores", 50, 120);
        g.drawString("3. Quit", 50, 140);


    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    }

    @Override
    public int getID() {
        return tId;
    }

    public void keyReleased(int key, char c) {
        switch(key) {
            case Input.KEY_1:
                tMain.newGame();
                tMain.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                break;
            case Input.KEY_2:
                // TODO: Implement later
                break;
            case Input.KEY_3:
                // TODO: Implement later
                break;
            default:
                break;
        }
    }

}