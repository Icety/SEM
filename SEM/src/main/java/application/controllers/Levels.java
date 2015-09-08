package application.controllers;

import application.Main;
import application.OldMain;
import application.core.Alien;
import application.core.Sprite;
import application.core.Player;
import application.core.Projectile;
import javafx.scene.canvas.GraphicsContext;
import org.newdawn.slick.*;
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
    protected ArrayList<String> tKnownImages;
    protected ArrayList<Image> tImages;

    public Levels(int id) {
        tId = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        tMain = (Main) game;
        tAliens = tMain.tGame.getLevel().getAliens();

        for (Alien alien: tAliens) {
            Image image = setImage(alien);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {

        g.setColor(Color.white);

        g.drawString(("SCORE: " + Integer.toString(OldMain.game.getScore())), OldMain.getWidth() - 140, 50);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        tMain.tGame.update();

        for (Alien alien: tAliens) {
                gc.drawImage( alien.getImage(), alien.getX(), alien.getY() );
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

    protected Image setImage(Sprite sprite) throws SlickException {
        int index = tKnownImages.indexOf(sprite.getImage());
        if (index == -1) {
            //Add the image to the list
            Image image = new Image("src/main/java/application/images/" + sprite.getImage());
            tKnownImages.add(sprite.getImage());
            tImages.add(image);
            return image;
        }
        else {
            return tImages.get(index);
        }
    }
}
