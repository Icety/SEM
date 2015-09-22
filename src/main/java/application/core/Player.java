package application.core;

import application.Main;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Thomas on 01-09-15.
 */
public class Player extends Sprite {
    protected long tLastShot = 0;
    protected int tReloadTime = 250;
    protected int tSpeed = 5;
    private ArrayList<Upgrade> tActiveUpgrades = new ArrayList<>();

    protected boolean tShoot, tGoLeft, tGoRight;

    public Player() {
        tHealth = 3;
        tHeight = 30;
        tWidth = 80;
    }

    public void update() throws SlickException {
        if(tGoLeft) {
            moveLeft();
        }
        else if(tGoRight) {
            moveRight();
        }
        long time = (System.nanoTime() - tLastShot) / 1000000;
        if(tShoot && time > tReloadTime) {
            tLastShot = System.nanoTime();
            shoot();
        }
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.updateProjectiles();

        //Update Upgrades
            //Reset everything
        boolean hasWeaponUpgrade = false;
        tReloadTime = 250;

            //Apply new reload times and delete inactive Upgrades
        Iterator<Upgrade> it = tActiveUpgrades.iterator();
        while(it.hasNext()) {
            Upgrade u = it.next();
            if(u.isActive() && u instanceof SpeedUpgrade && !hasWeaponUpgrade) {
                tReloadTime = 50;
            }
            if(u.isActive() && u instanceof WeaponUpgrade) {
                tReloadTime = 500;
                hasWeaponUpgrade = true;
            }
            if(!u.isActive()) {
                //tActiveUpgrades.remove(u);
            }
        }
    }

    private void shoot() throws SlickException {
        int bestWeapon = 0;
        for(Upgrade u: tActiveUpgrades) {
            if(u instanceof WeaponUpgrade && u.isActive()) {
                bestWeapon = 1          ;
            }
        }

        if(bestWeapon == 0 ) {
            laserSound();
            Projectile projectile = new PlayerProjectile(tX + tWidth / 2, tY);
            this.addProjectile(projectile);
        }
        else if(bestWeapon == 1) {
            for (int i=0; i<5; i++) {
                int x = tX + i * tWidth / 10;
                int y = tY + tHeight;
                int dirx = x - (tX + tWidth / 2);
                int diry = y;
                this.addProjectile(new BachelliProjectile(x, y, dirx / Math.max(dirx, diry), -diry / Math.max(dirx, diry)));
            }
        }
    }

    protected void moveLeft() {
        if ( (tX ) > 10 ) {
            tX -= tSpeed;
        }
    }
    protected void moveRight() {
        if ( !((tX + tWidth + 10) > Main.WIDTH )) {
            tX += tSpeed;
        }
    }

    public String toString() {
        String result = "Player on coords: " + tX + ", " + tY;
        return result;
    }

    public Image getImage() {
        return Main.PLAYER;
    }

    public void laserSound() throws SlickException {
        Sound laser = new Sound("src/main/java/application/sound/shoot.wav");
        laser.play();
    }

    public void leftArrowPressed(boolean pressed) {
        tGoLeft = pressed;
    }

    public void rightArrowPressed(boolean pressed) {
        tGoRight = pressed;
    }

    public void fireButtonPressed(boolean pressed) {
        tShoot = pressed;
    }

    public int hit() {
        tHealth--;
        return tKillScore;
    }

    public Player getPlayer() {
        return this;
    }

    public int getHealth() {
        return tHealth;
    }

    public void upgrade(Upgrade u) {
        tActiveUpgrades.add(u);
    }
}
