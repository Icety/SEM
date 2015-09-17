package application.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for PlayerProjectile.java.
 *
 * @author Arthur Breurkes
 */
public class PlayerProjectileTest {
    PlayerProjectile testProjectile;

    /**
     * Test whether the constructor method works correctly.
     *
     * @throws Exception
     */
    @Test
    public void setUp() throws Exception {
        testProjectile = new PlayerProjectile(0, 0);

        assertNotNull(testProjectile);
    }

    /**
     * Test whether getImage() returns the correct image.
     *
     * @throws Exception
     */
    @Test
    public void testGetImage() throws Exception {
        testProjectile = new PlayerProjectile(0, 0);

        assertNull(testProjectile.getImage());
    }
}