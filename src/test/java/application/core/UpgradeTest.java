package application.core;

import application.core.upgrades.HealthUpgrade;
import application.core.upgrades.Upgrade;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Test class for Upgrade.java.
 * @author Arthur Breurkes
 */
public class UpgradeTest {
    private Upgrade testUpgrade;

    /**
     * Initialize variable for the test process.
     */
    @Before
    public void setUp() {
        testUpgrade = new HealthUpgrade(0, 0);
    }

    /**
     * Test whether setDirection() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetDirection() throws Exception {
        testUpgrade.setDirection(2);

        assertEquals(2, testUpgrade.getDirection());
    }

    /**
     * Test whether the Upgrade is out of bounds.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsOutOfBounds() throws Exception {
        assertFalse(testUpgrade.isOutOfBounds());
        testUpgrade.tY = -1;

        assertTrue(testUpgrade.isOutOfBounds());
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        testUpgrade.tY = 0;
        testUpgrade.setSpeed(1);
        testUpgrade.setDirection(1);
        testUpgrade.update();

        assertEquals(1, testUpgrade.tY);
    }

    /**
     * Test whether toString() returns the correct String value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToString() throws Exception {
        testUpgrade.tX = 0;
        testUpgrade.tY = 0;

        assertEquals("Upgrade on coords: 0, 0", testUpgrade.toString());
    }

    /**
     * Test whether hit() handles a hit correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testHit() throws Exception {
        testUpgrade.setToDraw(true);
        testUpgrade.hit();

        assertFalse(testUpgrade.toDraw());
    }

    /**
     * Test whether isRemoved() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsRemovedTrue() throws Exception {
        testUpgrade.setRemoved(true);
        assertTrue(testUpgrade.isRemoved());
    }

    /**
     * Test whether isRemoved() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsRemovedFalse() throws Exception {
        testUpgrade.setRemoved(false);
        assertFalse(testUpgrade.isRemoved());
    }

    /**
     * Test whether isActive() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsActiveTrue() throws Exception {
        testUpgrade.setDuration(Integer.MAX_VALUE);

        assertTrue(testUpgrade.isActive());
    }

    /**
     * Test whether isActive() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsActiveFalse() throws Exception {
        testUpgrade.setDuration(Integer.MIN_VALUE);

        assertFalse(testUpgrade.isActive());
    }

    /**
     * Test whether toDraw() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToDrawTrue() throws Exception {
        testUpgrade.setToDraw(true);

        assertTrue(testUpgrade.toDraw());
    }

    /**
     * Test whether toDraw() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToDrawFalse() throws Exception {
        testUpgrade.setToDraw(false);

        assertFalse(testUpgrade.toDraw());
    }
}