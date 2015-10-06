package application.core.aliens;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for MiniAlien.
 * @author Niek vand der Laan.
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class MiniAlien extends Alien {

    /**
     * Constructor for MiniAlien.
     */
    public MiniAlien() {
        super.tHealth = 1;
        super.tHitScore = 0;
        super.tKillScore = 40;
        tWidth = 70;
        tHeight = 70;
        tShootChance = 0;
        tDirection = 1;
        tSpeed = 1;
    }

    /**
     * Getter method for the belonging Image.
     * @return the Image.
     */
    public Image getImage() {
        return Main.MINI_ALIEN;
    }

    /**
     * Add shoot chance to the Alien.
     */
    @Override
    public void addShootChance() {
        tShootChance += Math.random() * 10;
    }

    /**
     * Create a String representing the MiniAlien.
     * @return the belonging String value.
     */
    @Override
    public String toString() {
        return "MiniAlien on coords: " + tX + ", " + tY;
    }
}
