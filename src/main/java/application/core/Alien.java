package application.core;

import application.Main;
import org.w3c.dom.Element;

import javax.xml.crypto.NodeSetData;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Thomas on 01-09-15.
 */
public class Alien extends Sprite {
    protected boolean tRemoved;
    protected int tShootChance;
    protected int tDirection;
    protected double tSpeed;
    protected boolean tCanShoot = false;
    protected boolean tBonusAlien = false;

    protected ArrayList<Upgrade> tUpgrade = new ArrayList<>();

    public void readXml(Element eElement) {
        tX = Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
        tY = Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
    }

    public void update() {
        tX += tDirection * tSpeed;
        shoot();
        addShootChance();
        this.updateProjectiles();
        this.updateUpgrades();
    }

    public boolean isBonusAlien() {
        return tBonusAlien;
    }

    public void shoot() {
        if (tCanShoot) {
            if ((Math.random() * 100) > tRandomChance ) {
                this.addProjectile(new SmallProjectile(tX+ tWidth/2, tY + tHeight));
                tShootChance = 0;
            }
        }
    }

    public void setCanShoot(boolean canShoot) {
        tCanShoot = canShoot;
    }

    public String toString() {
        String result = "Alien " + this.getClass().getSimpleName() + " on coords: " + tX + ", " + tY;
        return result;
    }

    public boolean isRemoved() {
        return tRemoved;
    }

    public void addShootChance() {
        tShootChance += Math.random() * 0;
    }

    public boolean endOfScreen() {
        //return tX >= Main.WIDTH - tWidth - 10 || tX <= 10;
        return tX == Main.WIDTH - tWidth - 10 || tX == 10;
    }

    public void switchDirection() {
        tY += 15;
        tDirection *= -1;
        drop();// Well, this shouldnt be here. But since every Sprite whitin Alien is deleted when an Alien is killed, this is for testing.
    }

    /**
     * Checks whether the alien is the lowest alien of its column
     * @return
     */
    protected void setLowerLevel(ArrayList<Alien> aliens) {
        int bA = 5;
        int hA = 10;
        Rectangle myBox = new Rectangle(tX + bA, Main.HEIGHT, (int) tWidth - (2 * bA), (int) tHeight - (2 * hA));
        for(Alien a: aliens) {
            Rectangle AlienBox = new Rectangle(
                    a.getX() + bA,
                    Main.HEIGHT,
                    (int) a.getWidth() - (2 * bA),
                    (int) a.getHeight() - (2 * hA)
            );
            if(AlienBox.getBounds().intersects(myBox)) {
                if (tY < a.getY())
                    tCanShoot = false;
            }
        }
        tCanShoot = true;
    }
    public void applyDifficulty() {
        int d = tDifficulty;
        if(d == 1) {
            tSpeed = 1;
            tRandomChance = 99.8;
        }
        else if(d == 2) {
            tSpeed = 2;
            tRandomChance = 99.9;
        }
        else {
            tSpeed = 3;
            tRandomChance = 99.5;
        }
    }

    protected void drop() {
        if(Math.random()*100>90) {
            tUpgrade.add(new WeaponUpgrade(tX + tWidth / 2, tY + tHeight));
        }
    }

    public ArrayList<Upgrade> getUpgrades() {
        return tUpgrade;
    }

    protected void updateUpgrades() {
//        for(Upgrade u : tUpgrade) {
//            if(!u.isActive()) {
//                tUpgrade.remove(u);
//                System.out.println("Upgrade deleted");
//            }
//        }
    }
}