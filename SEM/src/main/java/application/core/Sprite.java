package application.core;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Thomas on 02-09-15.
 */
public class Sprite {
    int tX;
    int tY;
    int tWidth, tHeight, tScreenWidth, tScreenHeight;
    Image tImage = null;
    String tImageString;

    public int getX() {
        return tX;
    }

    public int getY() {
        return tY;
    }

    public Image getImage() throws SlickException {
        if (tImage == null) {
            tImage = new Image("src/main/java/application/images/" + tImageString);
        }
        return tImage;
    }
}
