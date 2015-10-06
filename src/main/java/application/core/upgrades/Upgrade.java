package application.core.upgrades;

import application.Main;
import application.core.Sprite;

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
        return ((getY() < 0) || (getY()> Main.HEIGHT));
    }

    /**
     * Update method for the upgrade.
     */
    public void update() {
        setY(tDirection * tSpeed);
    }

    /**
     * Create a String representing the Upgrade.
     * @return the belonging String.
     */
    public String toString() {
        return "Upgrade on coords: " + getX() + ", " + getY();
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

    public int getDirection() {
        return tDirection;
    }

    public int getSpeed() {
        return tSpeed;
    }

    public void setSpeed(int tSpeed) {
        this.tSpeed = tSpeed;
    }

    public void setRemoved(boolean tRemoved) {
        this.tRemoved = tRemoved;
    }

    public long getTime() {
        return tTime;
    }

    public void setTime(long tTime) {
        this.tTime = tTime;
    }

    public long getDuration() {
        return tDuration;
    }

    public void setDuration(long tDuration) {
        this.tDuration = tDuration;
    }

    public boolean isToDraw() {
        return tToDraw;
    }

    public void setToDraw(boolean tToDraw) {
        this.tToDraw = tToDraw;
    }
}

