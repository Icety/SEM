package application.core;

import application.Main;

/**
 * Class for SmallProjectile.
 * @author Niek van der Laan.
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class SmallProjectile extends Projectile {

    /**
     * Constructor for SmallProjectile.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public SmallProjectile(int x, int y) {
        tDirection = 1;
        tX = x;
        tY = y;
        tWidth = 7;
        tHeight = 15;

        if (Main.DIFFICULTY == 1) {
            tSpeed = 2;
        }
        else if (Main.DIFFICULTY == 2) {
            tSpeed = 3;
        }
        else {
            tSpeed = 5;
        }
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public org.newdawn.slick.Image getImage() {
        return Main.SMALL_PROJECTILE;
    }
}
