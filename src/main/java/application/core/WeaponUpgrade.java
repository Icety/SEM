package application.core;

import application.Main;

/**
 * Class for WeaponUpgrade.
 * @author Ties WesterBorg.
 */
public class WeaponUpgrade extends Upgrade {

    /**
     * Constructor for WeaponUpgrade.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public WeaponUpgrade(int x, int y) {
        tDirection = 1;
        tX = x;
        tY = y;
        tWidth = 50;
        tHeight = 50;
        tHealth = 1;
        if (tDifficulty == 1)
            tSpeed = 2;
        else if (tDifficulty == 2)
            tSpeed = 2;
        else
            tSpeed = 4;
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public org.newdawn.slick.Image getImage() {
        return Main.UPGRADE_1;
    }
}
