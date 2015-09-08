package application.controllers;

import application.Main;
import application.core.Alien;
import application.core.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

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
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {

        g.setColor(Color.white);

        g.drawString(("SCORE: " + Integer.toString(Main.sGame.getScore())), container.getWidth() - 140, 50);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        Main.sGame.update();

        for (Alien alien: Main.sGame.getLevel().getAliens()) {
            (alien.getImage()).draw(alien.getX(), alien.getY());
        }
    }

    @Override
    public int getID() {
        return tId;
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
