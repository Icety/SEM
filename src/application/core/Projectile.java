package application.core;

import application.Main;
import javafx.scene.image.Image;

/**
 * Created by Niek on 9/2/2015.
 */
public class Projectile implements Sprite {
    protected int tX;
    protected int tY;
    protected Image tImage;

    public int getX() {
        return tX;
    }

    public int getY() {
        return tY;
    }

    public Image getImage() {
        return tImage;
    }

    public void moveUp() {
        tY++;
        if( tY + tImage.getHeight() < 0) {
            //delete projectile;
        }
    }

    public void moveDown() {
        tY--;
        if( tY + tImage.getHeight() > Main.getHeight()) {
            //delete projectile;
        }
    }
}
