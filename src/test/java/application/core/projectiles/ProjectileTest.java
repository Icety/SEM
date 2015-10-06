package application.core.projectiles;

import application.core.projectiles.Projectile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Projectile.java.
 * @author Arthur Breurkes
 */
public class ProjectileTest {
    private Projectile testProjectile;

    /**
     * Initialize variables for testing process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testProjectile = new Projectile(0, 0);
    }

    /**
     * Test whether setDirection() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetDirection() throws Exception {
        testProjectile.setDirectionY(2);

        assertEquals(2, testProjectile.tDirectionY, 0.0);
    }

    /**
     * Test whether toString() returns the correct String.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToString() throws Exception {
        testProjectile.setX(0);
        testProjectile.setY(0);

        assertEquals("Projectile on coords: 0, 0", testProjectile.toString());
    }

    /**
     * Test whether isRemoved() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsRemoved() throws Exception {
        testProjectile.tRemoved = false;
        assertFalse(testProjectile.isRemoved());

        testProjectile.tRemoved = true;
        assertTrue(testProjectile.isRemoved());
    }
}