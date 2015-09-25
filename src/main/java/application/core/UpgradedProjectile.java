package application.core;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for UpgradedProjectile.
 * @author Niek van der Laan.
 */
public class UpgradedProjectile extends Projectile {
    protected float tX;
    protected float tY;
    protected float tDirectionX;
    protected float tDirectionY;

    /**
     * Constructor method for UpgradedProjectile.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param directionX x-direction.
     * @param directionY y-direction.
     */
    public UpgradedProjectile(int x, int y, float directionX, float directionY) {
        this.tDirection = 1;
        this.tX = x;
        this.tY = y;
        this.tSpeed = 5;
        this.tWidth = 50;
        this.tHeight = 70;
        this.tDirectionX = -directionX;
        this.tDirectionY = -directionY;
        this.tWidth = 7;
        this.tHeight = 15;
    }

    /**
     * Update method for the UpgradedProjectile.
     */
    public void update() {
        this.tX += tDirectionX * tSpeed;
        this.tY += tDirectionY * tSpeed;
    }

    /**
     * Getter method for the x-coordinate of the UpgradedProjectile.
     * @return x-coordinate.
     */
    public int getX() {
        return (int) tX;
    }

    /**
     * Getter method for the y-coordinate of the UpgradedProjectile.
     * @return y-coordinate.
     */
    public int getY() {
        return (int) tY;
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public Image getImage() {
        return Main.PLAYER_PROJECTILE;
    }
}