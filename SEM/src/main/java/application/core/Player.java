package application.core;

import application.OldMain;

/**
 * Created by Thomas on 01-09-15.
 */
public class Player extends Sprite {
    protected int tHealth;
    protected long tLastShot = 0;
    protected int tReloadTime = 150;
    protected int tSpeed = 5;

    protected boolean tShoot, tGoLeft, tGoRight;

    public Player() {
        tImage ="smallAlien.png";
        tHealth = 3;
    }

    @Override
    public void setGraphics(int width, int height, int screenWidth, int screenHeight) {
        super.setGraphics(width, height, screenWidth, screenHeight);
        tX = screenWidth / 2;
        tY = screenHeight - height - 10;
    }

    public void update(long time) {
        if(tGoLeft) {
            moveLeft();
        }
        else if(tGoRight) {
            moveRight();
        }
        if(tShoot && time > tReloadTime) {
            System.out.println("Shoot");
            tLastShot = System.nanoTime();
            Projectile projectile = new PlayerProjectile(tX + tWidth/2, tY - 10);
            //projectile.addHit(getPlayer());
            OldMain.game.addProjectile(projectile);
        }
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void moveLeft() {
        if ( (tX) > 10 ) {
            tX -= tSpeed;
        }
    }
    protected void moveRight() {
        if ( !((tX + tWidth + 10) > OldMain.getWidth() )) {
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
        if (tHealth <= 0) {
            //delete the alien.
            System.out.println("U DEAD");
        } else {
            System.out.println("U HIT BRUH");
        }
    }

    public Player getPlayer() {
        return this;
    }
}
