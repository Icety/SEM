package application.core;

/**
 * Created by Ties on 22-9-2015.
 */
public class WeaponUpgrade extends Upgrade {

    public WeaponUpgrade(int x, int y) {
        tDirection = 1;
        tX = x;
        tY = y;
        tWidth = 35;
        tHeight = 50;
        tHealth = 1;
        if (tDifficulty == 1)
            tSpeed = 2;
        else if (tDifficulty == 2)
            tSpeed = 3;
        else
            tSpeed = 5;
    }
}
