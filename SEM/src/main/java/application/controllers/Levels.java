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

    public Levels(int id) {
        tId = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {

        Player p = Main.sGame.getPlayer();
        p.settX(Main.sGame.getWidth() / 2);
        p.settY(Main.sGame.getHeight()  - (p.getHeight() + 50));

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {

        g.setColor(Color.white);

        g.drawString(("SCORE: " + Integer.toString(Main.sGame.getScore())), container.getWidth() - 140, 50);

        for (Alien alien: Main.sGame.getLevel().getAliens()) {
            (alien.getImage()).draw(alien.getX(), alien.getY());
        }
        Player p = Main.sGame.getPlayer();
        p.getImage().draw(p.getX(), p.getY());

        for (Projectile projectile: Main.sGame.getProjectiles()) {
            projectile.getImage().draw(projectile.getX(), projectile.getY());
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        Main.sGame.update();
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
            default:
                break;
        }
    }



//    protected void drawPlayer(GraphicsContext gc) {
//        Player player = OldMain.game.getPlayer();
//        gc.drawImage(player.getImage(), player.getX(), player.getY());
//    }
//
//    protected void drawProjectiles(GraphicsContext gc) {
//        ArrayList<Projectile> projectiles = OldMain.game.getProjectiles();
//        for (Projectile projectile: projectiles) {
//            gc.drawImage( projectile.getImage(), projectile.getX(), projectile.getY() );
//        }
//    }

//    OldMain.primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
//        @Override
//        public void handle(KeyEvent event) {
//            KeyCode code = event.getCode();
//            if (code == KeyCode.ESCAPE) {
//                // show menu
//            } else if (code == KeyCode.P) {
//                if (!tPaused) {
//                    tPaused = true;
//                } else {
//                    tPaused = false;
//                }
//            } else if (code == KeyCode.RIGHT) {
//                tRightArrow = true;
//                OldMain.game.getPlayer(). rightArrowPressed(tRightArrow);
//            } else if (code == KeyCode.LEFT) {
//                tLeftArrow = true;
//                OldMain.game.getPlayer().leftArrowPressed(tLeftArrow);
//            } else if (code == KeyCode.SPACE) {
//                OldMain.game.getPlayer().fireButtonPressed(true);
//            }
//
//        }
//    });
//
//    OldMain.primaryStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
//        @Override
//        public void handle(KeyEvent event) {
//            KeyCode code = event.getCode();
//            if (code == KeyCode.RIGHT) {
//                tRightArrow = false;
//                OldMain.game.getPlayer().rightArrowPressed(tRightArrow);
//            } else if (code == KeyCode.LEFT) {
//                tLeftArrow = false;
//                OldMain.game.getPlayer().leftArrowPressed(tLeftArrow);
//            } else if (code == KeyCode.SPACE) {
//                OldMain.game.getPlayer().fireButtonPressed(false);
//            }
//        }
//    });

}
