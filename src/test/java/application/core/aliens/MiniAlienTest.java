package application.core.aliens;

import application.Main;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Test class for MiniAlien.java.
 * @author Arthur Breurkes.
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
     * @throws Exception possible Exception.
     */
    @Test
    public void testAddShootChance() throws Exception {
        int oldChance = testAlien.getShootChance();
        testAlien.addShootChance();

        assertTrue(testAlien.getShootChance() >= oldChance);
    }

    /**
     * Test whether toString() returns the correct string.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToString() throws Exception {
        testAlien.setX(1);
        testAlien.setY(1);

        assertEquals("MiniAlien on coords: 1, 1", testAlien.toString());
    }

    /**
     * Test whether getImage() returns the correct image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage() throws Exception {
        assertEquals(Main.MINI_ALIEN, testAlien.getImage());
    }
}