package application.core;

import application.Main;
import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by Niek on 9/2/2015.
 */
public class SmallProjectile extends Projectile {

    public SmallProjectile(int x, int y) {
        tDirection = 1;
        tX = x;
        tY = y;
        tWidth = 7;
        tHeight = 15;

        if(tDifficulty == 1)
            tSpeed = 2;
        else if(tDifficulty == 2)
            tSpeed = 3;
        else
            tSpeed = 5;
    }

    public org.newdawn.slick.Image getImage() {
        return Main.SMALL_PROJECTILE;
    }
}
