package application.core.projectiles;

import application.Main;

/**
 * Class for projectile.
 * @author Niek van der Laan
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})
public class ExtendedProjectile extends Projectile {
    protected float tDirectionX;
    protected boolean tRemoved = false;

    /**
     * Constructor method for the Projectile.
     * @param x x-coordinate of the projectile.
     * @param y y-coordinate of the projectile.
     * @param directionX x-direction.
     * @param directionY y-direction.
     */
    public ExtendedProjectile(int x, int y, float directionX, float directionY) {
        super(x, y);
        tDirectionY = directionY;
        tDirectionX = directionX;
    }

    /**
     * Setter method for the Projectile's x direction.
     * @param directionX the integer value.
     */
    public void setDirectionX(int directionX) {
        tDirectionX = directionX;
    }

    /**
     * Update method for the Projectile.
     */
    @Override
    public void update() {
        tX += tDirectionX * tSpeed;
        tY += tDirectionY * tSpeed;
    }
}
