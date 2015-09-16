package application.core;

import application.Main;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    /**
     * Test whether getImage() returns the correct Image.
     *
     * @throws Exception
     */
    @Test
    public void testGetImage() throws Exception {
        assertNull(testAlien.getImage());
    }

    /**
     * Test whether addShootChance() updates the value correctly.
     *
     * @throws Exception
     */
    @Test
    public void testAddShootChance() throws Exception {
        testAlien.addShootChance();

        assertTrue(testAlien.tShootChance >= 0);
    }
}