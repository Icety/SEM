package application.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//ToDo: Write testSwitchDirection() (Dependency unreachable)
/**
 * Test class for MothershipAlien.java.
 *
 * @author Arthur Breurkes
 */
public class MothershipAlienTest {
    private MothershipAlien testAlien;

    /**
     * Initialize variables for tests.
     */
    @Before
    public void setUp() {
        testAlien = new MothershipAlien();
    }

//    @Test
//    public void testSwitchDirection() throws Exception {
//
//    }

    /**
     * Test whether isAlive() returns the correct boolean value.
     *
     * @throws Exception
     */
    @Test
    public void testIsAlive() throws Exception {
        assertTrue(testAlien.isAlive());

        testAlien.tHealth = 0;
        assertFalse(testAlien.isAlive());
    }
}