package application.core;

import application.Main;
import javafx.scene.image.Image;
import org.w3c.dom.Element;

import java.io.File;

/**
 * Created by Thomas on 01-09-15.
 */
public class Player implements Sprite {
    protected int tX;
    protected int tY;
    protected Image tImage;
    protected int tHealth;

    public Player() {
        tImage = new Image(new File("src/application/images/smallAlien.png").toURI().toString());
        tX = (int)(Main.getWidth()/2 - tImage.getWidth()/2);
        tY = (int)(Main.getHeight()-tImage.getHeight()-50);
        tHealth = 1;
    }

    public void readXml(Element eElement) {
      //Unused
    }

    public int getX() {
        return tX;
    }

    public int getY() {
        return tY;
    }

    public Image getImage() {
        return tImage;
    }

    public void moveLeft() {
        if ( !((tX - tImage.getWidth() - 10) < 0 )) {
            tX--;
        }
    }
    public void moveRigth() {
        if ( !((tX + tImage.getWidth() + 10) > Main.getWidth() )) {
            tX++;
        }
    }

    public String toString() {
        String result = "Player on coords: "+ tX +", "+ tY;
        return result;
    }
}
