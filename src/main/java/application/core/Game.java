package application.core;

import java.util.Iterator;
import application.logger.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Class for Game.
 * @author Thomas Oomens
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier",
        "checkstyle:linelength"
})
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
    protected boolean tNextLevel = false;
    protected boolean tNextLevelTransition = false;
    protected Logger tLogger;
    protected String tPlayerName;

    /**
     * Constructor for Game.
     * @param width the width of the game.
     * @param height the height of the game.
     * @param logger the Logger to be bound to the game.
     */
    public Game(int width, int height, Logger logger) {
        tScreenWidth = width;
        tScreenHeight = height;
        levelFactory = new LevelFactory(tScreenWidth, tScreenHeight);
        highScoreManager = new HighScoreManager();
        levelNumber = 0;
        tPlayer = new Player();
        tPaused = false;
        tLogger = logger;
    }

    /**
     * Setter method for the Game score.
     * @param value the value to be added to the Score.
     */
    public void setScore(int value) {
        tScore += value;
    }

    /**
     * Getter method for the Game score.
     * @return the score as an integer.
     */
    public int getScore() {
        return tScore;
    }

    /**
     * Method to make the Game proceed to the next level.
     */
    public void nextLevel() {
        tNextLevel = false;
        tLevel = levelFactory.buildLevel(levelNumber);
        tLogger.setLog("The level with number: '" + levelNumber + "' was build.", 2);
        levelNumber++;
    }

    /**
     * Check whether the game has a next level.
     * @return the boolean value.
     */
    public boolean hasNextLevel() {
        return levelFactory.levelExists(levelNumber);
    }

    /**
     * Getter method for the Level belonging to the Game.
     * @return the Level.
     */
    public Level getLevel() {
        return tLevel;
    }

    /**
     * Method to start a new Game.
     */
    public void newGame() {
        tLogger.setLog("A new game was started..", 2);
        levelNumber = 0;
        nextLevel();
    }

    /**
     * Getter method for the playing Player.
     * @return the Player.
     */
    public Player getPlayer() {
        return tPlayer;
    }

    /**
     * The update method for the Game.
     * @throws SlickException possible Exception.
     */
    public void update() throws SlickException {
        tPlayer.update();
        this.alienUpdate();
        this.checkCollision();

        if (tLevel.hasWon() && !tNextLevel) {
            if (hasNextLevel()) {
                tNextLevel = true;
                tLogger.setLog("The player has beaten the level and continues to the next level.", 2);
            }
            else {
                tLogger.setLog("The player has beaten the last level and won the game.", 2);
                tWon = true;
            }
        }
    }

    /**
     * Check whether the game is paused.
     * @return the boolean value.
     */
    public boolean isPaused() {
        return tPaused;
    }

    /**
     * Getter method for the height of the Game.
     * @return the integer value.
     */
    public int getHeight() {
        return tScreenHeight;
    }

    /**
     * Getter method for the width of the Game.
     * @return the integer value.
     */
    public int getWidth() {
        return tScreenWidth;
    }

    /**
     * Check whether the game was won.
     * @return the boolean value.
     */
    public boolean hasWon() {
        return tWon;
    }

    /**
     * Getter method for the HighScoreManager.
     * @return the HighScoreManager.
     */
    public HighScoreManager getHighScoreManager() {
        return highScoreManager;
    }

    /**
     * Check whether the game was lost.
     * @return the boolean value.
     */
    public boolean hasLost() {
        return tLost;
    }

    /**
     * Check whether next level is up.
     * @return the boolean value.
     */
    public boolean isNextLevel() {
        return tNextLevel;
    }

    /**
     * Update the state of the Aliens in the game.
     */
    protected void alienUpdate() {
        boolean directionSwitched = false;

        for (Alien alien : tLevel.getAliens()) {
            alien.update();
            if (!alien.isDead() && !alien.isBonusAlien()) {
                if (!directionSwitched && alien.endOfScreen()) {
                    tLogger.setLog("The aliens reached the edge and turned around.", 2);
                    for (Alien alien2 : tLevel.getAliens()) {
                        alien2.switchDirection();
                    }
                    directionSwitched = true;
                }

                alien.setLowerLevel(tLevel.getAliens());
            }
        }
    }

    /**
     * Check whether there was a collision in the Game.
     * @throws SlickException possible Exception.
     */
    protected void checkCollision() throws SlickException {
        for (Alien alien : tLevel.getAliens()) {
            Iterator<Projectile> it = alien.getProjectiles().iterator();
            checkAlienCollisions(it);
            checkPlayerUpgradeCollisions(alien.getUpgrades().iterator());
            checkDeadAlien(alien, it);
        }
    }

    /**
     * Checker method for collisions of Aliens with projectiles.
     * @param it the Iterator over Projectiles.
     */
    public void checkAlienCollisions(Iterator<Projectile> it) throws SlickException {
        while (it.hasNext()) {
            Projectile projectile = it.next();
            if (tPlayer.intersects(projectile)) {
                tLogger.setLog("Player has been hit.", 2);
                projectile.hit();
                tPlayer.hit();
                playerDeathSound();
                if (tPlayer.noLives()) {
                    tLogger.setLog("Player has lost.", 2);
                    tLost = true;
                }
                if (projectile.noLives()) {
                    it.remove();
                }
            }
        }
    }

    /**
     * Checker method for collisions of players with upgrades.
     * @param uit Iterator over upgrades.
     */
    public void checkPlayerUpgradeCollisions(Iterator<Upgrade> uit) {
        while (uit.hasNext()) {
            Upgrade u = uit.next();
            if (tPlayer.intersects(u)) {
                tPlayer.upgrade(u);
                u.hit();
            }
        }
    }

    /**
     * Checker method for dead Aliens.
     * @param alien a given Alien.
     * @param it the Iterator over Aliens.
     */
    public void checkDeadAlien(Alien alien, Iterator<Projectile> it) throws SlickException {
        boolean wasHit = false;
        //If the alien is dead, it can't collide with player projectiles, so it should be skipped
        if (!alien.isDead()) {
            it = tPlayer.getProjectiles().iterator();
            wasHit = false;
            while (it.hasNext()) {
                Projectile projectile = it.next();
                if (alien.intersects(projectile)) {
                    tLogger.setLog("Alien was hit.", 2);
                    wasHit = true;
                    tScore += projectile.hit();
                    tScore += alien.hit();
                    if (projectile.noLives()) {
                        it.remove();
                    }
                }
            }
            if (wasHit && alien.isDead()) {
                tLogger.setLog("Alien has died.", 2);
                if (alien instanceof MothershipAlien) {
                    motherShipKilled();
                } else {
                    invaderKilledSound();
                }

            }
            }
    }

    /**
     * The sound belonging to the shots fired.
     * @throws SlickException possible Exception.
     */
    public void playerDeathSound() throws SlickException {
        Sound death = new Sound("src/main/java/application/sound/explosion.wav");
        death.play();
    }

    /**
     * The sound belonging to the death of an alien
     * @throws SlickException
     */
    public void invaderKilledSound() throws SlickException {
        Sound invaderKilled = new Sound("src/main/java/application/sound/invaderkilled.wav");
        invaderKilled.play();
    }

    /**
     * The sound belonging to the death of an alien
     * @throws SlickException
     */
    public void motherShipKilled() throws SlickException {
        Sound motherShip = new Sound("src/main/java/application/sound/mothership.wav");
        motherShip.play(1.0f,2.0f);
    }


    /**
     * Getter method for the name of the Player.
     * @return the String value.
     */
    public String getPlayerName() {
        return tPlayerName;
    }

    /**
     * Setter method for the name of the Player.
     * @param tPlayerName the desired String value
     */
    public void setPlayerName(String tPlayerName) {
        this.tPlayerName = tPlayerName;
    }
}
