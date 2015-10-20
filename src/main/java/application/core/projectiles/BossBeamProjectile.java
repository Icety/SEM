package application.core.projectiles;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for SmallProjectile.
 * @author Niek van der Laan.
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class BossBeamProjectile extends Projectile {
    protected int tCount = 0;

    /**
     * Constructor for SmallProjectile.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public BossBeamProjectile(int x, int y) {
        super(x, y);
        tWidth = 25 * Main.DIFFICULTY;
        tHeight = 10;
    }

    /**
     * Update method for the beam, making the beam bigger the first 50 ticks, after that move it down.
     */
    public void update() {
        if (tCount < 30) {
            tHeight += 50;
        } else {
            tY += 50;
        }
        tCount++;
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public Image getImage() {
        return Main.BOSS_BEAM_PROJECTILE;
    }
}
