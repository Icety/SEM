package application.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for SmallProjectile.java.
 *
 * @author Arthur Breurkes
 */
public class SmallProjectileTest {

    /**
     * Test whether SmallProjectiles are initialized correctly.
     *
     * @throws Exception
     */
    @Test
    public void testSmallProjectile() throws Exception {
        SmallProjectile testProjectile = new SmallProjectile(0, 0);

        assertNotNull(testProjectile);
    }

    /**
     * Test whether getImage() returns the correct image.
     *
     * @throws Exception
     */
    @Test
    public void testGetImage() throws Exception {
        SmallProjectile testProjectile = new SmallProjectile(0, 0);

        assertNull(testProjectile.getImage());
    }
}