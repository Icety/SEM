package application.core;

import application.Main;

/**
 * Created by Thomas on 01-09-15.
 */
public class Player extends Sprite {
    protected int tHealth;
    protected long tLastShot = 0;
    protected int tReloadTime = 1000;
    protected int tSpeed = 5;

    protected boolean tShoot, tGoLeft, tGoRight;

    public Player() {
        tImageString = "Space_Invaders_cannon.png";
        tHealth = 3;
        tHeight = 30;
        tWidth = 80;
    }

    public void update() {
        if(tGoLeft) {
            moveLeft();
        }
        else if(tGoRight) {
            moveRight();
        }
        long time = (System.nanoTime() - tLastShot) / 1000000;
        if(tShoot && time > tReloadTime) {
            tLastShot = System.nanoTime();
            Projectile projectile = new PlayerProjectile(tX + tWidth/2 , tY );
            Main.sGame.addProjectile(projectile);
        }
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void moveLeft() {
        if ( (tX ) > 10 ) {
            tX -= tSpeed;
        }
    }
    protected void moveRight() {
        if ( !((tX + tWidth + 10) > Main.sGame.getWidth() )) {
            tX += tSpeed;
        }
    }

    public String toString() {
        String result = "Player on coords: " + tX + ", " + tY;
        return result;
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

    public void hit() {
        tHealth--;
    }

    public Player getPlayer() {
        return this;
    }

    public int getHealth() {
        return tHealth;
    }
}
