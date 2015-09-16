package application.core;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Created by Niek on 9/9/2015.
 */
public class FinalBoss extends Alien {
    public FinalBoss() {
        tHealth = 50;
        tHitScore = 0;
        tKillScore = 10000;
        tWidth = 320;
        tHeight = 167;
        tShootChance = 0;
        tDirection = 1;
        tSpeed = 3;
    }

    @Override
    public void addShootChance() {
        tShootChance += Math.random() * 40;
    }

    @Override
    public void shoot() {
        if (tCanShoot) {
            if ((Math.random() * 100 > 99.9 ) || tShootChance > 1000) {
                this.addProjectile(new BossProjectile(tX + tWidth/2, tY + tHeight));
                tShootChance = 0;
            }
        }
    }

    public Image getImage() {
        return Main.BOSS_BACHELLI;
    }

    public boolean endOfScreen() {
        return tX > Main.WIDTH - tWidth - 10 || tX == 10;
    }
}
