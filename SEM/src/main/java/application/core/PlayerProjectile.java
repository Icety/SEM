package application.core;

import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by Thomas on 02-09-15.
 */
public class PlayerProjectile extends Projectile {



    public PlayerProjectile(int x, int y) {
        tDirection = -1;
        tImageString = "playerProjectile.png";
        tX = x;
        tY = y;

        tWidth = 7;
        tHeight = 15;
    }
}
