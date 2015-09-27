package application.core;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for BossProjectile.
 * @author Thomas Oomens
 */
public class BossProjectile extends Projectile{

    /**
     * Constructor method for the BossProjectile.
     * @param x x-coordinate of the projectile.
     * @param y y-coordinate of the projectile.
     */
    public BossProjectile(int x, int y) {
        tDirection = 1;
        tX = x;
        tY = y;
        tSpeed = 5;
        tWidth = 50;
        tHeight = 70;
    }

    /**
     * Getter method for the belonging Image.
     * @return the Image belonging to BossProjectile.
     */
    public Image getImage() {
        return Main.BOSS_PROJECTILE;
    }
}
