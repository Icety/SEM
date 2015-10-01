package application.core;

import application.Main;

/**
 * Class for Upgrade.
 * @author Ties Westerborg.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier",
        "checkstyle:magicnumber"
})
public abstract class Upgrade extends Sprite {
    protected int tDirection = 1;
    protected int tSpeed = 8;
    protected boolean tRemoved = false;
    protected long tTime = System.currentTimeMillis();
    protected long tDuration = 15000; //15 sec
    protected boolean tToDraw = true;

    /**
     * Setter method for the direction of the Upgrade.
     * @param direction the value of the direction.
     */
    public void setDirection(int direction) {
        tDirection = direction;
    }

    /**
     * Check whether upgrade is out of bounds.
     * @return the boolean value.
     */
    public boolean isOutOfBounds() {
        return ((tY < 0) || (tY > Main.HEIGHT));
    }

    /**
     * Update method for the upgrade.
     */
    public void update() {
        tY += tDirection * tSpeed;
    }

    /**
     * Create a String representing the Upgrade.
     * @return the belonging String.
     */
    public String toString() {
        return "Upgrade on coords: " + tX + ", " + tY;
    }

    /**
     * Method to handle hits.
     * @return 0.
     */
    @Override
    public int hit() {
        tToDraw = false;
        return 0;
    }

    /**
     * Check whether the Upgrade is removed.
     * @return the boolean value.
     */
    public boolean isRemoved() {
        return tRemoved;
    }

    /**
     * Check whether the Upgrade is active.
     * @return the boolean value.
     */
    public boolean isActive() {
        return tTime + tDuration > System.currentTimeMillis();
    }

    /**
     * Check whether the Upgrade still has to be spawned.
     * @return the boolean value.
     */
    public boolean toDraw() {
        return tToDraw;
    }
}

