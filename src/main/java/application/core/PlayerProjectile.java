package application.core;

import application.Main;
import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by Thomas on 02-09-15.
 */
public class PlayerProjectile extends Projectile {



    public PlayerProjectile(int x, int y) {
        tDirection = -1;
        tX = x;
        tY = y;

        tWidth = 7;
        tHeight = 15;
    }

    public org.newdawn.slick.Image getImage() {
        return Main.PLAYER_PROJECTILE;
    }
}
