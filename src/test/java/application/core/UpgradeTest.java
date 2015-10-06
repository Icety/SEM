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

        assertEquals(2, testUpgrade.tDirection);
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
        testUpgrade.tSpeed = 1;
        testUpgrade.tDirection = 1;
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
        testUpgrade.tToDraw = true;
        testUpgrade.hit();

        assertFalse(testUpgrade.tToDraw);
    }

    /**
     * Test whether isRemoved() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsRemovedTrue() throws Exception {
        testUpgrade.tRemoved = true;
        assertTrue(testUpgrade.isRemoved());
    }

    /**
     * Test whether isRemoved() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsRemovedFalse() throws Exception {
        testUpgrade.tRemoved = false;
        assertFalse(testUpgrade.isRemoved());
    }

    /**
     * Test whether isActive() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsActiveTrue() throws Exception {
        testUpgrade.tDuration = Integer.MAX_VALUE;

        assertTrue(testUpgrade.isActive());
    }

    /**
     * Test whether isActive() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsActiveFalse() throws Exception {
        testUpgrade.tDuration = Integer.MIN_VALUE;

        assertFalse(testUpgrade.isActive());
    }

    /**
     * Test whether toDraw() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToDrawTrue() throws Exception {
        testUpgrade.tToDraw = true;

        assertTrue(testUpgrade.toDraw());
    }

    /**
     * Test whether toDraw() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToDrawFalse() throws Exception {
        testUpgrade.tToDraw = false;

        assertFalse(testUpgrade.toDraw());
    }
}