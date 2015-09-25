package application.controllers;

import application.Main;
import application.core.*;
import application.core.Game;
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
    protected Main tMain;
    protected int tId;
    protected ArrayList<Alien> tAliens;
    protected Image tBackground;
    protected String tBackgroundString = "background.jpg";
    protected boolean pause = false;

    public Levels(int id) {
        tId = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {

        tMain = (Main) game;
        tBackground = new Image("src/main/java/application/images/"+ tBackgroundString);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        container.setPaused(pause);
        if (!pause) {
            Player p = tMain.getGame().getPlayer();
            int lives = p.getHealth();

            tBackground.draw(0, 0, container.getWidth(), container.getHeight());

            g.setColor(Color.white);

            //Display Score in top left.
            g.drawString(("SCORE: " + Integer.toString(tMain.getGame().getScore())), 140, 50);

            //Display Lives in top right.
            g.drawString("LIVES: ", container.getWidth() - 500, 50);
            for (int i = 1; i <= lives; i++) {
                p.getImage().draw(container.getWidth() - 500 + i * 110, 50, p.getWidth(), p.getHeight());
            }

            //Draw all aliens and its upgrades
            for (Alien alien : tMain.getGame().getLevel().getAliens()) {
                if (!alien.isDead()) {
                    (alien.getImage()).draw(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
                }
                drawProjectiles(alien.getProjectiles());
                drawUpgrades(alien.getUpgrades());
            }

            //Draw the player
            p.getImage().draw(p.getX(), p.getY(), p.getWidth(), p.getHeight());
            drawProjectiles(p.getProjectiles());

        } else {
            g.drawString("PAUSED", container.getWidth() /2, container.getHeight() /2);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (!pause) {
            tMain.getGame().update();
            if (tMain.getGame().hasWon()) {
                game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            }
            if (tMain.getGame().hasLost()) {
                game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            }
            if (tMain.getGame().isNextLevel()) {
                game.enterState(7);
            }
            if (!tMain.getGame().getLevel().getBackground().equals(tBackgroundString)) {
                tBackgroundString = tMain.getGame().getLevel().getBackground();
                tBackground = new Image("src/main/java/application/images/"+ tBackgroundString);
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
                tMain.getGame().getPlayer().leftArrowPressed(true);
                break;
            case Input.KEY_RIGHT:
                tMain.getGame().getPlayer().rightArrowPressed(true);
                break;
            case Input.KEY_SPACE:
                tMain.getGame().getPlayer().fireButtonPressed(true);
                //TODO
                break;
            default:
                break;
        }
    }

    public void keyReleased(int key, char c) {
        switch(key) {
            case Input.KEY_LEFT:
                tMain.getGame().getPlayer().leftArrowPressed(false);
                break;
            case Input.KEY_RIGHT:
                tMain.getGame().getPlayer().rightArrowPressed(false);
                break;
            case Input.KEY_SPACE:
                tMain.getGame().getPlayer().fireButtonPressed(false);
                break;
            case Input.KEY_ESCAPE:
                pause = !pause;
                System.out.println(pause);
            default:
                break;
        }
    }

    public void drawProjectiles(ArrayList<Projectile> projectiles) {
        for (Projectile projectile: projectiles) {
            projectile.getImage().draw(projectile.getX(), projectile.getY(), projectile.getWidth(), projectile.getHeight());
        }
    }

    private void drawUpgrades(ArrayList<Upgrade> upgrades) {
        for(Upgrade u: upgrades) {
            if(u.toDraw())
            (u.getImage()).draw(u.getX(), u.getY(), u.getWidth(), u.getHeight());
        }
    }
}
