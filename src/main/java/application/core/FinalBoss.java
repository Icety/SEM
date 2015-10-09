package application.core;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Class for FinalBoss.
 * @author Niek van der Laan
 */
public class FinalBoss extends Alien {
    protected int tSecondShot = 0;

    /**
     * Constructor method for FinalBoss.
     */
    public FinalBoss() {
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
     * Method to add shoot chance to the FinalBoss.
     */
    @Override
    public void addShootChance() {
        tShootChance += Math.random() * 40;
    }

    /**
     * Method to make the FinalBoss shoot.
     */
    @Override
    public void shoot() {
        tSecondShot++;
        if (tSecondShot > 150) {
            this.handleSpecial();
        }
        if (tCanShoot) {
            this.handleShot();
        }
    }

    /**
     * Getter method for the Image.
     * @return Image belonging to the FinalBoss.
     */
    public Image getImage() {
        if (tSecondShot > 150) {
            tHeight = 250;
            return Main.BOSS_CHARGE;
        } else {
            return Main.BOSS;
        }
    }

    /**
     * Handle the shooting of the special weapon
     */
    protected void handleSpecial() {
        if (tSecondShot == 151) {
            tY -= 83;
        }
        if (tSecondShot > 250) {
            tHeight = 167;
            tY += 83;
            tSecondShot = 0;
            int x, y;
            float dirx, diry;
            for (int i=0; i<10; i++) {
                x = tX + i * tWidth / 10;
                y = tY + tHeight;
                dirx = x - (tX + tWidth / 2);
                diry = y;
                this.shootSpecial(x, y, dirx / Math.max(dirx, diry), diry / Math.max(dirx, diry));
            }
        }
    }

    /**
     * Handle the shooting of the normal weapon
     */
    protected void handleShot() {
        if ((Math.random() * 100 > 99.9 ) || tShootChance > 1000) {
            shootMain(tX + tWidth/2, tY + tHeight);
            tShootChance = 0;
        }
    }

    /**
     * Add the projectile
     * @param x The x position on which the projectile should start
     * @param y The y position on which the projectile should start
     */
    protected void shootMain(int x, int y) {
        this.addProjectile(new BossProjectile(x, y));
    }

    /**
     * Add the special projectile
     * @param x The x position on which the projectile should start
     * @param y The y position on which the projectile should start
     * @param dirx The normalized x direction in which the projectile should move
     * @param diry The normalized y direction in which the projectile should move
     */
    protected void shootSpecial(int x, int y, float dirx, float diry) {
        this.addProjectile(new BachelliProjectile(x, y, dirx, diry));
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
