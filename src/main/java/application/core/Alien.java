package application.core;

import application.Main;
import org.w3c.dom.Element;

import javax.xml.crypto.NodeSetData;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Thomas on 01-09-15.
 */
public class Alien extends Sprite {
    protected boolean tDead = false;
    protected int tShootChance;
    protected int tDirection;
    protected double tSpeed;
    protected boolean tCanShoot = false;
    protected boolean tBonusAlien = false;

    protected ArrayList<Upgrade> tUpgrades = new ArrayList<>();

    public Alien() {
        applyDifficulty();
    }

    public void readXml(Element eElement) {
        tX = Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
        tY = Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
    }

    public void update() {
        //If dead, the alien can't move and shoot anymore, yet it's projectiles should keep moving
        if (!tDead) {
            tX += tDirection * tSpeed;
            shoot();
            addShootChance();
        }
        this.updateProjectiles();
        this.updateUpgrades();
    }

    /**
     * Updates the position of all the upgrades that this alien dropped
     */
    protected void updateUpgrades() {
        for(Upgrade u: tUpgrades) {
            u.update();
        }
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
        return "Alien on coords: " + tX + ", " + tY;
    }

    public boolean isDead() {
        return tDead;
    }

    public void addShootChance() {
        tShootChance += Math.random() * 0;
    }

    public boolean endOfScreen() {
        return (tX >= (Main.WIDTH - tWidth - 10) && tDirection == 1) || (tX <= 10 && tDirection == -1);
    }

    public void switchDirection() {
        tY += 15;
        tDirection *= -1;
    }

    /**
     * Checks whether the alien is the lowest alien of its column
     * @return
     */
    protected void setLowerLevel(ArrayList<Alien> aliens) {
        int bA = 5;
        int hA = 10;
        Rectangle myBox = new Rectangle(tX + bA, Main.HEIGHT, (int) tWidth - (2 * bA), (int) tHeight - (2 * hA));
        tCanShoot = true;
        for(Alien a: aliens) {
            if (!a.isDead()) {
                Rectangle AlienBox = new Rectangle(
                        a.getX() + bA,
                        Main.HEIGHT,
                        (int) a.getWidth() - (2 * bA),
                        (int) a.getHeight() - (2 * hA)
                );
                if (AlienBox.getBounds().intersects(myBox)) {
                    if (tY < a.getY()) {
                        tCanShoot = false;
                        return;
                    }
                }
            }
        }
    }

    public void applyDifficulty() {
        switch(Main.DIFFICULTY) {
            case 1:
                tSpeed = 1;
                tRandomChance = 99.8;
                break;
            case 2:
                tSpeed = 2;
                tRandomChance = 99.9;
                break;
            case 3:
                tSpeed = 3;
                tRandomChance = 99.5;
                break;
        }
    }

    protected void drop() {
        if(Math.random() * 100 > 97) {
            tUpgrades.add(new WeaponUpgrade(tX + tWidth / 2, tY + tHeight));
        }
        else if(Math.random() * 100 > 97 ) {
            tUpgrades.add(new SpeedUpgrade(tX + tWidth / 2, tY + tHeight));
        }
        else if(Math.random() * 100 > 98 ) {
            tUpgrades.add(new HealthUpgrade(tX + tWidth / 2, tY + tHeight));
        }
        else if(Math.random() * 100 > 98 ) {
            tUpgrades.add(new PlayerUpgrade(tX + tWidth / 2, tY + tHeight));
        }
    }

    public ArrayList<Upgrade> getUpgrades() {
        return tUpgrades;
    }

    @Override
    public int hit() {
        int result = super.hit();
        if (tHealth <= 0) {
            tDead = true;
            this.drop();
        }
        return result;
    }
}