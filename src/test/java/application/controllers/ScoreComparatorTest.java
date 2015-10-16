package application.controllers;

import application.core.Score;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for ScoreComparator.java.
 * @author Arthur Breurkes.
 */
public class ScoreComparatorTest {
    private ScoreComparator testComparator;
    private Score score1;
    private Score score2;

    /**
     * Initialize variables for the test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testComparator = new ScoreComparator();
        score1 = new Score("Test", 0);
        score2 = new Score("Test", 1);
    }

    /**
     * Test whether compare() works correctly.
     * When: x is greater than y.
     * @throws Exception possible Exception.
     */
    @Test
    public void testCompareIf() throws Exception {
        assertEquals(1, testComparator.compare(score2, score1));
    }

    /**
     * Test whether compare() works correctly.
     * When: x is smaller than y.
     * @throws Exception possible Exception.
     */
    @Test
    public void testCompareElseIf() throws Exception {
        assertEquals(-1, testComparator.compare(score1, score2));
    }

    /**
     * Test whether compare() works correctly.
     * When: x is equal to y.
     * @throws Exception possible Exception.
     */
    @Test
    public void testCompareDefault() throws Exception {
        assertEquals(0, testComparator.compare(score1, score1));
    }
}