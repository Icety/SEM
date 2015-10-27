package application.core.aliens;

import application.Main;
import application.core.projectiles.BossBeamProjectile;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

/**
 * Class for DaphnalienBoss.
 * @author Thomas Oomens
 */
public class AnimatedBoss extends FinalBoss {

    /**
     * Getter method for the Image.
     * @return Image belonging to the FinalBoss.
     */
    public Image getImage() {
        if (tSecondShot > 150) {
            tAnimate = true;
        }
        return Main.BOSS;
    }

    /**
     * Makes sure an animated image is used.
     * @return null.
     */
    public Animation getAnimation() {
        if (tSecondShot < 150) {
            tAnimate = false;
        }
        return null;
    }
}
