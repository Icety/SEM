package application.controllers;

import application.Main;
import application.core.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Rectangle;
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
    protected String tBackgroundString;
    protected Alien selected = new Alien();
    protected int mousex;
    protected int mousey;

    ArrayList<Alien> aliens = new ArrayList<Alien>();

    public LevelBuilder(int id) {
        tId = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {

        mousex = 0;
        mousey = 0;
        selected = null;
        //Add all aliens to list
        aliens.add(new MiniAlien());
        aliens.add(new SmallAlien());
        aliens.add(new BigAlien());
        aliens.add(new MothershipAlien());
        aliens.add(new FinalBoss());


        int x;
        int y = 10;
        int count = 0;
        for (Alien alien : aliens) {
            alien.settY(y);
            if (count % 2 == 0) {
                alien.settX(10);
                alien.getImage().draw(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
            } else {
                alien.settX(80);
                alien.getImage().draw(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
                y += 70;
            }
            count++;
        }
    }

    public int getID() {
        return tId;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {


        g.setColor(Color.white);

        for (Alien alien : aliens) {
            alien.getImage().draw(alien.getX(), alien.getY(), 70, 70);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        mousex = container.getInput().getMouseX();
        mousey = container.getInput().getMouseY();
    }

    public void mousePressed(int button, int x, int y) {

    }

    public void mouseReleased(int button, int x, int y) {

        //selecting type of alien
        if (mousex < 200) {
            if (button == Input.MOUSE_LEFT_BUTTON) {
               chooseAlien(checkCollision(mousex, mousey));
                System.out.println("checked collision");
            }
        } else {
            if (selected != null) {
                //Place the currently selected alien on the field
                if (button == Input.MOUSE_LEFT_BUTTON) {
                    placeAlien(makeAlien(), mousex, mousey);
                }
            }
        }
        if (button == Input.MOUSE_RIGHT_BUTTON) {
            if (Mouse.getX() > 200) {
                Alien a = checkCollision(x, y);
                if (a != null) {
                    aliens.remove(checkCollision(x, y));
                }
            }
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

    public Alien checkCollision(int x, int y) {
        Rectangle mouseBox = new Rectangle(x, y, 10, 10);
        System.out.println("MOUSE LOCATION: " + x + ", " + y);
        for(Alien a: aliens) {
            System.out.println("ALIEN LOCATION: " + a.getX() + ", " + a.getY());
            if(a.getBoundingBox().intersects(mouseBox)) {
                return a;
            }
        }
        System.out.println("collision is null");
        return null;
    }

    public void placeAlien(Alien alien, int x, int y) {
        alien.settX(x);
        alien.settY(y);
        aliens.add(alien);
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
