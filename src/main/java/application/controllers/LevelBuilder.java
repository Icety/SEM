package application.controllers;

import application.Main;
import application.core.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.awt.*;
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
        circle = new Circle(circlex,circley, 80);
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


        tBackground.draw(0, 0, container.getWidth(), container.getHeight());

        g.setColor(Color.blue);
        g.fillRect(0, 0, Main.WIDTH, menuHeight);

        g.setColor(Color.white);

        g.drawString("Selected Alien: ", Main.WIDTH / 4, menuHeight/2);

        if (selected != null) {
            selected.getImage().draw(Main.WIDTH / 2, menuHeight / 3, menuHeight / 2, menuHeight / 2);
        }

        for (Alien alien : aliens) {
            alien.getImage().draw(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
        }

        g.setColor(Color.red);
        g.draw(circle);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (circleUp && circle.getMinY() > menuHeight) {
            circley -= 4;
        }
        if (circleDown && circle.getMaxY() < Main.HEIGHT) {
            circley += 4;
        }
        if (circleRight && circle.getMaxX() < Main.WIDTH) {
            circlex += 4;
        }
        if (circleLeft && circle.getMinX() > 0){
            circlex -= 4;
        }
        circle.setCenterX(circlex);
        circle.setCenterY(circley);
    }

    public void keyReleased(int key, char c) {
        switch(key) {
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
               selected = new FinalBoss();
                break;
            case Input.KEY_5:
                selected = new MothershipAlien();
            default:
                break;
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
            case Input.KEY_SPACE:
                if (selected != null) {
                    Alien newAlien = makeAlien();
                    placeAlien(newAlien, circlex - newAlien.getWidth()/2, circley - newAlien.getHeight()/2);
                }
                break;
            case Input.KEY_R:
                Alien toRemove = checkCollision();
                if (checkCollision() != null) {
                    aliens.remove(toRemove);
                }
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

    public void chooseAlien(Alien a) {
        if (a != null) {
            selected = a;
            System.out.println("New selected alien");
        } else {
            System.out.println("chosen is null");
        }
    }

    public Alien checkCollision() {
        for(Alien a: aliens) {
            if(a.getBoundingBox().intersects(circle)) {
                return a;
            }
        }
        System.out.println("collision is null");
        return null;
    }

    public void placeAlien(Alien alien, int x, int y) {
        if (alien != null) {
            alien.settX(x);
            alien.settY(y);
            aliens.add(alien);
        }
    }

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
