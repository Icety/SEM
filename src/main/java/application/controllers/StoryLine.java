package application.controllers;

import application.Main;
import application.core.aliens.Alien;
import application.core.Player;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
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
    protected boolean tSkip = false;
    protected boolean tScoreUpdate = false;
    protected boolean tOverlay = false;
    protected int tCount = 0;
    protected int tTextHeight = -300;
    protected int tTimeLeft;
    protected int tDifficulty;
    protected int tPointsEarned;
    protected int tNewScore;



    /**
     * Constructor method for the controller.
     * @param id the given ID for the controller.
     */
    public StoryLine(int id) {
        tId = id;
    }

    /**
     * Initialization method for the controller.
     * @param container required GameController.
     * @param game the current game.
     * @throws SlickException possible Exception.
     */
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {

        tMain = (Main) game;
        tBackground = new Image("src/main/java/application/images/backgrounds/" + tBackgroundString);
    }

    /**
     * Render method for the controller.
     * @param container required GameController.
     * @param game the current game.
     * @param g required Graphics.
     * @throws SlickException possible Exception.
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {

        if (!tOverlay) {
                Player p = tMain.getGame().getPlayerController().getPlayers().get(0);
            int lives = p.getHealth();

        if (tStart) {
            tBackground2.draw(0, 0, container.getWidth(), container.getHeight());
        }
        else {
            tBackground.draw(0, 0, container.getWidth(), container.getHeight());
        }
            if (tDone) {
                g.drawString(tMain.getGame().getLevel().getStoryLine(), Main.WIDTH - 750, tTextHeight);
                if (tScoreUpdate && tCount < 500) {
                    showPoints(g, container);
                }
            }

            g.setColor(Color.white);

        //Display Score in top left.
        g.drawString(("SCORE"), 140, 50);
        g.drawString(Integer.toString(tMain.getGame().getScore()), 150, 80);

            //Display Lives in top right.
            g.drawString("LIVES: ", container.getWidth() - 500, 50);
            for (int i = 1; i <= lives; i++) {
                p.getImage().draw(container.getWidth() - 500 + i * 110, 50, p.getWidth(), p.getHeight());
            }

        //Display Time
        g.drawString("TIME LEFT", 240, 50);
        g.drawString(Integer.toString(tTimeLeft), 250, 80);

            //Draw the player
            p.getImage().draw(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        }
    }

    /**
     * Update method for the controller.
     * @param container required GameContainer.
     * @param game the current game.
     * @param delta a given integer.
     * @throws SlickException possible Exception.
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (tMain.getGame().getLevel() != null
                && tMain.getGame().getLevel().getStoryLine() != null
                && tMain.getGame().getLevel().getStoryLine().equals("")) {
            tSkip = true;
        }

        tCount++;
        Player p = tMain.getGame().getPlayerController().getPlayers().get(0);


        //Calculate new values.
        if (tCount == 1) {
            tPointsEarned = tMain.getGame().getScore() - tMain.getGame().getStartScore();
            tTimeLeft = tMain.getGame().getTime();
            tNewScore  = tPointsEarned + tTimeLeft * 10 * tDifficulty;
        }

        if (tStart) {
            //When just started counting, set the background
            if (tCount == 1) {
                tBackground2 = new Image("src/main/java/application/images/backgrounds/" + tMain.getGame().getLevel().getBackground());
                tDifficulty = tMain.DIFFICULTY;
                Main.BACKGROUNDMUSIC.stop();
                Main.BACKGROUNDMUSIC = new Music("src/main/java/application/sound/storyline.wav");
                Main.BACKGROUNDMUSIC.loop();
            }
            if (tCount % 2 == 0) {
                p.moveUp((int) (tCount / 50) * 2);
            }

            if (p.getY() < -150) {
                p.setX(250);
                p.setY(Main.HEIGHT + 120);
                tStart = false;
                tOverlay = true;
                tCount = 0;
                tScoreUpdate = true;
                game.enterState(20, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            }
        } else {
            //If there is no story, a boss is appearing. In this case,
            // we do not change the background as the "level" has not finished yet.
            if (tMain.getGame().getLevel().getTheme().equals("daphne")) {
                tBackground = new Image("src/main/java/application/images/backgrounds/" + tMain.getGame().getLevel().getBackground());
            } else if (!tMain.getGame().getLevel().getTheme().equals("")) {
                tBackground = new Image("src/main/java/application/images/backgrounds/" + tBackgroundString);
            }
            tOverlay = false;
            if (!tDone && !tSkip) {
                if (tCount % 2 == 0) {
                    p.moveUp(Math.max(2, 10 - (int) (tCount / 25)));
                }
                if (p.getY() < Main.HEIGHT / 2) {
                    tDone = true;
                    tCount = 0;
                }
            }
            else {
                if (tCount % 2 == 0) {
                    tTextHeight++;
                }
                if (tTextHeight > Main.HEIGHT / 2 || tSkip) {
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

                        String theme = tMain.getGame().getLevel().getTheme();
                        //Method in main to change the images according to the theme
                        tMain.setAlienImages(theme);

                        Main.sNewLevel = true;
                        game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                    }
                }
                else {
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

    /**
     * Getter method for the ID of the controller.
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
        tSkip = false;
        tStart = true;
        tTextHeight = -300;
        tScoreUpdate = false;
    }

    /**
     * Method to give the made up Story.
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

    /**
     * Check whether a key is pressed.
     * @param key the pressed key.
     * @param c the belonging character.
     */
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_SPACE:
                tSkip = true;
                break;
            default:
                break;
        }
    }

    /**
     * Method to visualize the points.
     * @param g required Graphics.
     * @param container required GameContainer.
     */
    private void showPoints(Graphics g, GameContainer container) {
        //Show total points earned in the past level
        if (tCount == 1) {
            tMain.getGame().setScore(-tPointsEarned + tNewScore);
        }
        if (10 < tCount) {
            g.drawString("Points earned:", container.getWidth() / 2 - 400, container.getHeight() / 2);
            g.drawString(Integer.toString(tPointsEarned), container.getWidth() / 2 - 400, container.getHeight() / 2 + 50);
        }

        //Draw +
        if (60 < tCount) {
            g.drawString("+", container.getWidth() / 2 - 300, container.getHeight() / 2 + 50);
        }

        //Show time bonus
        if (110 < tCount) {
            g.drawString("Time bonus:", container.getWidth() / 2 - 200, container.getHeight() / 2);
            g.drawString(Integer.toString(tTimeLeft * 10), container.getWidth() / 2 - 200, container.getHeight() / 2 + 50);
        }

        //Draw x
        if (160 < tCount) {
            g.drawString("x", container.getWidth() / 2 - 100, container.getHeight() / 2 + 50);
        }

        //Show time multiplier
        if (210 < tCount) {
            g.drawString("Difficulty multiplier:", container.getWidth() / 2, container.getHeight() / 2);
            g.drawString(Integer.toString(tDifficulty),  container.getWidth() / 2, container.getHeight() / 2 + 50);
        }

        //Draw =
        if (260 < tCount) {
            g.drawString("=", container.getWidth() / 2 + 200, container.getHeight() / 2 + 50);
        }

        //Show total score
        if (310 < tCount) {
            g.drawString("Total:", container.getWidth() / 2 + 400, container.getHeight() / 2);
            g.drawString(Integer.toString(tNewScore), container.getWidth() / 2 + 400, container.getHeight() / 2 + 50);
        }
    }
}
