package application.core;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for DaphnalienBoss.
 * @author Thomas Oomens
 */
public class DaphnalienBoss extends FinalBoss {
    protected int tSecondShot = 0;

    /**
     * Constructor method for DaphnalienBoss.
     */
    public DaphnalienBoss() {
        tHealth = 50;
        tHitScore = 0;
        tKillScore = 10000;
        tWidth = 320;
        tHeight = 167;
        tShootChance = 0;
        tDirection = 1;
        tSpeed = 3;
    }

    /**
     * Method to check whether FinalBoss is at the end of the screen.
     * @return the boolean value.
     */
    public boolean endOfScreen() {
        return tX > Main.WIDTH - tWidth - 10 || tX == 10;
    }

    /**
     * Method to return a readable representation for the FinalBoss.
     * @return the String value.
     */
    @Override
    public String toString() {
        return "FinalBoss on coords: " + tX + ", " + tY;
    }
}
