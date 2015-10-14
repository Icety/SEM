package application;

import application.core.Sprite;
import org.newdawn.slick.Image;

/**
 * Created by Ties on 13-10-2015.
 */
public class Barier extends Sprite {
    public Barier(int x, int y) {
        tX = x;
        tY = y;
        tWidth = 70;
        tHeight = 20;
        tHealth = 8;
    }

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

    public int hit() {
        tHealth--;
        return tHealth;
    }
}
