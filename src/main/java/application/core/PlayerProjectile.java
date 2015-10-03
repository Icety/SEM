package application.core;

import application.Main;

/**
 * Class for PlayerProjectile.
 * @author Thomas Oomens
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class PlayerProjectile extends Projectile {

    /**
     * Constructor for PlayerProjectile.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public PlayerProjectile(int x, int y) {
        tDirection = -1;
        tX = x;
        tY = y;
        tWidth = 7;
        tHeight = 15;
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public org.newdawn.slick.Image getImage() {
        return Main.PLAYER_PROJECTILE;
    }
}
