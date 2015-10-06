package application.core;

import application.Main;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.w3c.dom.Element;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Class for Alien.
 * @author Thomas Oomens
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})
public class Alien extends Sprite {
    protected boolean tDead = false;
    protected int tShootChance;
    protected int tDirection;
    protected double tSpeed;
    protected boolean tCanShoot = false;
    protected boolean tBonusAlien = false;
    protected ArrayList<Upgrade> tUpgrades = new ArrayList<>();

    /**
     * Constructor for Alien.
     */
    public Alien() {
        applyDifficulty();
    }

    /**
     * Read XML to create Aliens.
     * @param eElement read XML.
     */
    public void readXml(Element eElement) {
        tX = Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
        tY = Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
    }

    /**
     * Update method for the Alien.
     */
    public void update() {
        //ToDo: Dead aliens can't move and shoot anymore, its projectiles should keep moving
        if (!tDead) {
            tX += tDirection * tSpeed;
            shoot();
            addShootChance();
        }
        this.updateProjectiles();
        this.updateUpgrades();
    }

    /**
     * Updates the position of all the upgrades that this alien has dropped.
     */
    protected void updateUpgrades() {
        for (Upgrade u: tUpgrades) {
            u.update();
        }
    }

    /**
     * Checks whether an Alien is a bonus Alien.
     * @return a boolean value.
     */
    public boolean isBonusAlien() {
        return tBonusAlien;
    }

    /**
     * Shoot method for the Alien.
     */
    public void shoot() {
        if (tCanShoot) {
            if ((Math.random() * 100) > tRandomChance) {
                this.addProjectile(new SmallProjectile(tX + tWidth / 2, tY + tHeight));
                tShootChance = 0;
            }
        }
    }

    /**
     * Setter method to determine whether an Alien can shoot or not.
     * @param canShoot boolean value.
     */
    public void setCanShoot(boolean canShoot) {
        tCanShoot = canShoot;
    }

    /**
     * Method to return a String value describing the Alien.
     * @return a String value.
     */
    public String toString() {
        return "Alien on coords: " + tX + ", " + tY;
    }

    /**
     * Check whether the Alien is dead.
     * @return a boolean value.
     */
    public boolean isDead() {
        return tDead;
    }

    /**
     * Method to add shoot chance to the Alien.
     */
    public void addShootChance() {
        tShootChance += Math.random() * 0;
    }

    /**
     * Check whether the Alien is at the end of the screen.
     * @return a boolean value.
     */
    public boolean endOfScreen() {
        return (tX >= (Main.WIDTH - tWidth - 10)
                && tDirection == 1)
                || (tX <= 10 && tDirection == -1);
    }

    /**
     * Method to make the Alien switch direction.
     */
    public void switchDirection() {
        tY += 15;
        tDirection *= -1;
    }

    /**
     * Determines whether the alien is the lowest alien of its column.
     * @param aliens the Aliens to set for the lower Level.
     */
    protected void setLowerLevel(ArrayList<Alien> aliens) {
        int bA = 5;
        int hA = 10;
        Rectangle myBox = new Rectangle(tX + bA,
                Main.HEIGHT,
                (int) tWidth - (2 * bA),
                (int) tHeight - (2 * hA));
        tCanShoot = true;
        for (Alien a: aliens) {
            if (!a.isDead()) {
                Rectangle alienBox = new Rectangle(
                        a.getX() + bA,
                        Main.HEIGHT,
                        (int) a.getWidth() - (2 * bA),
                        (int) a.getHeight() - (2 * hA)
                );
                if (alienBox.getBounds().intersects(myBox)) {
                    if (tY < a.getY()) {
                        tCanShoot = false;
                        return;
                    }
                }
            }
        }
    }

    /**
     * Apply difficulty to Aliens.
     */
    public void applyDifficulty() {
        switch (Main.DIFFICULTY) {
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
            default: break;
        }
    }

    /**
     * Method to make Alien drop an upgrade.
     */
    protected void drop() {
        int c = (int) (Math.random() * 100);
        if (c > 96) {
            tUpgrades.add(new WeaponUpgrade(tX + tWidth / 2, tY + tHeight));
        }
        else if (c > 92) {
            tUpgrades.add(new SpeedUpgrade(tX + tWidth / 2, tY + tHeight));
        }
        else if (c > 88) {
            tUpgrades.add(new HealthUpgrade(tX + tWidth / 2, tY + tHeight));
        }
        else if (c > 84) {
            tUpgrades.add(new PlayerUpgrade(tX + tWidth / 2, tY + tHeight));
        }
    }

    /**
     * Getter method for the upgrades the Alien holds.
     * @return an ArrayList of upgrades.
     */
    public ArrayList<Upgrade> getUpgrades() {
        return tUpgrades;
    }

    /**
     * Method to apply impact of a projectily on the Alien.
     * @return the Score to be added to the total Score.
     */
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