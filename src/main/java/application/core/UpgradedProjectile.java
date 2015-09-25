package application.core;

import application.Main;
import org.newdawn.slick.Image;

/**
 * Created by Niek on 9/9/2015
 */
public class UpgradedProjectile extends Projectile {
    protected float tX;
    protected float tY;
    protected float tDirectionX;
    protected float tDirectionY;

    public UpgradedProjectile(int x, int y, float directionX, float directionY) {
        this.tDirection = 1;
        this.tX = x;
        this.tY = y;
        this.tSpeed = 5;
        this.tWidth = 50;
        this.tHeight = 70;
        this.tDirectionX = -directionX;
        this.tDirectionY = -directionY;
        this.tWidth = 7;
        this.tHeight = 15;
    }

    public void update() {
        this.tX += tDirectionX * tSpeed;
        this.tY += tDirectionY * tSpeed;
    }

    public int getX() {
        return (int) tX;
    }

    public int getY() {
        return (int) tY;
    }

    public Image getImage() {
        return Main.PLAYER_PROJECTILE;
    }
}