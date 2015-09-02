package application.core;

import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by Niek on 9/2/2015.
 */
public class smallProjectile extends Projectile {

    public smallProjectile() {
        tImage = new Image(new File("src/application/images/smallbullet.png").toURI().toString());
    }
}
