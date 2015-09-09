package application.core;


/**
 * Created by Niek on 9/9/2015.
 */
public class BossProjectile extends Projectile{

    public BossProjectile(int x, int y) {
        //tImage = new Image(new File("src/application/images/smallbullet.png").toURI().toString());
        tDirection = 1;
        //Should have a different image
        tImageString = "spaghettiheart.jpg";
        tX = x;
        tY = y;
        tSpeed = 5;
        tWidth = 50;
        tHeight = 70;
    }
}
