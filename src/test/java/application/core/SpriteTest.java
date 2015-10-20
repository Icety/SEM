package application.core;

import application.core.projectiles.Projectile;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test class for Sprite.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class SpriteTest {
    private Sprite testSprite;
    private Projectile testProjectile;

    /**
     * Initialize variables for test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testSprite = new Sprite();
        testProjectile = new Projectile(0, 0);
    }

    /**
     * Test whether getX() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetX() throws Exception {
        testSprite.tX = 1;

        assertEquals(1, testSprite.getX());
    }

    /**
     * Test whether getY() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetY() throws Exception {
        testSprite.tY = 1;

        assertEquals(1, testSprite.getY());
    }

    /**
     * Test whether setX() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSettX() throws Exception {
        testSprite.setX(1);

        assertEquals(1, testSprite.tX);
    }

    /**
     * Test whether setY() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSettY() throws Exception {
        testSprite.setY(1);

        assertEquals(1, testSprite.tY);
    }

    /**
     * Test whether getWidth() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetWidth() throws Exception {
        testSprite.tWidth = 1;

        assertEquals(1, testSprite.getWidth());
    }

    /**
     * Test whether getHeight() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetHeight() throws Exception {
        testSprite.tHeight = 1;

        assertEquals(1, testSprite.getHeight());
    }

    /**
     * Test whether getImage() returns the correct image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage() throws Exception {
        assertNull(testSprite.getImage());
    }

    /**
     * Test whether addProjectile() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testAddProjectile() throws Exception {
        assertEquals(0, testSprite.getProjectiles().size());
        testSprite.addProjectile(testProjectile);

        assertEquals(testProjectile, testSprite.getProjectiles().get(0));
    }

    /**
     * Test whether getProjectiles returns the correct list of projectiles.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetAndGetProjectiles() throws Exception {
        ArrayList<Projectile> testProjectiles = new ArrayList<>();
        testProjectiles.add(testProjectile);
        testSprite.setProjectiles(testProjectiles);

        assertEquals(testProjectiles, testSprite.getProjectiles());
    }

    /**
     * Test whether removeProjectile() works correctly.
     * @throws Exception possible Exception.
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
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateProjectiles() throws Exception {
        ArrayList<Projectile> testProjectiles = new ArrayList<>();
        testProjectile.setX(100000);
        testProjectile.setY(100000);
        testProjectiles.add(testProjectile);
        testSprite.addProjectile(testProjectile);
        testSprite.updateProjectiles();

        assertEquals(testProjectiles, testSprite.getProjectiles());
    }

    /**
     * Test whether noLives() returns the correct boolean value.
     * @throws Exception possible Exception.
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
     * WARNING: Totally illegally tested with toString() because parent library misses equals()!
     * @throws Exception possible Exception.
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
     * @throws Exception possible Exception.
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
     * @throws Exception possible Exception.
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

    /**
     * Test whether setDifficulty() and getDifficulty() work correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetAndGetDifficulty() throws Exception {
        testSprite.setDifficulty(1);

        assertEquals(1, testSprite.getDifficulty());
    }

    /**
     * Test whether setKillScore() and getKillScore() work correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetAndGetKillScore() throws Exception {
        testSprite.setKillScore(1);

        assertEquals(1, testSprite.getKillScore());
    }

    /**
     * Test whether setHitScore() and getHitScore() work correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetAndGetHitScore() throws Exception {
        testSprite.setHitScore(1);

        assertEquals(1, testSprite.getHitScore());
    }
}