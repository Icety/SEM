package application.core;

import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by Thomas on 01-09-15.
 */
public class SmallAlien extends Alien {

    public SmallAlien() {
        tImageString = "smallAlien.png";
        super.tHealth = 2;
        super.tHitScore = 10;
        super.tKillScore = 50;
        tWidth = 50;
        tHeight = 50;
    }
}