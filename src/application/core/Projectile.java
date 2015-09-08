package application.core;

import application.Main;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Niek on 9/2/2015.
 */
public class Projectile implements Sprite {
    protected int tX;
    protected int tY;
    protected Image tImage;
    protected int tDirection = 1;
    protected int tSpeed = 15;
    protected int tHealth = 1;
    protected ArrayList<Sprite> tHitList = new ArrayList<>();

    public int getX() {
        return tX;
    }

    public int getY() {
        return tY;
    }

    public Image getImage() {
        return tImage;
    }

    public void setDirection(int direction) {
        tDirection = direction;
    }

    public void move() {
        tY += tDirection * tSpeed;
        if( tY + tImage.getHeight() < 0) {
            //delete projectile;
        }
        if( tY + tImage.getHeight() > Main.getHeight()) {
            //delete projectile;
        }
        checkCollision();
    }

    public String toString() {
        String result = "Projectile on coords: "+ tX +", "+ tY;
        return result;
    }

    public void checkCollision() {
        Rectangle ProjectileBox = new Rectangle(getX(),getY(),(int)getImage().getWidth(),(int)getImage().getHeight());
        for(Alien a: Main.game.getLevel().getAliens()) {
            int bA = 5;
            int hA = 10;
            Rectangle AlienBox = new Rectangle(a.getX()+bA,a.getY()+5,(int)a.getImage().getWidth()-(2*bA),(int)a.getImage().getHeight()-(2*hA));
            if(AlienBox.getBounds().intersects(ProjectileBox)) {
                //Let The alien and projectile take damage
                this.hit(a);
                return;
            }
        }
        Player p = Main.game.getPlayer();
        Rectangle PlayerBox = new Rectangle(p.getX(),p.getY(),(int)p.getImage().getWidth(),(int)p.getImage().getHeight());
        if(PlayerBox.getBounds().intersects(ProjectileBox)) {
            //Let The alien and projectile take damage
            this.hit(p);
        }
    }

    private void hit(Alien a) {
        if(!tHitList.contains(a)) {
            a.hit();
            tHitList.add(a);
            tHealth--;
            if (tHealth <= 0) {
                Main.game.removeProjectile(this);
            }
        }
    }

    private void hit(Player p) {
        if(!tHitList.contains(p)) {
            p.hit();
           // tHitList.add(p);
            tHealth--;
            if (tHealth <= 0) {
                Main.game.removeProjectile(this);
            }
        }
    }

    public void addHit(Player p) {
        tHitList.add(p);
    }
}
