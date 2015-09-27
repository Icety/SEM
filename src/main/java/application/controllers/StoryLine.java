package application.controllers;

import application.Main;
import application.core.Alien;
import application.core.Player;
import application.core.Projectile;
import application.core.Upgrade;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;

/**
 * Created by Thomas on 08-09-15.
 */
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

    public StoryLine(int id) {
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

        Player p = tMain.getGame().getPlayer();
        int lives = p.getHealth();

        if (tStart) {
            tBackground2.draw(0, 0, container.getWidth(), container.getHeight());
        }
        else {
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

        //Draw the player
        p.getImage().draw(p.getX(), p.getY(), p.getWidth(), p.getHeight());

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        tCount++;
        Player p = tMain.getGame().getPlayer();
        if (tStart) {
            //When just started counting, set the background
            if (tCount == 1) {
                tBackground2 = new Image("src/main/java/application/images/"+ tMain.getGame().getLevel().getBackground());
            }
            if (tCount % 2 == 0) {
                p.moveUp((int) (tCount / 50) * 2);
            }
            if (p.getY() < -150) {
                System.out.println(p.getY());
                p.settX(250);
                p.settY(Main.HEIGHT + 120);
                tStart = false;
                tCount = 0;
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
            }
            else {
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
                        p.settX(Main.WIDTH - p.getWidth());
                        p.settY(Main.HEIGHT - p.getHeight() - 50);
                        this.resetValues();
                        tMain.getGame().nextLevel();
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

    @Override
    public int getID() {
        return tId;
    }

    protected void resetValues() {
        tCount = 0;
        tDone = false;
        tTextHeight = -300;
    }

    protected String getStory() {
        //Todo: Get this from XML
        return "those aliens, are they?? Pandaliens??\n\n" +
                "Now the ship is on it's way. But wait! What are\n\n" +
                "the aliens, so it could be destroyed.\n\n" +
                "left it's safe home, in search for the planet of \n\n" +
                "retaliate! Their most powerful ship: The Thomas \n\n" +
                "planet was under attack, they would have to \n\n" +
                "it became clear to the SEMmians that their \n\n" +
                "After destroying the first layer of aliens";
    }

    public void keyPressed(int key, char c) {
        switch(key) {
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
            default:
                break;
        }
    }
}
