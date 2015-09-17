package application.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for SmallAlien.java.
 *
 * @author Arthur Breurkes
 */
public class SmallAlienTest {
    private SmallAlien testAlien;

    /**
     * Initialize variables for tests.
     */
    @Before
    public void setUp() {
        testAlien = new SmallAlien();
    }

    /**
     * Test whether addShootChance() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testAddShootChance() throws Exception {
        int oldChance = testAlien.tShootChance;
        testAlien.addShootChance();

        assertTrue(testAlien.tShootChance >= oldChance);
    }

    /**
     * Test whether toString() returns the correct string.
     *
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        testAlien.tX = 1;
        testAlien.tY = 1;

        assertEquals("SmallAlien on coords: 1, 1", testAlien.toString());
    }
}