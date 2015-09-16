package application.core;

import application.Main;
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
    protected double tRandomChance;

    public void readXml(Element eElement) {
        tX = Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
        tY = Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
    }

    public void move() {
        tX += tDirection * tSpeed;
        shoot();
        applyDifficulty(); //This should only happen on init.
    }

    public void shoot() {
        if (isLowerLevel()) {
            if ((Math.random() * 100) > tRandomChance ) {
                Main.sGame.addProjectile(new SmallProjectile(tX+ tWidth / 2, tY + tHeight));
                tShootChance = 0;
            }
        }
    }


    /**
     * Checks whether the alien is the lowest alien of its column
     * @return
     */
    protected boolean isLowerLevel() {
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
            drop();
            tRemoved = true;

            Main.sGame.setScore(tKillScore);
        } else {
            Main.sGame.setScore(tHitScore);
        }
    }

    private void drop() {
        if(Math.random()*100>90) {
            Main.sGame.getLevel().addUpgrade(new WeaponUpgrade(tX + tWidth / 2, tY + tHeight));
            //Main.sGame.addProjectile(new WeaponUpgrade(tX + tWidth / 2, tY + tHeight));
        }
    }

    public void addShootChance() {
        tShootChance += Math.random() * 0;
    }

    public boolean endOfScreen() {
        return tX >= Main.sGame.getWidth() - tWidth - 10 || tX <= 10;
    }

    public void switchDirection() {
            tY += 15;
            tDirection *= -1;
    }

    public void applyDifficulty() {
        int d = tDifficulty;
        if(d == 1) {
            tSpeed = 1;
            tRandomChance = 99.5;
        }
        else if(d == 2) {
            tSpeed = 2;
            tRandomChance = 99.0;
        }
        else {
            tSpeed = 3;
            tRandomChance = 98.6;
        }
    }
}