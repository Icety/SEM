package application.core;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for Barrier.
 * @author Ties Westerborg.
 */
public class Barrier extends Sprite {

    /**
     * Constructor method for Barrier.
     * @param x x-coordinate of the barrier.
     * @param y y-coordinate of the barrier.
     */
    public Barrier(int x, int y) {
        tX = x;
        tY = y;
        tWidth = 70;
        tHeight = 20;
        tHealth = 8;
    }

    /**
     * Getter method for the Image of the Barrier.
     * @return the Image.
     */
    public Image getImage() {
        switch (tHealth) {
            case 8: return Main.Barier_1;
            case 7: return Main.Barier_2;
            case 6: return Main.Barier_3;
            case 5: return Main.Barier_4;
            case 4: return Main.Barier_5;
            case 3: return Main.Barier_6;
            case 2: return Main.Barier_7;
            case 1: return Main.Barier_8;
            default: return Main.Barier_8;
        }
    }

    /**
     * Handler method for hit.
     * @return the amount of health the Barrier has.
     */
    public int hit() {
        tHealth--;
        return tHealth;
    }
}
