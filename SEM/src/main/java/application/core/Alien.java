package application.core;

import application.Main;
import javafx.scene.image.Image;
import org.w3c.dom.Element;

import java.awt.*;

/**
 * Created by Thomas on 01-09-15.
 */
public class Alien extends Sprite {
    protected int tHealth;
    protected int tHitScore;
    protected int tKillScore;
    protected boolean tRemoved;
    protected int tShootChance;
    protected int tDirection;
    protected double tSpeed;

    public void readXml(Element eElement) {
        tX = Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
        tY = Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
    }

    public void move() {
        tX += tDirection *tSpeed;
        shoot();
    }

    public void shoot() {
        if (isLowerLevel()) {
            if ((Math.random() * 100 > 99.9 ) || tShootChance > 1000) {
                Main.sGame.addProjectile(new smallProjectile(tX+ tWidth/2, tY + tHeight));
                tShootChance = 0;
            }
        }
    }


    /**
     * Checks whether the alien is the lowest alien of its column
     * @return
     */
    private boolean isLowerLevel() {
        int bA = 5;
        int hA = 10;
        Rectangle myBox = new Rectangle(tX + bA,Main.sGame.getHeight(),(int)tWidth-(2*bA),(int)tHeight-(2*hA));
        for(Alien a: Main.sGame.getLevel().getAliens()) {
            Rectangle AlienBox = new Rectangle(a.getX()+bA,Main.sGame.getHeight(),(int)a.getWidth()-(2*bA),(int)a.getHeight()-(2*hA));
            if(AlienBox.getBounds().intersects(myBox)) {
                if (tY < a.getY())
                return false;
            }
        }
        return true;
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

            Main.sGame.setScore(tKillScore);
        } else {
            Main.sGame.setScore(tHitScore);
        }
    }

    public void addShootChance() {
        tShootChance += Math.random() * 0;
    }

    public boolean endOfScreen() {
        return tX == Main.sGame.getWidth() - tWidth - 10 || tX == 10;
    }

    public void switchDirection() {
            tY += 15;
            tDirection *= -1;
    }
}