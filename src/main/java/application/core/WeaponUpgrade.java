package application.core;

import application.Main;

/**
 * Created by Ties on 22-9-2015.
 */
public class WeaponUpgrade extends Upgrade {

    public WeaponUpgrade(int x, int y) {
        tDirection = 1;
        tX = x;
        tY = y;
        tWidth = 35;
        tHeight = 35;
        tHealth = 1;
        if (tDifficulty == 1)
            tSpeed = 2;
        else if (tDifficulty == 2)
            tSpeed = 2;
        else
            tSpeed = 4;
    }
    public org.newdawn.slick.Image getImage() {
        return Main.UPGRADE_1;
    }
}
