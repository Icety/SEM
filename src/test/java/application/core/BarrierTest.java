package application.core;

import application.Main;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Barrier.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class BarrierTest {
    private Barrier testBarrier;

    /**
     * Initialize variables for the test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testBarrier = new Barrier(0, 0);
    }

    /**
     * Test whether getImage() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImageDefault() throws Exception {
        testBarrier.tHealth = 9;

        assertEquals(Main.BARRIER_8, testBarrier.getImage());
    }

    /**
     * Test whether getImage() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage8() throws Exception {
        testBarrier.tHealth = 8;

        assertEquals(Main.BARRIER_1, testBarrier.getImage());
    }

    /**
     * Test whether getImage() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage7() throws Exception {
        testBarrier.tHealth = 7;

        assertEquals(Main.BARRIER_2, testBarrier.getImage());
    }

    /**
     * Test whether getImage() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage6() throws Exception {
        testBarrier.tHealth = 6;

        assertEquals(Main.BARRIER_3, testBarrier.getImage());
    }

    /**
     * Test whether getImage() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage5() throws Exception {
        testBarrier.tHealth = 5;

        assertEquals(Main.BARRIER_4, testBarrier.getImage());
    }

    /**
     * Test whether getImage() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage4() throws Exception {
        testBarrier.tHealth = 4;

        assertEquals(Main.BARRIER_5, testBarrier.getImage());
    }

    /**
     * Test whether getImage() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage3() throws Exception {
        testBarrier.tHealth = 3;

        assertEquals(Main.BARRIER_6, testBarrier.getImage());
    }

    /**
     * Test whether getImage() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage2() throws Exception {
        testBarrier.tHealth = 2;

        assertEquals(Main.BARRIER_7, testBarrier.getImage());
    }

    /**
     * Test whether getImage() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage1() throws Exception {
        testBarrier.tHealth = 1;

        assertEquals(Main.BARRIER_8, testBarrier.getImage());
    }

    /**
     * Test whether hit() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testHit() throws Exception {
        testBarrier.tHealth = 1;

        assertEquals(0, testBarrier.hit());
    }
}