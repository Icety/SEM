package application.core.projectiles;

import application.Main;
import org.newdawn.slick.Image;

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
        super(x, y);
        tDirectionY = -1;
        tWidth = 7;
        tHeight = 15;
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public Image getImage() {
        return Main.PLAYER_PROJECTILE;
    }
}
