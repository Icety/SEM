package application.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Score.java.
 * @author Arthur Breurkes
 */
public class ScoreTest {
    private Score testScore;

    /**
     * Initialize variable for the test process.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testScore = new Score("John", 0);
    }

    /**
     * Test whether getScore() returns the correct value.
     *
     * @throws Exception
     */
    @Test
    public void testGetScore() throws Exception {
        assertEquals(0, testScore.getScore());
    }

    /**
     * Test whether getPlayer() returns the correct Player name.
     *
     * @throws Exception
     */
    @Test
    public void testGetPlayer() throws Exception {
        assertEquals("John", testScore.getPlayer());
    }
}