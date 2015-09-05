package application.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

//ToDo: Write testSetStartPlayer().
/**
 * Tests for the class Level.java.
 *
 * Created by Arthur on 9/5/15.
 */
public class LevelTest {
    private Level testLevel;
    private Alien testAlien;
    private ArrayList<Alien> testAliens;

    /**
     * Initialization of an Alien and an ArrayList containing this Alien for test purposes.
     */
    @Before
    public void setUp() {
        testAlien = new Alien();
        testAliens = new ArrayList<>();
        testAliens.add(testAlien);
    }

    /**
     * Test whether getAliens() returns the correct set of aliens.
     *
     * @throws Exception
     */
    @Test
    public void testGetAliens() throws Exception {
        testLevel = new Level();
        testLevel.addAliens(testAliens);

        assertEquals(testAliens, testLevel.getAliens());
    }

    /**
     * Test whether addAliens adds Aliens correctly.
     *
     * @throws Exception
     */
    @Test
    public void testAddAliens() throws Exception {
        testLevel = new Level();
        testLevel.addAliens(new ArrayList<>());
        assertEquals(0, testLevel.getAliens().size());

        testLevel.addAliens(testAliens);
        assertEquals(1, testLevel.getAliens().size());
    }

    /**
     * Test whether a player is added correctly.
     *
     * @throws Exception
     */
    @Test
    public void testSetStartPlayer() throws Exception {
        testLevel = new Level();
    }

    /**
     * Test whether toString() returns the correct string containing all Aliens in the level.
     *
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        testLevel = new Level();
        testLevel.addAliens(testAliens);

        assertEquals("Level with the following aliens: \nAlien on coords: 0, 0\n", testLevel.toString());
    }

    /**
     * Test whether removeAlien removes an Alien from the level correctly.
     *
     * @throws Exception
     */
    @Test
    public void testRemoveAlien() throws Exception {
        testLevel = new Level();
        testLevel.addAliens(testAliens);
        assertTrue(testLevel.getAliens().contains(testAlien));

        testLevel.removeAlien(testAlien);
        assertFalse(testLevel.getAliens().contains(testAlien));
    }
}