package application.core.aliens;

import application.Main;
import application.core.projectiles.BossBeamProjectile;
import org.newdawn.slick.Animation;

/**
 * Class for DaphnalienBoss.
 * @author Thomas Oomens
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class DaphnalienBoss extends AnimatedBoss {
    /**
     * Constructor method for DaphnalienBoss.
     */
    public DaphnalienBoss() {
        super();
        tWidth = 320;
        tHeight = 282;
        tChargeHeight = 358;
        tNormalHeight = 282;
        tChargeUp = false;
    }

    /**
     * Add the rainbow beam projectile.
     * @param x The x position on which the projectile should start.
     * @param y The y position on which the projectile should start.
     */
    protected void shootMain(int x, int y) {
        this.addProjectile(new BossBeamProjectile(x, y));
    }

    /**
     * Get the animation belonging to the boss.
     * @return the animation.
     */
    public Animation getAnimation() {
        super.getAnimation();
        return Main.DAPHNALIEN;
    }
}
