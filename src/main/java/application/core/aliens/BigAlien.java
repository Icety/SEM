package application.core.aliens;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for BigAlien.
 * @author Niek van der Laan
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class BigAlien extends Alien {

    /**
     * Constructor method for BigAlien.
     */
    public BigAlien() {
        tHealth = 1;
        tHitScore = 0;
        tKillScore = 40;
        tWidth = 70;
        tHeight = 70;
        tShootChance = 0;
        tDirection = 1;
        tSpeed = 1;
    }

    /**
     * Getter method for the Image.
     * @return the Image belonging to BigAlien.
     */
    public Image getImage() {
        return Main.BIG_ALIEN;
    }

    /**
     * Method to add shoot chance to the BigAlien.
     */
    @Override
    public void addShootChance() {
        tShootChance += Math.random() * 3;
    }

    /**
     * Create a readable representation of the BigAlien.
     * @return a String value of the BigAlien.
     */
    @Override
    public String toString() {
        return "BigAlien on coords: " + tX + ", " + tY;
    }
}
