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
    protected int tDirection = 1;
    protected int tSpeed = 15;

    public int getX() {
        return tX;
    }

    public int getY() {
        return tY;
    }

    public Image getImage() {
        return tImage;
    }

    public void setDirection(int direction) {
        tDirection = direction;
    }

    public void move() {
        tY += tDirection * tSpeed;
        if( tY + tImage.getHeight() < 0) {
            //delete projectile;
        }
        if( tY + tImage.getHeight() > Main.getHeight()) {
            //delete projectile;
        }
    }

    public String toString() {
        String result = "Projectile on coords: "+ tX +", "+ tY;
        return result;
    }
}
