package application.core;

import application.Main;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Niek on 9/9/2015.
 */
public class BigAlien extends Alien {

    public BigAlien() {
        tHealth = 1;
        tHitScore = 0;
        tKillScore = 40;
        tWidth = 70;
        tHeight = 70;
        tShootChance = 0;
        tDirection = 1;
        tSpeed = 1;
    }

    public Image getImage() {
        return Main.BIG_ALIEN;
    }

    @Override
    public void addShootChance() {
        tShootChance += Math.random() * 3;
    }

    @Override
    public String toString() {
        return "BigAlien on coords: " + tX + ", " + tY;
    }
}
