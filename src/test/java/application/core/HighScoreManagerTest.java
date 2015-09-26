package application.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test class for HighScoreManager.java.
 * @author Arthur Breurkes
 */
public class HighScoreManagerTest {
    private HighScoreManager testScoreManager;

    @Before
    public void setUp() throws Exception {
        testScoreManager = new HighScoreManager();
        testScoreManager.highScoreFile = "testScores.dat";
    }

    /**
     * Test whether getScores() returns the correct list of Scores.
     *
     * @throws Exception
     */
    @Test
    public void testGetScores() throws Exception {
        assertNotNull(testScoreManager.getScores());
    }

    /**
     * Test whether addScores() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testAddScores() throws Exception {
        int n = testScoreManager.getScores().size() + 1;
        testScoreManager.addScores("Henk", 1);

        assertEquals(n, testScoreManager.getScores().size());
    }

    /**
     * Test whether loadScoreFile() works correctly.
     *
     * @throws Exception
     */
    @Test
    public void testLoadScoreFile() throws Exception {
        testScoreManager.scores = null;
        testScoreManager.loadScoreFile();

        assertNotNull(testScoreManager.scores);
    }

    /**
     * Test whether updateScoreFile() works correctly.
     * @throws Exception
     */
    @Test
    public void testUpdateScoreFile() throws Exception {
        ArrayList<Score> testList = testScoreManager.getScores();
        testList.add(new Score("John", 0));
        testScoreManager.scores = testList;
        testScoreManager.updateScoreFile();
        int n = testList.size();

        assertEquals(n, testScoreManager.getScores().size());
    }
}