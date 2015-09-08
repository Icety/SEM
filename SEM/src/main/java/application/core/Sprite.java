package application.core;


import org.newdawn.slick.Image;

/**
 * Created by Thomas on 02-09-15.
 */
public class Sprite {
    int tX;
    int tY;
    int tWidth, tHeight, tScreenWidth, tScreenHeight;
    String tImage;

    public int getX() {
        return tX;
    }

    public int getY() {
        return tY;
    }

    public String getImage() {
        return tImage;
    }

    public void setGraphics(int width, int height, int screenWidth, int screenHeight) {
        tWidth = width;
        tHeight = height;
        tScreenWidth = screenWidth;
        tScreenHeight = screenHeight;
    }
}
