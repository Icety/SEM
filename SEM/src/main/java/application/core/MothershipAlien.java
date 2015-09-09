package application.core;

/**
 * Created by Niek on 9/8/2015.
 */
public class MothershipAlien extends Alien{

    public MothershipAlien() {
        tImageString = "mothership.png";
        super.tHealth = 1;
        super.tHitScore = 0;
        super.tKillScore = 500;
        tWidth = 50;
        tHeight= 30;
        tShootChance = 0;
    }
}
