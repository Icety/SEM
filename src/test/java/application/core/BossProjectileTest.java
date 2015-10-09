package application.core;

import application.Main;
import application.core.projectiles.BossProjectile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Test class for BossProjectile.java.
 * @author Arthur
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})
public class BossProjectileTest {
    private BossProjectile testProjectile;

    /**
     * Intialize variables for the test process.
     */
    @Before
    public void setUp() {
        testProjectile = new BossProjectile(1, 1);
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
        assertEquals(Main.BOSS_PROJECTILE, testProjectile.getImage());
    }
}