package application.core.projectiles;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for UpgradedProjectile.
 * @author Niek van der Laan.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier",
        "checkstyle:magicnumber"
})
public class UpgradedProjectile extends ExtendedProjectile {
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
        super(x, y, directionX, directionY);
        this.tSpeed = 5;
        this.tWidth = 7;
        this.tHeight = 15;
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public Image getImage() {
        return Main.PLAYER_PROJECTILE;
    }
}