package application.core;

import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Thomas on 01-09-15.
 */
public class Game {
    protected int tScore;
    protected LevelFactory levelFactory;
    protected HighScoreManager highScoreManager;
    protected int levelNumber;
    protected Level tLevel;
    protected Player tPlayer;
    protected int tScreenWidth;
    protected int tScreenHeight;
    protected boolean tPaused;
    protected boolean tWon = false;
    protected boolean tLost = false;



    public Game(int width, int height) {
        tScreenWidth = width;
        tScreenHeight = height;
        levelFactory = new LevelFactory(tScreenWidth, tScreenHeight);
        highScoreManager = new HighScoreManager();
        levelNumber = 0;
        tPlayer = new Player();
        tPaused = false;

    }

    public void setScore(int value) {
        tScore += value;
    }

    public int getScore() { return tScore; }

    public void nextLevel() {
        tLevel = levelFactory.buildLevel(levelNumber);
        levelNumber++;
    }

    public boolean hasNextLevel() {
        return levelFactory.levelExists(levelNumber);
    }

    public Level getLevel() {
        return tLevel;
    }

    public void newGame() {
        levelNumber = 0;
        nextLevel();
    }

    public Player getPlayer() {
        return tPlayer;
    }

    public void update() throws SlickException {
        tPlayer.update();
        this.alienUpdate();
        this.checkCollision();

        if (tLevel.hasWon()) {
            if (hasNextLevel()) {
                nextLevel();
            }
            else {
                tWon = true;
                highScoreManager.addScores(tScore);
                System.out.println(highScoreManager.toString());
            }
        }
    }

    public boolean isPaused(){
        return tPaused;
    }

    public int getHeight() {
        return tScreenHeight;
    }

    public int getWidth() {
        return tScreenWidth;
    }

    public boolean hasWon() {
        return tWon;
    }

    public HighScoreManager getHighScoreManager() {
        return highScoreManager;
    }

    public boolean hasLost() {
        return tLost;
    }

    protected void alienUpdate() {
        boolean directionSwitched = false;

        for (Alien alien : tLevel.getAliens()) {
            alien.update();

            //Switch direction when the borders are reached
            if (!directionSwitched && alien.endOfScreen()) {
                for (Alien alien2 : tLevel.getAliens()) {
                    alien2.switchDirection();
                }
                directionSwitched = true;
            }

            alien.setLowerLevel(tLevel.getAliens());
        }
    }

    protected void checkCollision() {
        Iterator<Alien> i = tLevel.getAliens().iterator();
        boolean wasHit = false;
        while (i.hasNext()) {
            Alien alien = i.next();
            //Check collision between alien and player projectile
            Iterator<Projectile> it = tPlayer.getProjectiles().iterator();
            wasHit = false;
            while (it.hasNext()) {
                Projectile projectile = it.next();
                if (alien.intersects(projectile)) {
                    wasHit = true;
                    tScore += projectile.hit();
                    tScore += alien.hit();
                    if (projectile.noLives()) {
                        it.remove();
                        continue;
                    }
                }
            }
            if (wasHit && alien.noLives()) {
                i.remove();
                continue;
            }

            //Check collision between player and alien projectile
            it = alien.getProjectiles().iterator();
            while (it.hasNext()) {
                Projectile projectile = it.next();
                if (tPlayer.intersects(projectile)) {
                    projectile.hit();
                    tPlayer.hit();
                    if (tPlayer.noLives()) {
                        tLost = true;
                        highScoreManager.addScores(tScore);
                        System.out.println(highScoreManager.toString());
                    }
                    if (projectile.noLives()) {
                        it.remove();
                    }
                }
            }
        }
    }
}
