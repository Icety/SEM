package application.core;

import application.core.aliens.Alien;
import application.core.projectiles.PlayerProjectile;
import application.core.projectiles.Projectile;
import application.logger.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Test class for Game.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier",
        "checkstyle:linelength",
        "checkstyle:magicnumber"
})
public class GameTest {
    private Game testGame;
    private Game testMultiPlayerGame;
    private Alien testAlien;
    private Logger testLogger;
    private Level nonMockedLevel;
    private Player nonMockedPlayer;

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
        testAliens.add(testAlien);
        testLogger = new Logger();
        testGame = new Game(10, 10, testLogger, 1);
        testMultiPlayerGame = new Game(10, 10, testLogger, 2);
        nonMockedLevel = new Level(1, testGame.getPlayerController());
        nonMockedPlayer = new Player();
        testGame.tLogger = testLogger;
        testMultiPlayerGame.tLogger = testLogger;
        nonMockedLevel.tAliens = testAliens;
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
        testAlien.setDirection(-1);
        testGame.update();

        assertEquals(1, testAlien.getDirection());
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
     * Test whether update() works correctly with a multiplayer game.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateWithMultiPlayer() throws Exception {
        testMultiPlayerGame.tLevel = testLevel;
        testMultiPlayerGame.update();

        assertFalse(testMultiPlayerGame.hasWon());
    }

    /**
     * Test whether isNextLevel() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsNextLevel() throws Exception {
        testGame.tNextLevel = true;

        assertTrue(testGame.isNextLevel());
    }

    /**
     * Test whether checkBarrierCollisions() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testCheckBarrierCollisions() throws Exception {
        ArrayList<Barrier> testBarriers = new ArrayList<>();
        ArrayList<Player> testPlayers = new ArrayList<>();
        ArrayList<Projectile> testProjectiles = new ArrayList<>();
        testProjectiles.add(new PlayerProjectile(10, 10));
        testPlayer.tProjectiles = testProjectiles;
        testPlayers.add(testPlayer);
        testGame.getPlayerController().setPlayers(1, testPlayers);
        Barrier testBarrier = new Barrier(10, 10);
        testBarrier.tHealth = 1;
        testBarriers.add(testBarrier);
        Iterator<Barrier> it = testBarriers.iterator();

        testGame.checkBarierCollisions(it);
        assertEquals(1, testBarrier.getHealth());
    }
}