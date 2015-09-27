package application.core;

import application.Main;

/**
 * Class for HealthUpgrade.
 * @author Ties Westerborg
 */
public final class HealthUpgrade extends Upgrade {

    /**
     * Constructor for HealthUpgrade.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public HealthUpgrade(int x, int y) {
        tDirection = 1;
        tX = x;
        tY = y;
        tWidth = 50;
        tHeight = 50;
        tHealth = 1;
        if (Main.DIFFICULTY == 1)
            tSpeed = 2;
        else if (Main.DIFFICULTY == 2)
            tSpeed = 3;
        else
            tSpeed = 5;
    }

    /**
     * Getter method for the Image.
     * @return the belonging Image.
     */
    public org.newdawn.slick.Image getImage() {
        return Main.UPGRADE_2;
    }
}
