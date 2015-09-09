package application.core;

import application.Main;
import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by Thomas on 01-09-15.
 */
public class SmallAlien extends Alien {

    public SmallAlien() {
        tImageString = "smallAlien.png";
        super.tHealth = 1;
        super.tHitScore = 0;
        super.tKillScore = 20;
        tWidth = 70;
        tHeight = 70;
        tShootChance = 0;
        tDirection = 1;
        tSpeed = 1;
    }

    @Override
    public void addShootChance() {
        tShootChance += Math.random() * 5;
    }
}
