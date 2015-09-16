package application.core;

import application.Main;
import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by Niek on 9/2/2015.
 */
public class SmallProjectile extends Projectile {

    public SmallProjectile(int x, int y) {
        //tImage = new Image(new File("src/application/images/smallbullet.png").toURI().toString());
        tDirection = 1;
        tX = x;
        tY = y;
        tSpeed = 5;
        tWidth = 7;
        tHeight = 15;
    }

    public org.newdawn.slick.Image getImage() {
        return Main.SMALL_PROJECTILE;
    }
}
