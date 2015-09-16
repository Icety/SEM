package application.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for BossProjectile.java.
 */
public class BossProjectileTest {
    private BossProjectile testProjectile;

    @Before
    public void setUp() {
        testProjectile = new BossProjectile(1, 1);
    }

    /**
     * Test whether the object is correctly initialized.
     *
     * @throws Exception
     */
    @Test
    public void testForProperInitialization() throws Exception {
        assertNotNull(testProjectile);
        assertEquals(1, testProjectile.getX());
        assertEquals(1, testProjectile.getY());
    }

    @Test
    public void testGetImage() throws Exception {
        assertNull(testProjectile.getImage());
    }
}