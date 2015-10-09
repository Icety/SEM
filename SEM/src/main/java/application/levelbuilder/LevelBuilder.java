package application.levelbuilder;

import application.core.aliens.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Niek on 9/15/2015.
 */
public class LevelBuilder extends BasicGameState {

    protected int tId;
    protected String tBackgroundString;
    protected Alien selected = new Alien();
    ArrayList<Alien> aliens = new ArrayList<Alien>();

    public LevelBuilder(int id) {
        tId = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {

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

        if(selected != null) {
            selected.getImage().draw(selected.getX(), selected.getY(), selected.getWidth(), selected.getHeight());
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    }

    public void mousePressed(int button, int x, int y) {

    }

    public void mouseDragged(int button, int x, int y) {
       //     selected.settX(x);
        //    selected.settY(y);
    }

    public void mouseReleased(int button, int x, int y) {

    //selecting type of alien
    if (x < 100) {
        if (button == Input.MOUSE_LEFT_BUTTON) {
            //Selecting alien type
            int bA = 5;
            int hA = 10;
            Rectangle mouseBox = new Rectangle(x, y,1,1);
            for(Alien a: aliens) {
                Rectangle alienBox = new Rectangle(a.getX()+bA,a.getY()+5,(int)a.getWidth()-(2*bA),(int)a.getHeight()-(2*hA));
                if(alienBox.getBounds().intersects(mouseBox)) {
                    selected = a;
                }
            }
        }
    } else {
            if (selected != null) {
                //Place the currently selected alien on the field
                if (button == Input.MOUSE_LEFT_BUTTON) {
                    selected.settX(x);
                    selected.settY(y);
                    aliens.add(selected);
                }
            }
    }
        if (button == Input.MOUSE_RIGHT_BUTTON) {
                if (Mouse.getX() > 100)
                    aliens.remove(selected);
        }
    }
}
