package application.core;

import application.Main;

/**
 * Created by Niek on 9/9/2015.
 */
public class FinalBoss extends Alien{
    public FinalBoss() {
        tImageString = "finalbossbachelli.png";
        super.tHealth = 50;
        super.tHitScore = 0;
        super.tKillScore = 10000;
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
        if (isLowerLevel()) {
            if ((Math.random() * 100 > 99.9 ) || tShootChance > 1000) {
                Main.sGame.addProjectile(new BossProjectile(tX+ tWidth/2, tY + tHeight));
                tShootChance = 0;
            }
        }
    }

    public boolean endOfScreen() {
        return tX > Main.sGame.getWidth() - tWidth - 10 || tX == 10;
    }
}
