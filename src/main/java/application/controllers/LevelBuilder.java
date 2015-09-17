package application.controllers;

import application.Main;
import application.core.*;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayList;

/**
 * Created by Niek on 9/15/2015.
 */
public class LevelBuilder extends BasicGameState {

    protected int tId;
    protected Image tBackground;
    protected String tBackgroundString = "moving.jpg";
    protected Alien selected = new Alien();
    protected int menuHeight;
    protected Circle circle;
    protected int circlex;
    protected int circley;
    protected boolean circleDown, circleUp, circleLeft, circleRight;

    ArrayList<Alien> aliens = new ArrayList<Alien>();

    public LevelBuilder(int id) {
        tId = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {

        circlex = Main.WIDTH/2;
        circley =  Main.HEIGHT/3;
        circle = new Circle(circlex,circley, 70);
        menuHeight = 150;
        selected = null;
        tBackground = new Image("src/main/java/application/images/"+ tBackgroundString);
    }

    public int getID() {
        return tId;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {


        //Draw the background
        tBackground.draw(0, 0, container.getWidth(), container.getHeight());

        //Draw the menu bar
        g.setColor(Color.blue);
        g.fillRect(0, 0, Main.WIDTH, menuHeight);

        //Draw all text
        g.setColor(Color.white);
        g.drawString("Space:  add selected alien", Main.WIDTH / 8, menuHeight / 8);
        g.drawString("R:     Delete alien in circle", Main.WIDTH / 8, 2*menuHeight / 8);
        g.drawString("Arrows: move circle", Main.WIDTH / 8, 3*menuHeight / 8);
        g.drawString("Selected Alien: ", Main.WIDTH / 3, menuHeight / 2);
        g.drawString("1: MiniAlien       ", Main.WIDTH - Main.WIDTH/4, menuHeight / 8);
        g.drawString("2: SmallAlien      ", Main.WIDTH - Main.WIDTH/4, 2*menuHeight / 8);
        g.drawString("3: BigAlien      ", Main.WIDTH - Main.WIDTH/4, 3*menuHeight / 8);
        g.drawString("4: MothershipAlien      ", Main.WIDTH - Main.WIDTH/4, 4*menuHeight / 8);
        g.drawString("5: FinalBoss", Main.WIDTH - Main.WIDTH/4, 5*menuHeight / 8 );

        //Draw the circle
        g.setColor(Color.red);
        g.draw(circle);

        //Display the Alien we have currently selected
        if (selected != null) {
            selected.getImage().draw(Main.WIDTH / 2, menuHeight / 3, menuHeight / 2, menuHeight / 2);
        }

        //Draw all aliens
        for (Alien alien : aliens) {
            alien.getImage().draw(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        //Move circle up
        if (circleUp && circle.getMinY() > menuHeight) {
            circley -= 4;
        }
        //Move circle down
        if (circleDown && circle.getMaxY() < Main.HEIGHT) {
            circley += 4;
        }
        //Move circle right
        if (circleRight && circle.getMaxX() < Main.WIDTH) {
            circlex += 4;
        }
        //Move circle left
        if (circleLeft && circle.getMinX() > 0){
            circlex -= 4;
        }
        //Update circle coordinates
        circle.setCenterX(circlex);
        circle.setCenterY(circley);
    }

    public void keyReleased(int key, char c) {
        switch(key) {

            //Update selected alien
            case Input.KEY_1:
                selected = new MiniAlien();
                break;
            case Input.KEY_2:
                selected = new SmallAlien();
                break;
            case Input.KEY_3:
                selected = new BigAlien();
                break;
            case Input.KEY_4:
                selected = new MothershipAlien();
                break;
            case Input.KEY_5:
                selected = new FinalBoss();
                break;

            //If we stop pressing an arrow, we make the circle stop moving aswell
            case Input.KEY_DOWN:
                    circleDown = false;
                break;
            case Input.KEY_UP:
                    circleUp = false;
                break;
            case Input.KEY_LEFT:
                    circleLeft = false;
                break;
            case Input.KEY_RIGHT:
                    circleRight = false;
                break;

            //Place the selected alien in the middle of our circle
            case Input.KEY_SPACE:
                if (selected != null) {
                    Alien newAlien = makeAlien();
                    placeAlien(newAlien, circlex - newAlien.getWidth()/2, circley - newAlien.getHeight()/2);
                }
                break;

            //Remove the alien inside our circle
            case Input.KEY_R:
                Alien toRemove = checkCollision();
                if (checkCollision() != null) {
                    aliens.remove(toRemove);
                }
                break;
            default:
                break;
        }
    }

    public void keyPressed(int key, char c) {
        switch(key) {
            case Input.KEY_DOWN:
                if (circle.getY() < Main.HEIGHT)
                circleDown = true;
                break;
            case Input.KEY_UP:
                if (circle.getY() > 0)
                circleUp = true;
                break;
            case Input.KEY_LEFT:
                if (circle.getX() > 0)
                circleLeft = true;
                break;
            case Input.KEY_RIGHT:
                if (circle.getX() < Main.WIDTH)
                circleRight = true;
                break;
        }
    }

    /**
     * Checks whether circle is above an alien in the game
     * @return the alien which is under our circle, null if none
     */
    public Alien checkCollision() {
        for(Alien a: aliens) {
            if(a.getBoundingBox().intersects(circle)) {
                return a;
            }
        }
        System.out.println("collision is null");
        return null;
    }

    /**
     * Places an alien according to the parameters
     * @param alien determines which type of alien is to be placed
     * @param x determines the x coordinate
     * @param y determines the y coordinate
     */
    public void placeAlien(Alien alien, int x, int y) {
        if (alien != null) {
            alien.settX(x);
            alien.settY(y);
            aliens.add(alien);
        }
    }

    /**
     * Makes a new alien which is the same type as our selected alien (Alien selected)
     * @return the new made alien
     */
    public Alien makeAlien() {
        Alien alien;

        if (selected instanceof MiniAlien) {
            alien = new MiniAlien();
            return alien;
        } else if (selected instanceof SmallAlien) {
            alien = new SmallAlien();
            return alien;
        } else if (selected instanceof BigAlien) {
            alien = new BigAlien();
            return alien;
        } else if (selected instanceof FinalBoss) {
            alien = new FinalBoss();
            return alien;
        } else if (selected instanceof MothershipAlien) {
            alien = new MothershipAlien();
            return alien;
        } else {
            return null;
        }
    }
}
