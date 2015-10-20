package application.core.projectiles;

import application.Main;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for SmallProjectile.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class SmallProjectileTest {

    /**
     * Test whether SmallProjectiles are initialized correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSmallProjectileDifficultyOne() throws Exception {
        Main.DIFFICULTY = 1;
        SmallProjectile testProjectile = new SmallProjectile(0, 0);

        assertEquals(2, testProjectile.getSpeed());
    }

    /**
     * Test whether SmallProjectiles are initialized correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSmallProjectileDifficultyTwo() throws Exception {
        Main.DIFFICULTY = 2;
        SmallProjectile testProjectile = new SmallProjectile(0, 0);

        assertEquals(3, testProjectile.getSpeed());
    }

    /**
     * Test whether SmallProjectiles are initialized correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSmallProjectileDifficultyThree() throws Exception {
        Main.DIFFICULTY = 3;
        SmallProjectile testProjectile = new SmallProjectile(0, 0);

        assertEquals(5, testProjectile.getSpeed());
    }

    /**
     * Test whether getImage() returns the correct image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage() throws Exception {
        SmallProjectile testProjectile = new SmallProjectile(0, 0);

        assertEquals(Main.SMALL_PROJECTILE, testProjectile.getImage());
    }
}