package application.core;

import application.Main;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Niek on 9/2/2015.
 */
public class Projectile extends Sprite {
    protected int tDirection = 1;
    protected int tSpeed = 15;
    protected int tHealth = 1;
    protected boolean tRemoved = false;
    protected ArrayList<Sprite> tHitList = new ArrayList<>();

    public void setDirection(int direction) {
        tDirection = direction;
    }

    public void move() throws SlickException {
        tY += tDirection * tSpeed;
        if( (tY < 0) || (tY > Main.sGame.getHeight()) ) {
            tRemoved = true;
        }
        checkCollision();
    }

    public String toString() {
        String result = "Projectile on coords: "+ tX +", "+ tY;
        return result;
    }

    public void checkCollision() throws SlickException {
        Rectangle ProjectileBox = new Rectangle(getX(),getY(),tWidth,tHeight);
        if (this instanceof PlayerProjectile) {
            for(Alien a: Main.sGame.getLevel().getAliens()) {
                int bA = 5;
                int hA = 10;
                Rectangle AlienBox = new Rectangle(a.getX()+bA,a.getY()+5,(int)a.getWidth()-(2*bA),(int)a.getHeight()-(2*hA));
                if(AlienBox.getBounds().intersects(ProjectileBox)) {
                    //Let The alien and projectile take damage
                    this.hit(a);
                    return;
                }
            }
        } else {
            Player p = Main.sGame.getPlayer();
            int bA = 5;
            int hA = 10;
            Rectangle PlayerBox = new Rectangle(p.getX()+bA,p.getY()+5,(int)p.getWidth()-(2*bA),(int)p.getHeight()-(2*hA));
            if(PlayerBox.getBounds().intersects(ProjectileBox)) {
                this.hit(p);
            }
        }
    }

    public boolean isRemoved() {
        return tRemoved;
    }

    private void hit(Sprite a) {
        if(!tHitList.contains(a)) {
            if (a instanceof Alien) {
                ((Alien) a).hit();
            } else {
                ((Player) a).hit();
            }
            tHitList.add(a);
            tHealth--;
            if (tHealth <= 0) {
                tRemoved = true;
            }
        }
    }
}