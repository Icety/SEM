package application.core.aliens;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for SmallAlien.
 * @author Thomas Oomens
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class SmallAlien extends Alien {

    /**
     * Constructor method for SmallAlien.
     */
    public SmallAlien() {
        tHealth = 1;
        tHitScore = 0;
        tKillScore = 20;
        tWidth = 70;
        tHeight = 70;
        tShootChance = 0;
        tDirection = 1;
        tSpeed = 1;
    }

    /**
     * Getter method for the beloging Image.
     * @return the belonging Image.
     */
    public Image getImage() {
        return Main.SMALL_ALIEN;
    }

    /**
     * Method to add shoot chance to the SmallAlien.
     */
    @Override
    public void addShootChance() {
        tShootChance += Math.random() * 5;
    }

    /**
     * Create a String representing the SmallAlien.
     * @return the belonging String.
     */
    @Override
    public String toString() {
        return "SmallAlien on coords: " + tX + ", " + tY;
    }
}
