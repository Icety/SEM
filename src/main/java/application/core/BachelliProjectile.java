package application.core;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for BachelliProjectile.
 * @author Niek van der Laan.
 */
public class BachelliProjectile extends Projectile {
    protected float tX;
    protected float tY;
    protected float tDirectionX;
    protected float tDirectionY;

    /**
     * Constructor method for the BachelliProjectile.
     * @param x an x-coordinate.
     * @param y a y-coordinate.
     * @param directionX the x-direction of the BachelliProjectile.
     * @param directionY the y-direction of the BachelliProjectile.
     */
    public BachelliProjectile(int x, int y, float directionX, float directionY) {
        this.tDirection = 1;
        this.tX = x;
        this.tY = y;
        this.tSpeed = 5;
        this.tWidth = 50;
        this.tHeight = 70;
        this.tDirectionX = directionX;
        this.tDirectionY = directionY;
    }

    /**
     * Update method for the BachelliProjectile.
     */
    public void update() {
        this.tX += tDirectionX * tSpeed;
        this.tY += tDirectionY * tSpeed;
    }

    /**
     * Getter method for the x-coordinate.
     * @return the x-coordinate of the BachelliProjectile.
     */
    public int getX() {
        return (int) tX;
    }

    /**
     * Getter method for the y-coordinate.
     * @return the y-coordinate of the BachelliProjectile.
     */
    public int getY() {
        return (int) tY;
    }

    /**
     * Getter method for the Image.
     * @return Image belonging to the BachelliProjectile.
     */
    public Image getImage() {
        return Main.BACHELLI_PROJECTILE;
    }
}