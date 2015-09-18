package application.core;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Thomas on 02-09-15.
 */
public class Sprite {

    protected int tX;
    protected int tY;
    protected int tWidth, tHeight, tKillScore = 0, tHitScore = 0;
    protected Image tImage = null;
    protected String tImageString;
    protected ArrayList<Projectile> tProjectiles = new ArrayList<Projectile>();
    protected Rectangle tBoundingBox;
    protected int tHealth;

    public int getX() {
        return tX;
    }

    public int getY() {
        return tY;
    }

    public void settX(int x) {
        tX = x;
    }

    public void settY(int y) {
        tY = y;
    }

    public Image getImage() {
        return null;
    }

    public int getWidth() {
        return tWidth;
    }

    public int getHeight() {
        return tHeight;
    }

    public void addProjectile(Projectile projectile) {
        tProjectiles.add(projectile);
    }

    public ArrayList<Projectile> getProjectiles() {
        return tProjectiles;
    }

    public void removeProjectile(Projectile projectile) {
        tProjectiles.remove(projectile);
    }

    protected void updateProjectiles() {
        Iterator<Projectile> i = tProjectiles.iterator();
        while (i.hasNext()) {
            Projectile projectile = i.next();
            projectile.update();
            if (projectile.isOutOfBounds()) {
                i.remove();
            }
        }
    }

    public boolean noLives() {
        return (tHealth < 1);
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(this.getX(), this.getY(), tWidth, tHeight);
    }

    public int hit() {
        tHealth--;
        if (tHealth <= 0) {
            return tKillScore;
        } else {
            return tHitScore;
        }
    }

    /**
     *
     * @param sprite    Another Sprite object, with which you want to check collision
     * @return          Returns whether the given object collides with this object
     */
    public boolean intersects(Sprite sprite) {
        return this.getBoundingBox() != null && this.getBoundingBox().intersects(sprite.getBoundingBox());
    }


}
