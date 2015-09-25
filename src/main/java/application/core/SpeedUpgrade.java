package application.core;

import application.Main;

/**
 * Class for SpeedUpgrade.
 * @author Ties WesterBorg.
 */
public final class SpeedUpgrade extends Upgrade {

    /**
     * Constructor method for the SpeedUpgrade.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public SpeedUpgrade(int x, int y) {
        tDirection = 1;
        tX = x;
        tY = y;
        tWidth = 50;
        tHeight = 50;
        tHealth = 1;
        if (tDifficulty == 1)
            tSpeed = 2;
        else if (tDifficulty == 2)
            tSpeed = 3;
        else
            tSpeed = 5;
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public org.newdawn.slick.Image getImage() {
        return Main.UPGRADE_0;
    }
}
