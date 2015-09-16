package application.core;

import application.Main;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by Ties on 15-9-2015.
 */
public class Upgrade extends Sprite {
    protected int tDirection = 1;
    protected int tSpeed = 15;
    protected int tHealth = 1;
    protected boolean tRemoved = false;

    public void setDirection(int direction) {
        tDirection = direction;
    }

    public void move() throws SlickException {
        tY += tDirection * tSpeed;
        if ((tY < 0) || (tY > Main.sGame.getHeight())) {
            tRemoved = true;
        }
        checkCollision();
    }

    public String toString() {
        String result = "Upgrade on coords: " + tX + ", " + tY;
        return result;
    }

    public void checkCollision() throws SlickException {
        Rectangle UpgradeBox = new Rectangle(getX(), getY(), tWidth, tHeight);
        Player p = Main.sGame.getPlayer();
        int bA = 5;
        int hA = 10;
        Rectangle PlayerBox = new Rectangle(p.getX() + bA, p.getY() + 5, (int) p.getWidth() - (2 * bA), (int) p.getHeight() - (2 * hA));
        if (PlayerBox.getBounds().intersects(UpgradeBox)) {
            this.hit(p);
        }
    }

    public boolean isRemoved() {
        return tRemoved;
    }

    private void hit(Sprite a) {

    }
}

