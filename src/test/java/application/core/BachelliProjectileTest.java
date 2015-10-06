package application.core;

import application.Main;
import application.core.projectiles.BachelliProjectile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for BachelliProjectile.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})
public class BachelliProjectileTest {
    private BachelliProjectile testProjectile;

    /**
     * Initialize variables for the test process.
     */
    @Before
    public void setUp() {
        testProjectile = new BachelliProjectile(1, 1, 1, 1);
    }

    /**
     * Test whether the object is correctly initialized.
     * @throws Exception possible Exception.
     */
    @Test
    public void testForProperInitialization() throws Exception {
        assertNotNull(testProjectile);
        assertEquals(1, testProjectile.getX());
        assertEquals(1, testProjectile.getY());
    }

    /**
     * Test whether getImage() returns the correct image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage() throws Exception {
        assertEquals(Main.BACHELLI_PROJECTILE, testProjectile.getImage());
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        testProjectile.update();

        assertEquals(6, testProjectile.getX());
        assertEquals(6, testProjectile.getY());
    }

    /**
     * Test whether getX() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetX() throws Exception {
        testProjectile.setX(1);

        assertEquals(1, testProjectile.getX());
    }

    /**
     * Test whether getY() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetY() throws Exception {
        testProjectile.setY(1);

        assertEquals(1, testProjectile.getY());
    }
}