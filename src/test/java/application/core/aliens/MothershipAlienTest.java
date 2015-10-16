package application.core.aliens;

import application.Main;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

//ToDo: Write testSwitchDirection() (Dependency unreachable)
/**
 * Test class for MothershipAlien.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class MothershipAlienTest {
    private MothershipAlien testAlien;

    /**
     * Initialize variables for tests.
     */
    @Before
    public void setUp() {
        testAlien = new MothershipAlien();
    }

    /**
     * Test whether isAlive() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsAlive() throws Exception {
        assertTrue(testAlien.isAlive());

        testAlien.setHealth(0);
        assertFalse(testAlien.isAlive());
    }

    /**
     * Test whether toString() returns the correct string.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToString() throws Exception {
        testAlien.setX(1);
        testAlien.setY(1);

        assertEquals("MotherShipAlien on coords: 1, 1", testAlien.toString());
    }

    /**
     * Test whether getImage() returns the correct image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage() throws Exception {
        assertEquals(Main.MOTHERSHIP_ALIEN, testAlien.getImage());
    }

    /**
     * Test whether switchDirection() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSwitchDirection() throws Exception {
        testAlien.setDirection(1);
        testAlien.setX(-2001);
        testAlien.switchDirection();

        assertEquals(-1, testAlien.getDirection());
    }
}