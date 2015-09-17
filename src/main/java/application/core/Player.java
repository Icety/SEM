package application.core;

import application.Main;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Created by Thomas on 01-09-15.
 */
public class Player extends Sprite {
    protected long tLastShot = 0;
    protected int tReloadTime = 50;
    protected int tSpeed = 5;

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
            laserSound();

            Projectile projectile = new PlayerProjectile(tX + tWidth/2 , tY );
            this.addProjectile(projectile);
        }
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.updateProjectiles();
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
}
