package application.core;

import application.Main;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.File;

/**
 * Created by Thomas on 01-09-15.
 */
public class SmallAlien extends Alien {
    public SmallAlien() {
        tHealth = 1;
        tHitScore = 0;
        tKillScore = 20;
        tWidth = 70;
        tHeight = 70;
        tShootChance = 0;
        tDirection = 1;
        tSpeed = 1;
    }

    public Image getImage() {
        return Main.SMALL_ALIEN;
    }

    @Override
    public void addShootChance() {
        tShootChance += Math.random() * 5;
    }

    @Override
    public String toString() {
        return "SmallAlien on coords: " + tX + ", " + tY;
    }
}
