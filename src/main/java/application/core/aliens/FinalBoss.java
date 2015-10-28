package application.core.aliens;

import application.Main;
import application.core.projectiles.BachelliProjectile;
import application.core.projectiles.BossProjectile;
import org.newdawn.slick.Image;

/**
 * Class for FinalBoss.
 * @author Niek van der Laan
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})

public class FinalBoss extends Alien {
    protected int tSecondShot = 0;
    protected int tChargeHeight;
    protected int tNormalHeight;
    protected boolean tChargeUp;
    protected boolean tCharging = false;

    /**
     * Constructor method for FinalBoss.
     */
    public FinalBoss() {
        tHealth = 50;
        tHitScore = 0;
        tKillScore = 10000;
        tWidth = 320;
        tHeight = 167;
        tNormalHeight = 167;
        tChargeHeight = 250;
        tShootChance = 0;
        tDirection = 1;
        tSpeed = 3;
        tChargeUp = true;
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
        if (canShoot()) {
            this.handleShot();
        }
    }

    /**
     * Getter method for the Image.
     * @return Image belonging to the FinalBoss.
     */
    public Image getImage() {
        if (tSecondShot > 150) {
            return Main.BOSS_CHARGE;
        } else {
            return Main.BOSS;
        }
    }

    /**
     * Handle the shooting of the special weapon.
     */
    protected void handleSpecial() {
        if (tSecondShot == 151) {
            this.changeImageY();
        }
        if (tSecondShot > 250) {
            tSecondShot = 0;
            this.changeImageY();
            int x, y;
            float dirx, diry;
            for (int i = 0; i < 10; i++) {
                x = tX + i * tWidth / 10;
                y = tY + tHeight;
                dirx = x - (tX + tWidth / 2);
                diry = y;
                this.shootSpecial(x, y, dirx / Math.max(dirx, diry), diry / Math.max(dirx, diry));
            }
        }
    }

    /**
     * Handle the shooting of the normal weapon.
     */
    protected void handleShot() {
        if ((Math.random() * 100 > 99.9) || tShootChance > 1000) {
            shootMain(tX + tWidth / 2, tY + tHeight);
            tShootChance = 0;
        }
    }

    /**
     * Add the projectile.
     * @param x The x position on which the projectile should start.
     * @param y The y position on which the projectile should start.
     */
    protected void shootMain(int x, int y) {
        this.addProjectile(new BossProjectile(x, y));
    }

    /**
     * Add the special projectile.
     * @param x The x position on which the projectile should start.
     * @param y The y position on which the projectile should start.
     * @param dirx The normalized x direction in which the projectile should move.
     * @param diry The normalized y direction in which the projectile should move.
     */
    protected void shootSpecial(int x, int y, float dirx, float diry) {
        this.addProjectile(new BachelliProjectile(x, y, dirx, diry));
    }

    /**
     * Method to return a readable representation for the FinalBoss.
     * @return the String value.
     */
    @Override
    public String toString() {
        return "FinalBoss on coords: " + tX + ", " + tY;
    }

    /**
     * Used to change image of boss.
     * @return tSecondShot.
     */
    public int getSecondShot() {
        return tSecondShot;
    }

    /**
     * Setter for tSecondShot.
     * @param tSecondShot the value to set tSecondShot to.
     */
    public void setSecondShot(int tSecondShot) {
        this.tSecondShot = tSecondShot;
    }

    /**
     * Method to align alien properly when 'upgraded'.
     */
    protected void changeImageY() {
        if (!tCharging) {
            if (tChargeUp) {
                tY -= (tChargeHeight - tNormalHeight);
            }
            tHeight = tChargeHeight;
        } else {
            if (tChargeUp) {
                tY += (tChargeHeight - tNormalHeight);
            }
            tHeight = tNormalHeight;
        }
        tCharging = !tCharging;
    }
}
