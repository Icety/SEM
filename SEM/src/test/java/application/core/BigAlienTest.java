package application.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for BigAlien.java.
 *
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

    @Test
    public void testAddShootChance() throws Exception {
        testAlien.addShootChance();

        assertTrue(testAlien.tShootChance >= 0);
    }
}