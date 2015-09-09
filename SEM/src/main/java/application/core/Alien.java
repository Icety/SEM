package application.core;

import application.Main;
import javafx.scene.image.Image;
import org.w3c.dom.Element;

/**
 * Created by Thomas on 01-09-15.
 */
public class Alien extends Sprite {
    protected int tHealth;
    protected int tHitScore;
    protected int tKillScore;
    protected boolean tRemoved;

    public void readXml(Element eElement) {
        tX = Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
        tY = Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
    }

    public void move() {
        tX++;
        if ((tX + 10) > Main.sGame.getWidth()) {
            tY += 10;
            tX = 10;
        }
        shoot();
    }

    public void shoot() {
        if (Math.random() * 100 > 99.5 && isLowerLevel()) {
            Main.sGame.addProjectile(new smallProjectile(tX+ tWidth/2, tY + tHeight));
        }
    }

    private boolean isLowerLevel() {
        for(Alien a: Main.sGame.getLevel().getAliens()) {
            if(a.getX() > (tX - tWidth /2) || (tX + tWidth /2)  > a.getX()) {
                if(a.getY() > tY) {
                    return false;
                }
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
}