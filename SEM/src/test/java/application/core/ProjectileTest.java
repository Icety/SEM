package application.core;

import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

//ToDo: Finalize testMove() and write testCollision().
/**
 * Tests for the class Projectile.java.
 *
 * Created by Arthur on 9/5/15.
 */
public class ProjectileTest {
    private Projectile testProjectile;
    @Mock
    private final Image testImage = mock(Image.class);

    /**
     * Set up a basic Projectile for testing purposes.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testProjectile = new Projectile();
        testProjectile.tX = 1;
        testProjectile.tY = 1;
        //testProjectile.tImage = testImage;
    }

    /**
     * Test whether the correct X coordinate is being returned.
     *
     * @throws Exception
     */
    @Test
    public void testGetX() throws Exception {
        assertEquals(1, testProjectile.getX());
    }

    /**
     * Test whether the correct Y coordinate is being returned.
     *
     * @throws Exception
     */
    @Test
    public void testGetY() throws Exception {
        assertEquals(1, testProjectile.getY());
    }

    /**
     * Test whether the correct Image is being returned.
     *
     * @throws Exception
     */
    @Test
    public void testGetImage() throws Exception {
        assertEquals(testImage, testProjectile.getImage());
    }

    /**
     * Test whether the direction of the projectile is changed correctly.
     *
     * @throws Exception
     */
    @Test
    public void testSetDirection() throws Exception {
        assertEquals(1, testProjectile.tDirection);
        testProjectile.setDirection(2);

        assertEquals(2, testProjectile.tDirection);
    }

    /**
     * Test whether the projectile moves correctly.
     *
     * @throws Exception
     */
    @Test
    public void testMove() throws Exception {
    /*    testProjectile.move();

        assertEquals(16, testProjectile.getY());*/
    }

    /**
     * Test whether the projectile is correctly returned as a String.
     *
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        assertEquals("Projectile on coords: 1, 1", testProjectile.toString());
    }

    /**
     * Tests whether collisions with the projectile are handled correctly.
     *
     * @throws Exception
     */
    @Test
    public void testCheckCollision() throws Exception {

    }
}