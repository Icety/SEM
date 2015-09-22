package application.core;

import application.Main;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by Ties on 15-9-2015.
 */
public abstract class Upgrade extends Sprite {
    protected int tDirection = 1;
    protected int tSpeed = 8;
    protected boolean tRemoved = false;
    protected long tTime = System.currentTimeMillis();
    protected long tDuration = 15000; //15 sec
    protected boolean tToDraw = true;

    public void setDirection(int direction) {
        tDirection = direction;
    }

    public boolean isOutOfBounds() {
        return ( (tY < 0) || (tY > Main.HEIGHT) );
    }

    public void update() {
        tY += tDirection * tSpeed;
    }

    public String toString() {
        String result = "Upgrade on coords: " + tX + ", " + tY;
        return result;
    }

    @Override
    public int hit() {
        tToDraw = false;
        return 0;
    }

    public boolean isRemoved() {
        return tRemoved;
    }

    public boolean isActive() {
        return tTime + tDuration > System.currentTimeMillis();
    }

    public boolean toDraw() {
        return tToDraw;
    }
}

