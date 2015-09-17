package application.core;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Created by Niek on 9/9/2015.
 */
public class MiniAlien extends Alien{

    public MiniAlien() {
        super.tHealth = 1;
        super.tHitScore = 0;
        super.tKillScore = 40;
        tWidth = 70;
        tHeight = 70;
        tShootChance = 0;
        tDirection = 1;
        tSpeed = 1;
    }

    public Image getImage() {
        return Main.MINI_ALIEN;
    }

    @Override
    public void addShootChance() {
        tShootChance += Math.random() * 10;
    }

    @Override
    public String toString() {
        return "MiniAlien on coords: " + tX + ", " + tY;
    }
}
