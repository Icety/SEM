package application.core;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Created by Niek on 9/8/2015.
 */
public class MothershipAlien extends Alien{

    public MothershipAlien() {
        tImageString = "mothership.png";
        tHealth = 1;
        tHitScore = 0;
        tKillScore = 500;
        tWidth = 50;
        tHeight= 30;
        tShootChance = 0;
        tSpeed = 3;
        tDirection = 1;
        tBonusAlien = true;
    }

    public Image getImage() {
        return Main.MOTHERSHIP_ALIEN;
    }

    @Override
    public void switchDirection() {
        if (tX > Main.WIDTH + 2000 || tX < -2000) {
            tDirection *= -1;
        }
    }

    public boolean isAlive() {
        return tHealth > 0;
    }

    @Override
    public String toString() {
        return "MotherShipAlien on coords: " + tX + ", " + tY;
    }
}
