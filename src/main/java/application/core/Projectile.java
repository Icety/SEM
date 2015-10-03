package application.core;

import application.Main;

/**
 * Class for projectile.
 * @author Niek van der Laan
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})
public class Projectile extends Sprite {
    protected int tDirection = 1;
    protected int tSpeed = 15;
    protected int tHealth = 1;
    protected boolean tRemoved = false;

    /**
     * Setter method for the Projectile's direction.
     * @param direction the integer value.
     */
    public void setDirection(int direction) {
        tDirection = direction;
    }

    /**
     * Update method for the Projectile.
     */
    public void update() {
        tY += tDirection * tSpeed;
    }

    /**
     * Check whethet the Projectile is out of bounds.
     * @return the boolean value.
     */
    public boolean isOutOfBounds() {
        return ((tY < 0) || (tY > Main.HEIGHT));
    }

    /**
     * Create a String representing the Projectile.
     * @return the belonging String.
     */
    public String toString() {
        return "Projectile on coords: " + tX + ", " + tY;
    }

    /**
     * Check whether the Projectile is removed.
     * @return the boolean value.
     */
    public boolean isRemoved() {
        return tRemoved;
    }
}
