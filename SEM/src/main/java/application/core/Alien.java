package application.core;

import application.Main;
import javafx.scene.image.Image;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by Thomas on 01-09-15.
 */
public class Alien implements Sprite {
    protected int tX;
    protected int tY;
    protected Image tImage;
    protected int tHealth;
    protected int tHitScore;
    protected int tKillScore;
    protected boolean tRemoved;

    public void readXml(Element eElement) {
        tX = Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
        tY = Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
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

    public void move() {
        tX++;
        if ((tX + tImage.getWidth() + 10) > Main.getWidth()) {
            tY += tImage.getHeight() + 10;
            tX = 10;
        }
        shoot();
    }

    public void shoot() {
        if (Math.random() * 100 > 98 && isLowerLevel()) {
            Main.game.addProjectile(new smallProjectile(tX + (int) (tImage.getWidth() / 2), (int) (tY + tImage.getHeight())));
        }
    }

    private boolean isLowerLevel() {
        int y = 0;
        for(Alien a: Main.game.getLevel().getAliens()) {
            if(a.getY() > y ) {
                y = a.getY();
            }
        }
        return y == tY;
    }

    public String toString() {
        String result = "Alien on coords: " + tX + ", " + tY;
        return result;
    }

    public boolean isRemoved() {
        return tRemoved;
    }

    public void hit() {
        tHealth--;
        if (tHealth <= 0) {
            //delete the alien.
            tRemoved = true;

            Main.game.setScore(tKillScore);
        } else {
            Main.game.setScore(tHitScore);
        }
    }
}