package application.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for Sprite.java.
 *
 * @author Arthur Breurkes
 */
public class SpriteTest {
    private Sprite testSprite;

    /**
     * Initialize variables for test process.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testSprite = new Sprite();
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
}