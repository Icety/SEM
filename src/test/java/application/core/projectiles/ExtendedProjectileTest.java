package application.core.projectiles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for ExtendedProjectile.
 * @author Arthur Breurkes.
 */
public class ExtendedProjectileTest {
    private ExtendedProjectile testProjectile;

    /**
     * Initialize variables for the test purpose.
     * @throws Exception possible exception.
     */
    @Before
    public void setUp() throws Exception {
        testProjectile = new ExtendedProjectile(0, 0, 1.0f, 1.0f);

        assertNotNull(testProjectile);
    }

    /**
     * Test whether setDirectionX() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetDirectionX() throws Exception {
        testProjectile.setDirectionX(1);

        assertEquals(1.0, testProjectile.tDirectionX, 0.0);
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        testProjectile.update();

        assertEquals(15, testProjectile.getX());
        assertEquals(15, testProjectile.getX());
    }
}