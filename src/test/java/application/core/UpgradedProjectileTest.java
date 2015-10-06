package application.core;

import application.Main;
import application.core.projectiles.UpgradedProjectile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for UpgradedProjectile.java.
 * @author Arthur Breurkes
 */
public class UpgradedProjectileTest {
    private UpgradedProjectile testProjectile;

    /**
     * Initialize variable for the test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testProjectile = new UpgradedProjectile(0, 0, 1, 1);
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        testProjectile.tDirectionX = 1;
        testProjectile.tDirectionY = 1;
        testProjectile.tSpeed = 1;
        testProjectile.update();

        assertEquals(1.0, testProjectile.tX, 0.0);
        assertEquals(1.0, testProjectile.tX, 0.0);
    }

    /**
     * Test whether getX() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetX() throws Exception {
        testProjectile.tX = 1;

        assertEquals(1, testProjectile.getX());
    }

    /**
     * Test whether getY() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetY() throws Exception {
        testProjectile.tY = 1;

        assertEquals(1, testProjectile.getY());
    }

    /**
     * Test whether getImage() returns the correct Image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage() throws Exception {
        assertEquals(Main.PLAYER_PROJECTILE, testProjectile.getImage());
    }
}