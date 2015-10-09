package application.core.upgrades;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for WeaponUpgrade.
 * @author Ties WesterBorg.
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class WeaponUpgrade extends Upgrade {

    /**
     * Constructor for WeaponUpgrade.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public WeaponUpgrade(int x, int y) {
        setDirection(1);
        setX(x);
        setY(y);
        setWidth(50);
        setHeight(50);
        setHealth(1);
        if (getDifficulty() == 1) {
            setSpeed(2);
        }
        else if (getDifficulty() == 2) {
            setSpeed(2);
        }
        else {
            setSpeed(4);
        }
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public Image getImage() {
        return Main.UPGRADE_1;
    }
}
