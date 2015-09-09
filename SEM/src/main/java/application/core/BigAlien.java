package application.core;

/**
 * Created by Niek on 9/9/2015.
 */
public class BigAlien extends Alien{

    public BigAlien() {
        tImageString = "bigalien.png";
        super.tHealth = 1;
        super.tHitScore = 0;
        super.tKillScore = 40;
        tWidth = 70;
        tHeight = 70;
        tShootChance = 0;
        tDirection = 1;
        tSpeed = 1;
    }

    @Override
    public void addShootChance() {
        tShootChance += Math.random() * 3;
    }
}
