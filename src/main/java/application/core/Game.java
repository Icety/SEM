package application.core;

import application.logger.Logger;
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
        Logger.setLog("The level with number: '"+ levelNumber +"' was build.", 2);
        levelNumber++;
    }

    public boolean hasNextLevel() {
        return levelFactory.levelExists(levelNumber);
    }

    public Level getLevel() {
        return tLevel;
    }

    public void newGame() {
        Logger.setLog("A new game was started..", 2);
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
                Logger.setLog("The player has beaten the level and continues to the next level.", 2);
                nextLevel();
            }
            else {
                Logger.setLog("The player has beaten the last level and won the game.", 2);
                tWon = true;
                highScoreManager.addScores(tScore);
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
                Logger.setLog("The aliens reached the edge and turned around.", 2);
                for (Alien alien2 : tLevel.getAliens()) {
                    alien2.switchDirection();
                }
                directionSwitched = true;
            }

            alien.setLowerLevel(tLevel.getAliens());
        }
    }

    protected void checkCollision() throws SlickException {
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
                    Logger.setLog("Alien was hit.", 2);
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
                Logger.setLog("Alien has died.", 2);
                i.remove();
                continue;
            }

            //Check collision between player and alien projectile
            it = alien.getProjectiles().iterator();
            while (it.hasNext()) {
                Projectile projectile = it.next();
                if (tPlayer.intersects(projectile)) {
                    Logger.setLog("Player has been hit.", 2);
                    projectile.hit();
                    tPlayer.hit();
                    if (tPlayer.noLives()) {
                        Logger.setLog("Player has lost.", 2);
                        tLost = true;
                        highScoreManager.addScores(tScore);
                    }
                    if (projectile.noLives()) {
                        it.remove();
                    }
                }
            }
        }
    }
}
