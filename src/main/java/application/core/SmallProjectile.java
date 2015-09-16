package application.core;

import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by Niek on 9/2/2015.
 */
public class SmallProjectile extends Projectile {

    public SmallProjectile(int x, int y) {
        //tImage = new Image(new File("src/application/images/smallbullet.png").toURI().toString());
        tDirection = 1;
        //Should have a different image
        tImageString = "playerProjectile.png";
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
}
