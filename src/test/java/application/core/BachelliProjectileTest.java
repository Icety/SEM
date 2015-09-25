package application.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for BachelliProjectile.java.
 *
 * @author Arthur
 */
public class BachelliProjectileTest {
    private BachelliProjectile testProjectile;

    @Before
    public void setUp() {
        testProjectile = new BachelliProjectile(1, 1, 1, 1);
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

    /**
     * Test whether getImage() returns the correct image.
     *
     * @throws Exception
     */
    @Test
    public void testGetImage() throws Exception {
        assertNull(testProjectile.getImage());
    }

    /**
     * Test whether update() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        testProjectile.update();

        assertEquals(6, testProjectile.getX());
        assertEquals(6, testProjectile.getY());
    }

    /**
     * Test whether getX() returns the correct value.
     *
     * @throws Exception
     */
    @Test
    public void testGetX() throws Exception {
        testProjectile.tX = 1;

        assertEquals(1, testProjectile.getX());
    }

    /**
     * Test whether getY() returns the correct value.
     *
     * @throws Exception
     */
    @Test
    public void testGetY() throws Exception {
        testProjectile.tY = 1;

        assertEquals(1, testProjectile.getY());
    }
}