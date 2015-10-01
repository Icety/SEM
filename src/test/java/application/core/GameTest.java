package application.core;

import application.logger.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

/**
 * Test class for Game.java.
 * @author Arthur Breurkes
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier",
        "checkstyle:linelength",
        "checkstyle:magicnumber"
})
public class GameTest {
    private Game testGame;
    private Alien testAlien;
    private Logger testLogger;
    private Level nonMockedLevel;
    private Player nonMockedPlayer;
    private Projectile nonMockedProjectile;
    @Mock
    public final Level testLevel = mock(Level.class);
    @Mock
    public final Player testPlayer = mock(Player.class);
    @Mock
    public final Projectile testProjectile = mock(Projectile.class);

    /**
     * Set up variables for the test process.
     */
    @Before
    public void setUp() {
        testAlien = new Alien();
        ArrayList<Alien> testAliens = new ArrayList<>();
        testLogger = new Logger();
        testAliens.add(testAlien);
        testGame = new Game(10, 10, testLogger);
        nonMockedLevel = new Level();
        nonMockedPlayer = new Player();
        testGame.tLogger = testLogger;
        nonMockedLevel.tPlayer = testPlayer;
        nonMockedLevel.tAliens = testAliens;
        nonMockedPlayer = new Player();
        nonMockedProjectile = new SmallProjectile(10, 10);
        testLogger.startLogging();
    }

    /**
     * Kill the Logger after use.
     */
    @After
    public void tearDown() {
        testLogger.stopLogging();
    }

    /**
     * Test whether setScore() sets the score of the game correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetScore() throws Exception {
        testGame.setScore(100);
        assertEquals(100, testGame.tScore);
    }

    /**
     * Test whether getScore() correctly gets the score from the game.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetScore() throws Exception {
        testGame.setScore(0);
        assertEquals(0, testGame.getScore());
    }

    /**
     * Test whether nextLevel() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testNextLevel() throws Exception {
        testGame.nextLevel();

        assertEquals(1, testGame.levelNumber);
    }

    /**
     * Test whether hasNextLevel() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testHasNextLevel() throws Exception {
        testGame.levelNumber = 0;
        assertTrue(testGame.hasNextLevel());

        testGame.levelNumber = testGame.levelFactory.tLevels.getLength();
        assertFalse(testGame.hasNextLevel());
    }

    /**
     * Test whether getLevel() returns the correct level.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetLevel() throws Exception {
        testGame.tLevel = testLevel;

        assertEquals(testLevel, testGame.getLevel());
    }

    /**
     * Test whether newGame() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testNewGame() throws Exception {
        testGame.newGame();

        assertEquals(1, testGame.levelNumber);
    }

    /**
     * Test whether getPlayer() return the correct player.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetPlayer() throws Exception {
        testGame.tPlayer = testPlayer;

        assertEquals(testPlayer, testGame.getPlayer());
    }

    /**
     * Test whether isPaused() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsPaused() throws Exception {
        assertFalse(testGame.isPaused());

        testGame.tPaused = true;
        assertTrue(testGame.isPaused());
    }

    /**
     * Test whether getHeight() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetHeight() throws Exception {
        assertEquals(10, testGame.getHeight());
    }

    /**
     * Test whether getWidth() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetWidth() throws Exception {
        assertEquals(10, testGame.getWidth());
    }

    /**
     * Test whether hasWon() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testHasWon() throws Exception {
        assertFalse(testGame.hasWon());
        testGame.tWon = true;

        assertTrue(testGame.hasWon());
    }

    /**
     * Test whether hasLost() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testHasLost() throws Exception {
        assertFalse(testGame.hasLost());
        testGame.tLost = true;

        assertTrue(testGame.hasLost());
    }

    /**
     * Test whether update() works correctly.
     * Alien hasn' yet switched directions.
     * Alien is at end of screen.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateAlienNoDirectionSwitchAndAtEndOfScreen() throws Exception {
        testGame.tLevel = nonMockedLevel;
        testAlien.tX = 10;
        testAlien.tDirection = 1;
        testGame.update();

        assertEquals(1, testAlien.tDirection);
    }

    /**
     * Test whether update() works correctly.
     * Level was won.
     * Game Has a next level.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateWhileHasWonAndHasNextLevel() throws Exception {
        testGame.tLevel = nonMockedLevel;
        testGame.levelNumber = 0;
        nonMockedLevel.removeAlien(testAlien);
        testGame.update();

        assertEquals(0, testGame.levelNumber);
    }

    /**
     * Test whether update() works correctly.
     * Level was won.
     * Game has no next level.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateWhileHasWonAndHasNoNextLevel() throws Exception {
        testGame.tLevel = nonMockedLevel;
        testGame.levelNumber = 1000;
        nonMockedLevel.removeAlien(testAlien);
        testGame.update();

        assertTrue(testGame.hasWon());
    }

    /**
     * Test whether checkCollision() works correctly.
     * Alien is hit.
     * @throws Exception possible Exception.
     */
    @Test
    public void testCheckCollisionBetweenAlienAndProjectile() throws Exception {
        testGame.tWon = false;
        ArrayList<Alien> smallAliens = new ArrayList<>();
        Alien smallAlien = new SmallAlien();
        smallAlien.tX = 10;
        smallAlien.tY = 10;
        smallAliens.add(smallAlien);
        nonMockedLevel = new Level();
        nonMockedLevel.addAliens(smallAliens);
        testGame.tLevel = nonMockedLevel;
        testGame.tPlayer = nonMockedPlayer;
        nonMockedPlayer.addProjectile(nonMockedProjectile);
        testGame.checkCollision();

        assertFalse(nonMockedPlayer.getProjectiles().contains(nonMockedProjectile));
    }

    /**
     * Test whether checkCollision() works correctly.
     * Player is hit.
     * Does not die.
     * @throws Exception possible Exception.
     */
    @Test
    public void testCheckCollisionBetweenPlayerAndProjectile() throws Exception {
        testGame.tWon = false;
        ArrayList<Alien> smallAliens = new ArrayList<>();
        Alien smallAlien = new SmallAlien();
        smallAlien.tX = 10;
        smallAlien.tY = 10;
        smallAliens.add(smallAlien);
        nonMockedLevel = new Level();
        nonMockedLevel.addAliens(smallAliens);
        testGame.tLevel = nonMockedLevel;
        nonMockedPlayer.tX = 10;
        nonMockedPlayer.tY = 10;
        testGame.tPlayer = nonMockedPlayer;
        smallAlien.addProjectile(nonMockedProjectile);
        testGame.checkCollision();

        assertFalse(smallAlien.getProjectiles().contains(nonMockedProjectile));
    }

    /**
     * Test whether checkCollision() works correctly.
     * Player is hit.
     * Dies.
     * @throws Exception possible Exception.
     */
    @Test
    public void testCheckCollisionKillPlayer() throws Exception {
        testGame.tWon = false;
        ArrayList<Alien> smallAliens = new ArrayList<>();
        Alien smallAlien = new SmallAlien();
        smallAlien.tX = 10;
        smallAlien.tY = 10;
        smallAliens.add(smallAlien);
        nonMockedLevel = new Level();
        nonMockedLevel.addAliens(smallAliens);
        testGame.tLevel = nonMockedLevel;
        nonMockedPlayer.tX = 10;
        nonMockedPlayer.tY = 10;
        nonMockedPlayer.tHealth = 1;
        testGame.tPlayer = nonMockedPlayer;
        smallAlien.addProjectile(nonMockedProjectile);
        testGame.checkCollision();

        assertTrue(testGame.tLost);
    }

    /**
     * Test whether getHighScoreManager() returns the correct HighScoreManager.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetHighScoreManager() throws Exception {
        HighScoreManager testHighScoreManager = new HighScoreManager();
        testGame.highScoreManager = testHighScoreManager;

        assertEquals(testHighScoreManager, testGame.getHighScoreManager());
    }

    /**
     * Test whether upgrade hits are handled correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testCheckPlayerUpgradeCollisions() throws Exception {
        ArrayList<Upgrade> testUpgrades = new ArrayList<>();
        Upgrade testUpgrade = new HealthUpgrade(0, 0);
        testUpgrades.add(testUpgrade);
        Iterator<Upgrade> testIterator = testUpgrades.iterator();
        nonMockedPlayer.tX = 0;
        nonMockedPlayer.tY = 0;
        testGame.tPlayer = nonMockedPlayer;
        testGame.checkPlayerUpgradeCollisions(testIterator);

        assertFalse(testUpgrade.tToDraw);
    }

    /**
     * Test whether getPlayerName() returns the correct String value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetPlayerName() throws Exception {
        testGame.tPlayerName = "Mark";

        assertEquals("Mark", testGame.getPlayerName());
    }

    /**
     * Test whether setPlayerName() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetPlayerName() throws Exception {
        testGame.setPlayerName("John");

        assertEquals("John", testGame.tPlayerName);
    }
}