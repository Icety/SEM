package application.core;


import application.Main;
import org.newdawn.slick.Image;

/**
 * Created by Niek on 9/9/2015.
 */
public class BossProjectile extends Projectile{

    public BossProjectile(int x, int y) {
        //tImage = new Image(new File("src/application/images/smallbullet.png").toURI().toString());
        tDirection = 1;
        //Should have a different image
        tX = x;
        tY = y;
        tSpeed = 5;
        tWidth = 50;
        tHeight = 70;
    }

    public Image getImage() {
        return Main.BOSS_PROJECTILE;
    }
}
