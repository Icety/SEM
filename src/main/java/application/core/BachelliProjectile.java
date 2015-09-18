package application.core;


import application.Main;
import org.newdawn.slick.Image;

/**
 * Created by Niek on 9/9/2015.
 */
public class BachelliProjectile extends Projectile{
    protected float tX, tY, tDirectionX, tDirectionY;

    public BachelliProjectile(int x, int y, float directionX, float directionY) {
        //tImage = new Image(new File("src/application/images/smallbullet.png").toURI().toString());
        tDirection = 1;
        //Should have a different image
        tX = x;
        tY = y;
        tSpeed = 5;
        tWidth = 50;
        tHeight = 70;
        tDirectionX = directionX;
        tDirectionY = directionY;
    }

    public void update() {
        tX += tDirectionX * tSpeed;
        tY += tDirectionY * tSpeed;
    }

    public int getX() {
        return (int) tX;
    }

    public int getY() {
        return (int) tY;
    }

    public Image getImage() {
        return Main.BACHELLI_PROJECTILE;
    }
}
