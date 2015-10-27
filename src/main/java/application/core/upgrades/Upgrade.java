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
        tY += tDirection * tSpeed;
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

    /**
     * Getter method for the Direction.
     * @return the Direction
     */
    public int getDirection() {
        return tDirection;
    }

    /**
     * Getter method for the Speed.
     * @return the Speed
     */
    public int getSpeed() {
        return tSpeed;
    }

    /**
     * Setter method to set the Speed.
     * @param tSpeed to speed to set tSpeed to.
     */
    public void setSpeed(int tSpeed) {
        this.tSpeed = tSpeed;
    }

    /**
     * Setter method to set if this Upgrade should be removed.
     * @param tRemoved the value to set tRemoved to.
     */
    public void setRemoved(boolean tRemoved) {
        this.tRemoved = tRemoved;
    }

    /**
     * Getter method for the Time.
     * @return the Time
     */
    public long getTime() {
        return tTime;
    }

    /**
     * Setter method to set the Time.
     * @param tTime the value to set tTime to.
     */
    public void setTime(long tTime) {
        this.tTime = tTime;
    }

    /**
     * Getter method for the Duration of the Upgrade.
     * @return the Duration
     */
    public long getDuration() {
        return tDuration;
    }

    /**
     * Setter method to set the Duration of the Upgrade.
     * @param tDuration duration of the Upgrade.
     */
    public void setDuration(long tDuration) {
        this.tDuration = tDuration;
    }

    /**
     * Getter method to check if this Upgrade should be drawn.
     * @return true if to draw.
     */
    public boolean isToDraw() {
        return tToDraw;
    }

    /**
     * Setter to set if this Upgrade should be drawn.
     * @param tToDraw the value to set tToDraw to to.
     */
    public void setToDraw(boolean tToDraw) {
        this.tToDraw = tToDraw;
    }
}

