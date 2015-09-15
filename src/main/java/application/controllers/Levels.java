package application.controllers;

import application.Main;
import application.core.Alien;
import application.core.Game;
import application.core.Player;
import application.core.Projectile;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;

/**
 * Created by Thomas on 08-09-15.
 */
public class Levels extends BasicGameState {
    protected Main tMain; // stored for later use
    protected int tId;
    protected ArrayList<Alien> tAliens;
    protected Image tBackground;
    protected String tBackgroundString = "moving.jpg";
    protected boolean pause = false;

    public Levels(int id) {
        tId = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {

        tBackground = new Image("src/main/java/application/images/"+ tBackgroundString);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        container.setPaused(pause);
        if (!pause) {
            Player p = Main.sGame.getPlayer();
            int lives = p.getHealth();

            tBackground.draw(0, 0, container.getWidth(), container.getHeight());

            g.setColor(Color.white);

            //Display Score in top left.
            g.drawString(("SCORE: " + Integer.toString(Main.sGame.getScore())), 140, 50);

            //Display Lives in top right.
            g.drawString("LIVES: ", container.getWidth() - 500, 50);
            for (int i = 1; i <= lives; i++) {
                p.getImage().draw(container.getWidth() - 500 + i * 110, 50, p.getWidth(), p.getHeight());
            }

            //Draw all aliens
            for (Alien alien : Main.sGame.getLevel().getAliens()) {
                (alien.getImage()).draw(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
            }

            //Draw the player
            p.getImage().draw(p.getX(), p.getY(), p.getWidth(), p.getHeight());

            //Draw projectiles
            for (Projectile projectile : Main.sGame.getProjectiles()) {
                projectile.getImage().draw(projectile.getX(), projectile.getY(), projectile.getWidth(), projectile.getHeight());
            }
        } else {
            g.drawString("PAUSED", container.getWidth() /2, container.getHeight() /2);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (!pause) {
            Main.sGame.update();
            if (Main.sGame.hasWon()) {
                game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            }
            if (Main.sGame.hasLost()) {
                game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            }
            if (Main.sGame.getLevel().getBackground().equals(tBackgroundString)) {
                tBackground = new Image("src/main/java/application/images/"+ tBackgroundString);
                tBackgroundString = Main.sGame.getLevel().getBackground();
            }
        }

    }

    @Override
    public int getID() {
        return tId;
    }

    public void keyPressed(int key, char c) {
        switch(key) {
            case Input.KEY_LEFT:
                Main.sGame.getPlayer().leftArrowPressed(true);
                break;
            case Input.KEY_RIGHT:
                Main.sGame.getPlayer().rightArrowPressed(true);
                break;
            case Input.KEY_SPACE:
                Main.sGame.getPlayer().fireButtonPressed(true);
                //TODO
                break;
            default:
                break;
        }
    }

    public void keyReleased(int key, char c) {
        switch(key) {
            case Input.KEY_LEFT:
                Main.sGame.getPlayer().leftArrowPressed(false);
                break;
            case Input.KEY_RIGHT:
                Main.sGame.getPlayer().rightArrowPressed(false);
                break;
            case Input.KEY_SPACE:
                Main.sGame.getPlayer().fireButtonPressed(false);
                break;
            case Input.KEY_ESCAPE:
                pause = !pause;
                System.out.println(pause);
            default:
                break;
        }
    }
}
