package application.controllers;

import application.Main;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Test class for HighScoreBoard.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier"
})
public class HighScoreBoardTest {
    private HighScoreBoard testBoard;

    @Mock
    public final Main mockedGame = mock(Main.class);
    @Mock
    public final GameContainer mockedContainer = mock(GameContainer.class);

    /**
     * Setup variables for the test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testBoard = new HighScoreBoard(1);
        testBoard.tMain = mockedGame;
    }

    /**
     * Test whether the created HighScoreBoard is initialized correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testHighScoreBoardNotNull() throws Exception {
        assertNotNull(testBoard);
    }

    /**
     * Test whether keyReleased() works correctly with an unused key released.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedDefault() throws Exception {
        testBoard.keyReleased(Input.KEY_YEN, 'a');
    }

    /**
     * Test whether keyReleased() works correctly with the Enter key released.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedEnter() throws Exception {
        testBoard.keyReleased(Input.KEY_ENTER, 'a');
    }

    /**
     * Test whether update() works correctly, and thus, does nothing.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        testBoard.update(mockedContainer, mockedGame, 0);
    }

    /**
     * Test whether getID() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetID() throws Exception {
        assertEquals(1, testBoard.getID());
    }
}