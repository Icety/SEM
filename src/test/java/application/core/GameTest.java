package application.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

//ToDo: write testUpdate() methods. Will do after all other short tests.
/**
 * Test class for Game.java.
 *
 * @author Arthur Breurkes
 */
public class GameTest {
    private Game testGame;
    private Alien testAlien;
    private Level nonMockedLevel;
    private Player nonMockedPlayer;
    private Projectile nonMockedProjectile;
    private ArrayList<Alien> testAliens;
    @Mock
    public final Level testLevel = mock(Level.class);
    @Mock
    public final Player testPlayer = mock(Player.class);
    @Mock
    public final Projectile testProjectile= mock(Projectile.class);

    /**
     * Set up variables for the test process.
     */
    @Before
    public void setUp() {
        testAlien = new Alien();
        testAliens = new ArrayList<>();
        testAliens.add(testAlien);
        testGame = new Game(10, 10);
        nonMockedLevel = new Level();
        nonMockedPlayer = new Player();
        nonMockedLevel.tPlayer = testPlayer;
        nonMockedLevel.tAliens = testAliens;
        nonMockedPlayer = new Player();
        nonMockedProjectile = new SmallProjectile(10, 10);
    }

    /**
     * Test whether setScore() sets the score of the game correctly.
     *
     * @throws Exception
     */
    @Test
    public void testSetScore() throws Exception {
        testGame.setScore(100);
        assertEquals(100, testGame.tScore);
    }

    /**
     * Test whether getScore() correctly gets the score from the game.
     *
     * @throws Exception
     */
    @Test
    public void testGetScore() throws Exception {
        testGame.setScore(0);
        assertEquals(0, testGame.getScore());
    }

    /**
     * Test whether nextLevel() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testNextLevel() throws Exception {
        testGame.nextLevel();

        assertEquals(1, testGame.levelNumber);
    }

    /**
     * Test whether hasNextLevel() works correctly.
     *
     * @throws Exception
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
     *
     * @throws Exception
     */
    @Test
    public void testGetLevel() throws Exception {
        testGame.tLevel = testLevel;

        assertEquals(testLevel, testGame.getLevel());
    }

    /**
     * Test whether newGame() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testNewGame() throws Exception {
        testGame.newGame();

        assertEquals(1, testGame.levelNumber);
    }

    /**
     * Test whether getPlayer() return the correct player.
     *
     * @throws Exception
     */
    @Test
    public void testGetPlayer() throws Exception {
        testGame.tPlayer = testPlayer;

        assertEquals(testPlayer, testGame.getPlayer());
    }

    /**
     * Test whether isPaused() returns the correct boolean value.
     *
     * @throws Exception
     */
    @Test
    public void testIsPaused() throws Exception {
        assertFalse(testGame.isPaused());

        testGame.tPaused = true;
        assertTrue(testGame.isPaused());
    }

    /**
     * Test whether getHeight() returns the correct value.
     *
     * @throws Exception
     */
    @Test
    public void testGetHeight() throws Exception {
        assertEquals(10, testGame.getHeight());
    }

    /**
     * Test whether getWidth() returns the correct value.
     *
     * @throws Exception
     */
    @Test
    public void testGetWidth() throws Exception {
        assertEquals(10, testGame.getWidth());
    }

    /**
     * Test whether hasWon() returns the correct boolean value.
     *
     * @throws Exception
     */
    @Test
    public void testHasWon() throws Exception {
        assertFalse(testGame.hasWon());
        testGame.tWon = true;

        assertTrue(testGame.hasWon());
    }

    /**
     * Test whether hasLost() returns the correct boolean value.
     *
     * @throws Exception
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
     *
     * @throws Exception
     */
    @Test
    public void testUpdateAlienNoDirectionSwitchAndAtEndOfScreen() throws Exception {
        testGame.tLevel = nonMockedLevel;
        testAlien.tX = 10;
        testAlien.tDirection = 1;
        testGame.update();

        assertEquals(-1, testAlien.tDirection);
    }

    /**
     * Test whether update() works correctly.
     * Level was won.
     * Game Has a next level.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateWhileHasWonAndHasNextLevel() throws Exception {
        testGame.tLevel = nonMockedLevel;
        testGame.levelNumber = 0;
        nonMockedLevel.removeAlien(testAlien);
        testGame.update();

        assertEquals(1, testGame.levelNumber);
    }

    /**
     * Test whether update() works correctly.
     * Level was won.
     * Game has no next level.
     *
     * @throws Exception
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
     *
     * @throws Exception
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
     *
     * @throws Exception
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
     *
     * @throws Exception
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
}