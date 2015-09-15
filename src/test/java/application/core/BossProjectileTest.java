package application.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for BossProjectile.java.
 */
public class BossProjectileTest {
    private BossProjectile testProjectile;

    /**
     * Test whether the object is correctly initialized.
     *
     * @throws Exception
     */
    @Test
    public void testForProperInitialization() throws Exception {
        testProjectile = new BossProjectile(1, 1);

        assertNotNull(testProjectile);
        assertEquals(1, testProjectile.getX());
        assertEquals(1, testProjectile.getY());
    }
}