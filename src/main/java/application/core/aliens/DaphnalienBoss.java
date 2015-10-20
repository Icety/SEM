package application.core.aliens;

import application.Main;
import application.core.projectiles.BossBeamProjectile;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import application.core.aliens.FinalBoss;
import org.newdawn.slick.SpriteSheet;

/**
 * Class for DaphnalienBoss.
 * @author Thomas Oomens
 */
public class DaphnalienBoss extends AnimatedBoss {
    protected int tSecondShot = 0;

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
     * Add the rainbow beam projectile
     * @param x The x position on which the projectile should start
     * @param y The y position on which the projectile should start
     */
    protected void shootMain(int x, int y) {
        this.addProjectile(new BossBeamProjectile(x, y));
    }

    public Animation getAnimation() {
        super.getAnimation();
        return Main.DAPHNALIEN;
    }
}
