package application.controllers;

import application.Main;
import application.core.aliens.Alien;
import application.core.Player;
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

import java.util.ArrayList;

/**
 * Controller class for StoryLine.
 * @author Thomas Oomens.
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier",
        "checkstyle:linelength",
        "checkstyle:methodlength"
})
public class StoryLine extends BasicGameState {
    protected Main tMain;
    protected int tId;
    protected ArrayList<Alien> tAliens;
    protected Image tBackground;
    protected Image tBackground2;
    protected String tBackgroundString = "moving.jpg";
    protected boolean tDone = false;
    protected boolean tStart = true;
    protected int tCount = 0;
    protected int tTextHeight = -300;
    protected ArrayList<Player> tPlayers;


    /**
     * Constructor method for the controller.
     *
     * @param id the given ID for the controller.
     */
    public StoryLine(int id) {
        tId = id;
    }

    /**
     * Initialization method for the controller.
     *
     * @param container required GameController.
     * @param game      the current game.
     * @throws SlickException possible Exception.
     */
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {

        tMain = (Main) game;
        tBackground = new Image("src/main/java/application/images/" + tBackgroundString);
        tStart = false;

    }

    /**
     * Render method for the controller.
     *
     * @param container required GameController.
     * @param game      the current game.
     * @param g         required Graphics.
     * @throws SlickException possible Exception.
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {

        tPlayers = tMain.getGame().getPlayerController().getPlayers();

        for (Player p : tPlayers) {
            int lives = p.getHealth();

            if (tStart) {
                tBackground2.draw(0, 0, container.getWidth(), container.getHeight());
            } else {
                tBackground.draw(0, 0, container.getWidth(), container.getHeight());
                if (tDone) {
                    g.drawString(this.getStory(), Main.WIDTH - 600, tTextHeight);
                }
            }

            g.setColor(Color.white);

            //Display Score in top left.
            g.drawString(("SCORE: " + Integer.toString(tMain.getGame().getScore())), 140, 50);

            //Display Lives in top right.
            g.drawString("LIVES: ", container.getWidth() - 500, 50);
            for (int i = 1; i <= lives; i++) {
                p.getImage().draw(container.getWidth() - 500 + i * 110, 50, p.getWidth(), p.getHeight());
            }
        }

        for (Player x : tPlayers) {
            //Draw the player
            x.getImage().draw(x.getX(), x.getY(), x.getWidth(), x.getHeight());
        }


    }

    /**
     * Update method for the controller.
     *
     * @param container required GameContainer.
     * @param game      the current game.
     * @param delta     a given integer.
     * @throws SlickException possible Exception.
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        tCount++;
        tPlayers = tMain.getGame().getPlayerController().getPlayers();
        for (Player p : tPlayers) {
            if (tStart) {
                //When just started counting, set the background
                if (tCount == 1) {
                    tBackground2 = new Image("src/main/java/application/images/" + tMain.getGame().getLevel().getBackground());
                }
                if (tCount % 2 == 0) {
                    p.moveUp((int) (tCount / 50) * 2);
                }
                if (p.getY() < -150) {
                    p.setX(250);
                    p.setY(Main.HEIGHT + 120);
                    tStart = false;
                    tCount = 0;
                    System.out.println("state 7");
                    game.enterState(7, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                }
            } else {
                if (!tDone) {
                    if (tCount % 2 == 0) {
                        p.moveUp(Math.max(2, 10 - (int) (tCount / 25)));
                    }
                    if (p.getY() < Main.HEIGHT / 2) {
                        tDone = true;
                        tCount = 0;
                    }
                } else {
                    if (tCount % 2 == 0) {
                        tTextHeight++;
                    }
                    if (tTextHeight > Main.HEIGHT / 2) {
                        if (tCount > 500) {
                            tCount = 0;
                        }
                        if (tCount % 2 == 0) {
                            p.moveUp((int) (tCount / 50) * 2);
                        }
                        if (p.getY() < -150) {
                            p.setX(Main.WIDTH - p.getWidth());
                            p.setY(Main.HEIGHT - p.getHeight() - 50);
                            this.resetValues();
                            tMain.getGame().nextLevel();
                            System.out.println("state 1");
                            game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                        }
                    } else {
                        if (tCount % 3 == 0) {
                            if (tCount % 60 < 30) {
                                p.moveLeft();
                            } else if (tCount % 60 < 60) {
                                p.moveRight();
                            }
                        }
                    }
                }

            }
        }
    }

    /**
     * Getter method for the ID of the controller.
     *
     * @return the controller ID.
     */
    @Override
    public int getID() {
        return tId;
    }

    /**
     * Method to reset the values of the StoryLine.
     */
    protected void resetValues() {
        tCount = 0;
        tDone = false;
        tTextHeight = -300;
    }

    /**
     * Method to give the made up Story.
     *
     * @return The made up story in a String.
     */
    protected String getStory() {
        //Todo: Get this from XML
        return "those aliens, are they?? Pandaliens??\n\n"
                + "Now the ship is on it's way. But wait! What are\n\n"
                + "the aliens, so it could be destroyed.\n\n"
                + "left it's safe home, in search for the planet of \n\n"
                + "retaliate! Their most powerful ship: The Thomas \n\n"
                + "planet was under attack, they would have to \n\n"
                + "it became clear to the SEMmians that their \n\n"
                + "After destroying the first layer of aliens";
    }
}