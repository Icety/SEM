package application.core;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test class for Sprite.java.
 *
 * @author Arthur Breurkes
 */
public class SpriteTest {
    private Sprite testSprite;
    private Projectile testProjectile;

    /**
     * Initialize variables for test process.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testSprite = new Sprite();
        testProjectile = new Projectile();
    }

    /**
     * Test whether getX() returns the correct value.
     *
     * @throws Exception
     */
    @Test
    public void testGetX() throws Exception {
        testSprite.tX = 1;

        assertEquals(1, testSprite.getX());
    }

    /**
     * Test whether getY() returns the correct value.
     *
     * @throws Exception
     */
    @Test
    public void testGetY() throws Exception {
        testSprite.tY = 1;

        assertEquals(1, testSprite.getY());
    }

    /**
     * Test whether setX() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testSettX() throws Exception {
        testSprite.settX(1);

        assertEquals(1, testSprite.tX);
    }

    /**
     * Test whether setY() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testSettY() throws Exception {
        testSprite.settY(1);

        assertEquals(1, testSprite.tY);
    }

    /**
     * Test whether getWidth() returns the correct value.
     *
     * @throws Exception
     */
    @Test
    public void testGetWidth() throws Exception {
        testSprite.tWidth = 1;

        assertEquals(1, testSprite.getWidth());
    }

    /**
     * Test whether getHeight() returns the correct value.
     *
     * @throws Exception
     */
    @Test
    public void testGetHeight() throws Exception {
        testSprite.tHeight = 1;

        assertEquals(1, testSprite.getHeight());
    }

    /**
     * Test whether getImage() returns the correct image.
     *
     * @throws Exception
     */
    @Test
    public void testGetImage() throws Exception {
        assertNull(testSprite.getImage());
    }

    /**
     * Test whether addProjectile() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testAddProjectile() throws Exception {
        assertEquals(0, testSprite.getProjectiles().size());
        testSprite.addProjectile(testProjectile);

        assertEquals(testProjectile, testSprite.getProjectiles().get(0));
    }

    /**
     * Test whether getProjectiles returns the correct list of projectiles.
     *
     * @throws Exception
     */
    @Test
    public void testGetProjectiles() throws Exception {
        ArrayList<Projectile> testProjectiles = new ArrayList<>();
        testProjectiles.add(testProjectile);
        testSprite.addProjectile(testProjectile);

        assertEquals(testProjectiles, testSprite.getProjectiles());
    }

    /**
     * Test whether removeProjectile() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testRemoveProjectile() throws Exception {
        ArrayList<Projectile> testProjectiles = new ArrayList<>();
        testSprite.addProjectile(testProjectile);
        testSprite.removeProjectile(testProjectile);

        assertEquals(testProjectiles, testSprite.getProjectiles());
    }

    /**
     * Test whether updateProjectiles() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateProjectiles() throws Exception {
        ArrayList<Projectile> testProjectiles = new ArrayList<>();
        testProjectile.tX = 100000;
        testProjectile.tY = 100000;
        testSprite.addProjectile(testProjectile);
        testSprite.updateProjectiles();

        assertEquals(testProjectiles, testSprite.getProjectiles());
    }

    /**
     * Test whether noLives() returns the correct boolean value.
     *
     * @throws Exception
     */
    @Test
    public void testNoLives() throws Exception {
        testSprite.tHealth = 1;
        assertFalse(testSprite.noLives());

        testSprite.tHealth = 0;
        assertTrue(testSprite.noLives());
    }

    /**
     * Test whether getBoundingBox() returns the correct rectangle.
     *
     * WARNING: Totally illegally tested with toString() because parent library misses equals()!
     *
     * @throws Exception
     */
    @Test
    public void testGetBoundingBox() throws Exception {
        testSprite.tX = 1;
        testSprite.tY = 1;
        testSprite.tWidth = 1;
        testSprite.tHeight = 1;
        Rectangle testRectangle = new Rectangle(1, 1, 1, 1);

        assertEquals(testRectangle.toString(), testSprite.getBoundingBox().toString());
    }

    /**
     * Test whether hit() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testHit() throws Exception {
        testSprite.tHealth = 10;
        testSprite.tHitScore = 1;
        testSprite.hit();

        assertEquals(1, testSprite.hit());
    }

    /**
     * Test whether intersect() returns the correct boolean value..
     *
     * @throws Exception
     */
    @Test
    public void testIntersects() throws Exception {
        Sprite intersectSprite = new Sprite();
        testSprite.tX = 10;
        testSprite.tY = 10;
        intersectSprite.tX = 1000;
        intersectSprite.tY = 1000;

        assertTrue(testSprite.intersects(testSprite));
        assertFalse(testSprite.intersects(intersectSprite));
    }
}