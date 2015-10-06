package application.core.projectiles;

import application.Main;
import application.core.Sprite;

/**
 * Class for projectile.
 * @author Niek van der Laan
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})
public class Projectile extends Sprite {
    protected int tSpeed = 15;
    protected int tHealth = 1;
    protected float tX;
    protected float tY;
    protected float tDirectionY;
    protected boolean tRemoved = false;

    /**
     * Constructor method for the Projectile.
     * @param x x-coordinate of the projectile.
     * @param y y-coordinate of the projectile.
     */
    public Projectile(int x, int y) {
        tDirectionY = 1;
        tX = x;
        tY = y;
    }

    /**
     * Setter method for the Projectile's x direction.
     * @param directionY the integer value.
     */
    public void setDirectionY(int directionY) {
        tDirectionY = directionY;
    }

    /**
     * Update method for the Projectile.
     */
    public void update() {
        tY += tDirectionY * tSpeed;
    }

    /**
     * Check whether the Projectile is out of bounds.
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
        return "Projectile on coords: " + this.getX() + ", " + this.getY();
    }

    /**
     * Getter method for the x-coordinate of the Projectile.
     * @return x-coordinate.
     */
    public int getX() {
        return (int) tX;
    }

    /**
     * Getter method for the y-coordinate of the Projectile.
     * @return y-coordinate.
     */
    public int getY() {
        return (int) tY;
    }

    /**
     * Check whether the Projectile is removed.
     * @return the boolean value.
     */
    public boolean isRemoved() {
        return tRemoved;
    }
}
