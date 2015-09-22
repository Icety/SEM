package application.core;

import application.Main;

/**
 * Created by Ties on 15-9-2015.
 */
public final class HealthUpgrade extends Upgrade {

    public HealthUpgrade(int x, int y) {
        tDirection = 1;
        tX = x;
        tY = y;
        tWidth = 35;
        tHeight = 35;
        tHealth = 1;
        if (tDifficulty == 1)
            tSpeed = 2;
        else if (tDifficulty == 2)
            tSpeed = 3;
        else
            tSpeed = 5;
    }
    public org.newdawn.slick.Image getImage() {
        return Main.UPGRADE_2;
    }
}
