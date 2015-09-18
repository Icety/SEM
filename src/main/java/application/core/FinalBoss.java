package application.core;

import application.Main;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Created by Niek on 9/9/2015.
 */
public class FinalBoss extends Alien {
    protected int tSecondShot = 0;

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

    @Override
    public void addShootChance() {
        tShootChance += Math.random() * 40;
    }

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
                for (int i=0; i<10; i++) {
                    x = tX + i * tWidth / 10;
                    y = tY + tHeight;
                    dirx = x - (tX + tWidth / 2);
                    diry = y;
                    this.addProjectile(new BachelliProjectile(x, y, dirx / Math.max(dirx, diry), diry / Math.max(dirx, diry)));
                }
            }
        }
        if (tCanShoot) {
            if ((Math.random() * 100 > 99.9 ) || tShootChance > 1000) {
                this.addProjectile(new BossProjectile(tX + tWidth/2, tY + tHeight));
                tShootChance = 0;
            }
        }
    }

    public Image getImage() {
        if (tSecondShot > 150) {
            tHeight = 250;
            return Main.BOSS_BACHELLI_CHARGE;
        } else {
            return Main.BOSS_BACHELLI;
        }
    }

    public boolean endOfScreen() {
        return tX > Main.WIDTH - tWidth - 10 || tX == 10;
    }

    @Override
    public String toString() {
        return "FinalBoss on coords: " + tX + ", " + tY;
    }

    public void laserSound() throws SlickException {
        Sound sound = new Sound("src/main/java/application/sound/laser.wav");
        sound.play();
    }
}
