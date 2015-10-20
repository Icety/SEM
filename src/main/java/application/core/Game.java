package application.core;

import java.util.Iterator;

import application.Barier;
import application.Main;
import application.controllers.PlayerController;
import application.core.aliens.Alien;
import application.core.aliens.MothershipAlien;
import application.core.projectiles.Projectile;
import application.core.upgrades.Upgrade;
import application.logger.Logger;
import org.newdawn.slick.SlickException;

/**
 * Class for Game.
 * @author Thomas Oomens
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier",
        "checkstyle:linelength"
})
public class Game {
    protected Main tMain;
    protected int tScore;
    protected LevelFactory levelFactory;
    protected PlayerController playerController;
    protected HighScoreManager highScoreManager;
    protected int levelNumber;
    protected Level tLevel;
    protected int tScreenWidth;
    protected int tScreenHeight;
    protected boolean tPaused;
    protected boolean tWon = false;
    protected boolean tLost = false;
    protected boolean tNextLevel = false;
    protected int tPlayers;
    protected Logger tLogger;
    protected String tPlayerName;

    /**
     * Constructor for Game.
     * @param width the width of the game.
     * @param height the height of the game.
     * @param logger the Logger to be bound to the game.
     */
    public Game(int width, int height, Logger logger, int players) {
        tScreenWidth = width;
        tScreenHeight = height;
        levelFactory = LevelFactory.getFactory();
        playerController = new PlayerController(players);
        highScoreManager = new HighScoreManager();
        levelNumber = 0;
        tPlayers = players;
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
        tLevel = levelFactory.buildLevel(levelNumber, tPlayers, playerController );
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
     * The update method for the Game.
     * @throws SlickException possible Exception.
     */
    public void update() throws SlickException {
        playerController.update();
        this.alienUpdate();
        this.checkCollision();

        if (tLevel.getTheme() != Main.imageTheme) {
            Main.imageTheme = tLevel.getTheme();
        }

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
            checkPlayerUpgradeCollisions(alien.getIterator());
            checkDeadAlien(alien, it);
        }
        Iterator<Barier> bit = tLevel.getBariers().iterator();
        checkBarierCollisions(bit);
    }

    private void checkBarierCollisions(Iterator<Barier> bit) {
        while(bit.hasNext()) {
            Barier b = bit.next();
            for(Player p : getPlayerController().getPlayers()) {
                Iterator<Projectile> it = p.getProjectiles().iterator();
                while (it.hasNext()) {
                    Projectile projectile = it.next();
                    if (b.intersects(projectile)) {
                        b.hit();
                        tLogger.setLog("Barrier has been hit", 2);
                        projectile.hit();
                        it.remove();
                    }
                }
                if (b.noLives()) {
                    bit.remove();
                }
            }
        }
    }

    /**
     * Checker method for collisions of Aliens with projectiles.
     * @param it the Iterator over Projectiles.
     */
    public void checkAlienCollisions(Iterator<Projectile> it) throws SlickException {
        while (it.hasNext()) {
            Projectile projectile = it.next();

            for(Player p : playerController.getPlayers()) {
                if (p.intersects(projectile)) {
                    tLogger.setLog("Player has been hit.", 2);
                    projectile.hit();
                    p.hit();
                    tMain.tPlayerDeathSound.play();
                    if (p.noLives()) {
                        tLogger.setLog("Player has lost.", 2);
                        tLost = true;
                    }
                    if (projectile.noLives()) {
                        it.remove();
                    }
                }
            }
            Iterator<Barier> bit = tLevel.getBariers().iterator();
            while(bit.hasNext()) {
                Barier b = bit.next();
                if(projectile.intersects(b)){
                    b.hit();
                    projectile.hit();
                    if (projectile.noLives()) {
                        it.remove();
                    }
                    if (b.noLives()) {
                        bit.remove();
                    }
                }
            }
        }
    }

    /**
     * Checker method for collisions of players with upgrades.
     * @param uit Iterator over upgrades.
     */
    public void checkPlayerUpgradeCollisions(application.Iterator uit) {
        while (uit.hasNext()) {
            Upgrade u = (Upgrade)uit.next();
            for(Player p : playerController.getPlayers()){
                if (p.intersects(u)) {
                    p.upgrade(u);
                    u.hit();
                }
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
            for (Player p : playerController.getPlayers()) {
                it = p.getProjectiles().iterator();
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
                        tMain.tMotherShipKilledSound.play();
                    } else {
                        tMain.tInvaderKilledSound.play();
                    }

                }
            }
        }

    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setHasWon(boolean bool) {
        tWon = bool;
    }

    public void setHasLost(boolean bool) {
        tLost = bool;
    }

    public void setLevel(Level level) {
        tLevel = level;
    }
}
