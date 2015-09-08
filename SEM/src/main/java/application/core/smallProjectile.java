package application.core;

import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by Niek on 9/2/2015.
 */
public class smallProjectile extends Projectile {

    public smallProjectile(int x, int y) {
        //tImage = new Image(new File("src/application/images/smallbullet.png").toURI().toString());
        tDirection = 1;
        //Should have a different image
        tImageString = "playerProjectile.png";
        tX = x;
        tY = y;
        tSpeed = 5;

        tWidth = 10;
        tHeight = 25;
    }
}
