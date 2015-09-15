package application.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//ToDo: Write testMoveRight(), testLaserSound() (Dependency unreachable) and testUpdate (too long).
/**
 * Test class for Player.java.
 *
 * @author Arthur Breurkes
 */
public class PlayerTest {
    private Player testPlayer;

    /**
     * Initialize variables for the test process.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testPlayer = new Player();
    }

    /**
     * Test whether update works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {

    }

    /**
     * Test whether moveLeft() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testMoveLeft() throws Exception {
        testPlayer.tX = 20;
        testPlayer.moveLeft();

        assertEquals(15, testPlayer.getX());
    }

//    @Test
//    public void testMoveRight() throws Exception {
//        testPlayer.tX = 20;
//        testPlayer.moveRight();
//
//        assertEquals(25, testPlayer.getX());
//    }

    /**
     * Test whether toString() returns the correct String.
     *
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        testPlayer.settX(0);
        testPlayer.settY(0);

        assertEquals("Player on coords: 0, 0", testPlayer.toString());
    }

//    @Test
//    public void testLaserSound() throws Exception {
//
//    }

    /**
     * Test whether leftArrowPressed() returns the correct boolean value.
     *
     * @throws Exception
     */
    @Test
    public void testLeftArrowPressed() throws Exception {
        testPlayer.leftArrowPressed(true);
        assertTrue(testPlayer.tGoLeft);

        testPlayer.leftArrowPressed(false);
        assertFalse(testPlayer.tGoLeft);

    }

    /**
     * Test whether rightArrowPressed() returns the correct boolean value.
     *
     * @throws Exception
     */
    @Test
    public void testRightArrowPressed() throws Exception {
        testPlayer.rightArrowPressed(true);
        assertTrue(testPlayer.tGoRight);

        testPlayer.rightArrowPressed(false);
        assertFalse(testPlayer.tGoRight);
    }

    /**
     * Test whether fireButtonPressed() returns the correct boolean value.
     *
     * @throws Exception
     */
    @Test
    public void testFireButtonPressed() throws Exception {
        testPlayer.fireButtonPressed(true);
        assertTrue(testPlayer.tShoot);

        testPlayer.fireButtonPressed(false);
        assertFalse(testPlayer.tShoot);
    }

    /**
     * Test whether hit() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testHit() throws Exception {
        testPlayer.tHealth = 1;
        testPlayer.hit();

        assertEquals(0, testPlayer.tHealth);
    }

    /**
     * Test whether getPlayer() returns the correct player.
     *
     * @throws Exception
     */
    @Test
    public void testGetPlayer() throws Exception {
        assertEquals(testPlayer, testPlayer.getPlayer());
    }

    /**
     * Test whether getHealth() returns the correct value.
     *
     * @throws Exception
     */
    @Test
    public void testGetHealth() throws Exception {
        testPlayer.tHealth = 1;

        assertEquals(1, testPlayer.getHealth());
    }
}