package application.core.projectiles;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for BachelliProjectile.
 * @author Niek van der Laan.
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})
public class BachelliProjectile extends ExtendedProjectile {

    /**
     * Constructor method for the BachelliProjectile.
     * @param x an x-coordinate.
     * @param y a y-coordinate.
     * @param directionX the x-direction of the BachelliProjectile.
     * @param directionY the y-direction of the BachelliProjectile.
     */
    public BachelliProjectile(int x, int y, float directionX, float directionY) {
        super(x, y, directionX, directionY);
        tSpeed = 5;
        tWidth = 50;
        tHeight = 70;
    }

    /**
     * Getter method for the Image.
     * @return Image belonging to the BachelliProjectile.
     */
    public Image getImage() {
        return Main.BOSS_PROJECTILE_SPECIAL;
    }
}