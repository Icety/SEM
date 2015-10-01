package application.core;

import application.Main;

/**
 * Class for PlayerUpgrade.
 * @author Ties WesterBorg
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class PlayerUpgrade extends Upgrade {

    /**
     * Constructor for PlayerUpgrade.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public PlayerUpgrade(int x, int y) {
        tDirection = 1;
        tX = x;
        tY = y;
        tWidth = 50;
        tHeight = 50;
        tHealth = 1;
        if (Main.DIFFICULTY == 1) {
            tSpeed = 2;
        }
        else if (Main.DIFFICULTY == 2) {
            tSpeed = 2;
        }
        else {
            tSpeed = 4;
        }
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public org.newdawn.slick.Image getImage() {
        return Main.UPGRADE_3;
    }
}
