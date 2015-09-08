package application.core;

import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by Thomas on 01-09-15.
 */
public class SmallAlien extends Alien {

    public SmallAlien() {
        tImage = new Image(new File("src/application/images/smallAlien.png").toURI().toString());
        super.tHealth = 2;
        super.tHitScore = 10;
        super.tKillScore = 50;
    }
}
