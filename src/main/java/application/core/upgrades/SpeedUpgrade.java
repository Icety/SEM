package application.core.upgrades;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for SpeedUpgrade.
 * @author Ties WesterBorg.
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public final class SpeedUpgrade extends Upgrade {

    /**
     * Constructor method for the SpeedUpgrade.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public SpeedUpgrade(int x, int y) {
        setDirection(1);
        setX(x);
        setY(y);
        setWidth(50);
        setHeight(50);
        setHealth(1);
        if (Main.DIFFICULTY == 1) {
            setSpeed(2);
        }
        else if (Main.DIFFICULTY == 2) {
            setSpeed(3);
        }
        else {
            setSpeed(5);
        }
    }

    /**
     * Getter method for the belonging Image.
     * @return the belonging Image.
     */
    public Image getImage() {
        return Main.UPGRADE_0;
    }
}
