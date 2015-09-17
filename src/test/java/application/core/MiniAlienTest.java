package application.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for MiniAlien.java.
 *
 * @author Arthur Breurkes
 */
public class MiniAlienTest {
    private MiniAlien testAlien;

    /**
     * Initialize variables for tests.
     */
    @Before
    public void setUp() {
        testAlien = new MiniAlien();
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

        assertEquals("MiniAlien on coords: 1, 1", testAlien.toString());
    }

    /**
     * Test whether getImage() returns the correct image.
     *
     * @throws Exception
     */
    @Test
    public void testGetImage() throws Exception {
        assertNull(testAlien.getImage());
    }
}