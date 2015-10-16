package application.core.projectiles;

import application.Main;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Test class for PlayerProjectile.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier"
})
public class PlayerProjectileTest {
    PlayerProjectile testProjectile;

    /**
     * Test whether the constructor method works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void setUp() throws Exception {
        testProjectile = new PlayerProjectile(0, 0);

        assertNotNull(testProjectile);
    }

    /**
     * Test whether getImage() returns the correct image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage() throws Exception {
        testProjectile = new PlayerProjectile(0, 0);

        assertEquals(Main.PLAYER_PROJECTILE, testProjectile.getImage());
    }
}