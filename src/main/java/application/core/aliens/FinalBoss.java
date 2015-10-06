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
            if (tSecondShot == 151) {
                tY -= 83;
            }
            if (tSecondShot > 250) {
                tHeight = 167;
                tY += 83;
                tSecondShot = 0;
                int x, y;
                float dirx, diry;
                for (int i = 0; i < 10; i++) {
                    x = tX + i * tWidth / 10;
                    y = tY + tHeight;
                    dirx = x - (tX + tWidth / 2);
                    diry = y;
                    this.addProjectile(new BachelliProjectile(
                            x, y,
                            dirx / Math.max(dirx, diry), diry / Math.max(dirx, diry)));
                }
            }
        }
        if (canShoot()) {
            if ((Math.random() * 100 > 99.9) || tShootChance > 1000) {
                this.addProjectile(new BossProjectile(tX + tWidth / 2, tY + tHeight));
                tShootChance = 0;
            }
        }
    }

    /**
     * Getter method for the Image.
     * @return Image belonging to the FinalBoss.
     */
    public Image getImage() {
        if (tSecondShot > 150) {
            tHeight = 250;
            return Main.BOSS_BACHELLI_CHARGE;
        } else {
            return Main.BOSS_BACHELLI;
        }
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

    public int getSecondShot() {
        return tSecondShot;
    }

    public void setSecondShot(int tSecondShot) {
        this.tSecondShot = tSecondShot;
    }
}
