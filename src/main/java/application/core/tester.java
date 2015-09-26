//package application.core;
//
//import application.Main;
//import org.newdawn.slick.Image;
//import org.newdawn.slick.geom.Rectangle;
//import java.util.ArrayList;
//import java.util.Iterator;
//
///**
// * Class for Sprite.
// * @author Thomas Oomens
// */
//public class asd {
//    protected int tX;
//    protected int tY;
//    protected int tWidth, tHeight, tKillScore = 0, tHitScore = 0;
//    protected ArrayList<Projectile> tProjectiles = new ArrayList<>();
//    protected int tHealth;
//    protected int tDifficulty = Main.DIFFICULTY;
//    protected double tRandomChance;
//
//    /**
//     * Getter method for the x-coordinate.
//     * @return x-coordinate.
//     */
//    public int getX() {
//        return tX;
//    }
//
//    /**
//     * Getter method for the y-coordinate.
//     * @return y-coordinate.
//     */
//    public int getY() {
//        return tY;
//    }
//
//    /**
//     * Setter method for the x-coordinate.
//     * @param x x-coordinate.
//     */
//    public void settX(int x) {
//        tX = x;
//    }
//
//    /**
//     * Setter method for the y-coordinate.
//     * @param y y-coordinate.
//     */
//    public void settY(int y) {
//        tY = y;
//    }
//
//    /**
//     * Getter method for the belonging Image.
//     * @return the belonging Image.
//     */
//    public Image getImage() {
//        return null;
//    }
//
//    /**
//     * Getter method for the width.
//     * @return the width.
//     */
//    public int getWidth() {
//        return tWidth;
//    }
//
//    /**
//     * Getter method for the height.
//     * @return the height.
//     */
//    public int getHeight() {
//        return tHeight;
//    }
//
//    /**
//     * Adder method for a projectile.
//     * @param projectile a projectile.
//     */
//    public void addProjectile(Projectile projectile) {
//        tProjectiles.add(projectile);
//    }
//
//    /**
//     * Getter method for all projectiles.
//     * @return an ArrayList of projectiles.
//     */
//    public ArrayList<Projectile> getProjectiles() {
//        return tProjectiles;
//    }
//
//    /**
//     * Remover method for a projectile.
//     * @param projectile a projectile.
//     */
//    public void removeProjectile(Projectile projectile) {
//        tProjectiles.remove(projectile);
//    }
//
//    /**
//     * Setter method for the difficulty.
//     * @param difficulty the value of difficulty.
//     */
//    public void setDifficulty(int difficulty) {
//        tDifficulty = difficulty;
//    }
//
//    /**
//     * Update method for the Projectiles.
//     */
//    protected void updateProjectiles() {
//        Iterator<Projectile> i = tProjectiles.iterator();
//        while (i.hasNext()) {
//            Projectile projectile = i.next();
//            projectile.update();
//            if (projectile.isOutOfBounds()) {
//                i.remove();
//            }
//        }
//    }
//
//    /**
//     * Check whether a sprite has no lives.
//     * @return the boolean value.
//     */
//    public boolean noLives() {
//        return (tHealth < 1);
//    }
//
//    /**
//     * Getter method for the hitBox of the Sprite.
//     * @return the boundingBox.
//     */
//    public Rectangle getBoundingBox() {
//        return new Rectangle(this.getX(), this.getY(), tWidth, tHeight);
//    }
//
//    /**
//     * Method to handle hits.
//     * @return the Score.
//     */
//    public int hit() {
//        tHealth--;
//        if (tHealth <= 0) {
//            return tKillScore;
//        } else {
//            return tHitScore;
//        }
//    }
//
//    /**
//     * Method to check whether two sprites intersect.
//     * @param sprite Another Sprite object, with which you want to check collision
//     * @return whether the given object collides with this object
//     */
//    public boolean intersects(Sprite sprite) {
//        return this.getBoundingBox() != null && this.getBoundingBox().intersects(sprite.getBoundingBox());
//    }
//}
