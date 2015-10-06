package application.core;

import application.Main;
import application.core.projectiles.Projectile;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class for Sprite.
 * @author Thomas Oomens
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier"
})
public class Sprite {
    protected int tX;
    protected int tY;
    protected int tHealth;
    protected double tRandomChance;
    protected int tDifficulty = Main.DIFFICULTY;
    protected int tWidth, tHeight, tKillScore = 0, tHitScore = 0;
    protected ArrayList<Projectile> tProjectiles = new ArrayList<>();

    /**
     * Getter method for the x-coordinate.
     * @return x-coordinate.
     */
    public int getX() {
        return tX;
    }

    /**
     * Getter method for the y-coordinate.
     * @return y-coordinate.
     */
    public int getY() {
        return tY;
    }

    /**
     * Setter method for the x-coordinate.
     * @param x x-coordinate.
     */
    public void settX(int x) {
        tX = x;
    }

    /**
     * Setter method for the y-coordinate.
     * @param y y-coordinate.
     */
    public void settY(int y) {
        tY = y;
    }

    /**
     * Getter method for the Image.
     * @return the belonging Image.
     */
    public Image getImage() {
        return null;
    }

    /**
     * Getter method for the width.
     * @return the width.
     */
    public int getWidth() {
        return tWidth;
    }

    /**
     * Getter method for the height.
     * @return the height.
     */
    public int getHeight() {
        return tHeight;
    }

    /**
     * Check whether the Sprite is alive.
     * @return the boolean value.
     */
    public boolean noLives() {
        return tHealth < 1;
    }

    /**
     * Adder method for Projectiles.
     * @param projectile an ArrayList containing the projectiles to be added.
     */
    public void addProjectile(Projectile projectile) {
        tProjectiles.add(projectile);
    }

    /**
     * Getter method for the belonging Projectiles.
     * @return an ArrayList containing the belonging Projectiles.
     */
    public ArrayList<Projectile> getProjectiles() {
        return tProjectiles;
    }

    /**
     * Remover method for a specific Projectile.
     * @param projectile the projectile to be removed.
     */
    public void removeProjectile(Projectile projectile) {
        tProjectiles.remove(projectile);
    }

    /**
     * Setter method for the difficulty.
     * @param difficulty the integer value of the difficulty.
     */
    public void setDifficulty(int difficulty) {
        tDifficulty = difficulty;
    }

    /**
     * Update method for the projectiles belonging to the Sprite.
     */
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

    /**
     * Get the hitBox for the Sprite.
     * @return the belonging boundingBox.
     */
    public Rectangle getBoundingBox() {
        return new Rectangle(tX, tY, tWidth, tHeight);
    }

    /**
     * Hit handling method for the Sprite.
     * @return a specific score belonging to the hit.
     */
    public int hit() {
        tHealth--;
        if (tHealth <= 0) {
            return tKillScore;
        }
        return tHitScore;
    }

    /**
     * Check whether two Sprites intersect.
     * @param sprite the Sprite to compare to.
     * @return the boolean value.
     */
    public boolean intersects(Sprite sprite) {
        return getBoundingBox() != null && getBoundingBox().intersects(sprite.getBoundingBox());
    }
}