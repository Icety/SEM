package application.controllers;

import application.Main;
import application.core.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;

/**
 * Controller class for Levels.
 * @author Thomas Oomens
 */
public class Levels extends BasicGameState {
    protected Main tMain;
    protected int tId;
    protected Image tBackground;
    protected String tBackgroundString = "background.jpg";
    protected String tMusicString = "normalmusic.wav";
    protected boolean pause = false;
    protected String tTheme;

    public Levels(int id) {
        tId = id;
    }

    /**
     * Init method for Levels.
     * @param container GameContainer used by the program.
     * @param game being played at the moment.
     * @throws SlickException
     */
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {

        tMain = (Main) game;
        tBackground = new Image("src/main/java/application/images/"+ Main.imageTheme + "/"+ tBackgroundString);
    }

    /**
     * Render method for Levels.
     * @param container GameContainer being used at the moment.
     * @param game being played at the moment.
     * @param g Graphics used by the program.
     * @throws SlickException
     */
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

    /**
     * Update method for Levels.
     * @param container GameContainer used by the program.
     * @param game being played at the moment.
     * @param delta an integer value.
     * @throws SlickException
     */
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
                game.enterState(20);
            }
            if (!tMain.getGame().getLevel().getBackground().equals(tBackgroundString)) {
                tBackgroundString = tMain.getGame().getLevel().getBackground();
                tBackground = new Image("src/main/java/application/images/backgrounds/"+ tBackgroundString);
            }
            if (!tMain.getGame().getLevel().getMusic().equals(tMusicString)) {
                tMusicString = tMain.getGame().getLevel().getMusic();
                tMain.tBackgroundmusic.stop();
                tMain.tBackgroundmusic = new Music("src/main/java/application/sound/" + tMusicString);
                tMain.tBackgroundmusic.loop();
            }

            //Change the theme if the new theme does not match the current theme.
            if (!tMain.getGame().getLevel().getTheme().equals(tTheme)) {
                tTheme = tMain.getGame().getLevel().getTheme();

                //Method in main to change the images according to the theme
                tMain.setAlienImages(tTheme);
            }


        }

    }

    /**
     * Getter method for the ID of the Controller.
     * @return the ID of the Controller.
     */
    @Override
    public int getID() {
        return tId;
    }

    /**
     * Method to check whether a key is pressed.
     * @param key integer value for the key.
     * @param c character value for the key.
     */
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
                break;
            case Input.KEY_S:
                tMain.enterState(20);
                break;
            default:
                break;
        }
    }

    /**
     * Method to check whether a key is released.
     * @param key integer value for the key.
     * @param c character value for the key.
     */
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

    /**
     * Method to spawn in-game projectiles.
     * @param projectiles to draw in the game.
     */
    public void drawProjectiles(ArrayList<Projectile> projectiles) {
        for (Projectile projectile: projectiles) {
            projectile.getImage().draw(projectile.getX(), projectile.getY(), projectile.getWidth(), projectile.getHeight());
        }
    }

    /**
     * Method to spawn in-game upgrades.
     * @param upgrades to draw in the game.
     */
    private void drawUpgrades(ArrayList<Upgrade> upgrades) {
        for(Upgrade u: upgrades) {
            if(u.toDraw())
            (u.getImage()).draw(u.getX(), u.getY(), u.getWidth(), u.getHeight());
        }
    }
}
