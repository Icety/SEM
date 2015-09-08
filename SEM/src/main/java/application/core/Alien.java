package application.core;

import application.OldMain;
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
        if ((tX + 10) > OldMain.getWidth()) {
            tY += 10;
            tX = 10;
        }
        shoot();
    }

    public void shoot() {
        if (Math.random() * 100 > 98 && isLowerLevel()) {
            OldMain.game.addProjectile(new smallProjectile(tX, tY));
        }
    }

    private boolean isLowerLevel() {
        int y = 0;
        for(Alien a: OldMain.game.getLevel().getAliens()) {
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

            OldMain.game.setScore(tKillScore);
        } else {
            OldMain.game.setScore(tHitScore);
        }
    }
}