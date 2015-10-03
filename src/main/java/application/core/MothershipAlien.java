package application.core;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for MotherShipAlien.
 * @author Niek van der Laan
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class MothershipAlien extends Alien {

    /**
     * Constructor for MotherShipAlien.
     */
    public MothershipAlien() {
        tHealth = 1;
        tHitScore = 0;
        tKillScore = 500;
        tWidth = 50;
        tHeight = 30;
        tShootChance = 0;
        tSpeed = 3;
        tDirection = 1;
        tBonusAlien = true;
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public Image getImage() {
        return Main.MOTHERSHIP_ALIEN;
    }

    /**
     * Method to make MotherShipAlien change direction.
     */
    @Override
    public void switchDirection() {
        if (tX > Main.WIDTH + 2000 || tX < -2000) {
            tDirection *= -1;
        }
    }

    /**
     * Check whether MotherShipAlien is alive.
     * @return the boolean value.
     */
    public boolean isAlive() {
        return tHealth > 0;
    }

    /**
     * Create a String representing the MotherShipAlien.
     * @return the belonging String value.
     */
    @Override
    public String toString() {
        return "MotherShipAlien on coords: " + tX + ", " + tY;
    }
}
