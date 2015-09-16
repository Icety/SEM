package application.core;

import application.Main;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Niek on 9/2/2015.
 */
public class Projectile extends Sprite {
    protected int tDirection = 1;
    protected int tSpeed = 15;
    protected int tHealth = 1;
    protected boolean tRemoved = false;
    protected ArrayList<Sprite> tHitList = new ArrayList<>();

    public void setDirection(int direction) {
        tDirection = direction;
    }

    public void update() {
        tY += tDirection * tSpeed;
    }

    public boolean isOutOfBounds() {
        return ( (tY < 0) || (tY > Main.HEIGHT) );
    }

    public String toString() {
        String result = "Projectile on coords: "+ tX +", "+ tY;
        return result;
    }

    public boolean isRemoved() {
        return tRemoved;
    }
}
