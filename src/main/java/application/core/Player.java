package application.core;

import application.Main;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class for Player.
 * @author Thomas Oomens
 */
public class Player extends Sprite {
    protected long tLastShot = 0;
    protected int tReloadTime = 10;
    protected int tSpeed = 5;
    protected ArrayList<Upgrade> tActiveUpgrades = new ArrayList<>();
    protected boolean tUpgraded = false;
    protected int tLastSide = 0;
    protected boolean tShoot, tGoLeft, tGoRight;

    /**
     * Constructor for Player.
     */
    public Player() {
        tHealth = 3;
        tHeight = 30;
        tWidth = 80;
    }

    /**
     * Update method for the Player.
     * @throws SlickException
     */
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
        updateUpgrade();
    }

    /**
     * Updater for the upgrades of the Player.
     */
    public void updateUpgrade() {
        boolean hasWeaponUpgrade = false;
        tReloadTime = 250;

        for (Upgrade u : tActiveUpgrades) {
            if (u.isActive() && u instanceof SpeedUpgrade && !hasWeaponUpgrade) {
                tReloadTime = 50;
            }
            if (u.isActive() && u instanceof WeaponUpgrade) {
                tReloadTime = 500;
                hasWeaponUpgrade = true;
            }
        }
    }

    /**
     * Shoot method for the Player.
     * @throws SlickException
     */
    protected void shoot() throws SlickException {
        int bestWeapon = 0;
        for(Upgrade u: tActiveUpgrades) {
            if(u instanceof WeaponUpgrade && u.isActive()) {
                bestWeapon = 1;
            }
        }

        if(bestWeapon == 0 ) {
            shootWeaponZero();
        }
        else if(bestWeapon == 1) {
            shootWeaponOne();
        }
    }

    /**
     * Shoot method for when best weapon is zero.
     * @throws SlickException
     */
    public void shootWeaponZero() throws SlickException {
        if(tUpgraded) {
            if(tLastSide == 0) {
                laserSound();
                Projectile projectile = new PlayerProjectile(tX + 5, tY);
                this.addProjectile(projectile);
                tLastSide = 1;
            }
            else {
                laserSound();
                Projectile projectile2 = new PlayerProjectile(tX + tWidth - 10, tY);
                this.addProjectile(projectile2);
                tLastSide = 0;
            }
        }
        else {
            laserSound();
            Projectile projectile = new PlayerProjectile(tX + tWidth / 2, tY);
            this.addProjectile(projectile);
        }
    }

    /**
     * Shoot method for when best weapon is one.
     * @throws SlickException
     */
    public void shootWeaponOne() throws SlickException {
        int amount = 3;
        if(tUpgraded) {
            amount = 6;
        }
        int x, y;
        float dirx, diry;
        for (int i=0; i<amount; i++) {
            laserSound();
            x = tX + i * tWidth / amount;
            y = tY;
            dirx = -(x - (tX + tWidth / 2)) * 4;
            diry = y;
            this.addProjectile(new UpgradedProjectile(x, y, dirx / Math.max(dirx, diry), diry / Math.max(dirx, diry)));
        }
    }

    /**
     * Make the Player move Left.
     */
    public void moveLeft() {
        if ( (tX ) > 10 ) {
            tX -= tSpeed;
        }
    }

    /**
     * Make the Player more right.
     */
    public void moveRight() {
        if ( !((tX + tWidth + 10) > Main.WIDTH )) {
            tX += tSpeed;
        }
    }

    /**
     * Create a String which represents the Player.
     * @return the belonging String value.
     */
    public String toString() {
        return "Player on coords: " + tX + ", " + tY;
    }

    /**
     * Getter method for the Image belonging to the Player.
     * @return the Image belonging to the Player.
     */
    public Image getImage() {
        if(!tUpgraded)
        return Main.PLAYER;
        return Main.UPGRADED_PLAYER;
    }

    /**
     * The sound belonging to the shots fired.
     * @throws SlickException
     */
    public void laserSound() throws SlickException {
        Sound laser = new Sound("src/main/java/application/sound/shoot.wav");
        laser.play();
    }

    /**
     * Handle the left arrow pressed event.
     * @param pressed the boolean value.
     */
    public void leftArrowPressed(boolean pressed) {
        tGoLeft = pressed;
    }

    /**
     * Handle the right arrow pressed event.
     * @param pressed the boolean value.
     */
    public void rightArrowPressed(boolean pressed) {
        tGoRight = pressed;
    }

    /**
     * Handle the spacebar pressed event.
     * @param pressed the boolean value.
     */
    public void fireButtonPressed(boolean pressed) {
        tShoot = pressed;
    }

    /**
     * Handle a hit on the Player.
     * @return the Integer killScore.
     */
    public int hit() {
        tHealth--;
        return tKillScore;
    }

    /**
     * Getter method for the Player.
     * @return this.
     */
    public Player getPlayer() {
        return this;
    }

    /**
     * Getter method for the Player' health.
     * @return the health Integer value.
     */
    public int getHealth() {
        return tHealth;
    }

    /**
     * Upgrade the player with a given upgrade.
     * @param u the given upgrade.
     */
    public void upgrade(Upgrade u) {
        if(u instanceof HealthUpgrade && tHealth < 3) {
            tHealth++;
        }
        if(u instanceof PlayerUpgrade) {
            tUpgraded = true;
        }
        else {
            tActiveUpgrades.add(u);
        }
    }

    public void moveUp(int y) {
        tY -= y;
    }
}
