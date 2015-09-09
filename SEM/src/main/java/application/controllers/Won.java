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

public class Won extends BasicGameState{
    private StateBasedGame tGame; // stored for later use
    protected int tId;
    protected Image tBackground;

    public Won(int id) {
        tId = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        tGame = game;
        tBackground = new Image("src/main/java/application/images/background.jpg");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        tBackground.draw(0, 0, container.getWidth(), container.getHeight());

        g.setColor(Color.white);
        g.drawString("Je hebt gewonnen!", 50, 10);

        g.drawString("1. Play Game", 50, 100);
        g.drawString("2. High Scores", 50, 120);
        g.drawString("3. Quit", 50, 140);


    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return tId;
    }

    public void keyReleased(int key, char c) {
        switch(key) {
            case Input.KEY_1:
                Main.newGame();
                tGame.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
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