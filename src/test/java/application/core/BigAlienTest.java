package application.core;

import application.Main;
import application.core.aliens.BigAlien;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for BigAlien.java.
 * @author Arthur Breurkes
 */
public class BigAlienTest {
    private BigAlien testAlien;

    /**
     * Initialize variables for tests.
     */
    @Before
    public void setUp() {
        testAlien = new BigAlien();
    }

    /**
     * Test whether getImage() returns the correct Image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage() throws Exception {
        assertEquals(Main.BIG_ALIEN, testAlien.getImage());
    }

    /**
     * Test whether addShootChance() updates the value correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testAddShootChance() throws Exception {
        testAlien.addShootChance();

        assertTrue(testAlien.getShootChance() >= 0);
    }

    /**
     * Test whether toString() returns the correct string.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToString() throws Exception {
        testAlien.tX = 1;
        testAlien.tY = 1;

        assertEquals("BigAlien on coords: 1, 1", testAlien.toString());
    }
}