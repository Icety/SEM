package application.controllers;

import application.Main;
import application.core.Game;
import application.logger.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Test class for HighScoreForm.java.
 * @author Arthur Breurkes.
 */
public class HighScoreFormTest {
    private HighScoreForm testForm;
    private Game testGame;

    @Mock
    public final Main mockedGame = mock(Main.class);
    @Mock
    public final Logger mockedLogger = mock(Logger.class);
    @Mock
    public final GameContainer mockedContainer = mock(GameContainer.class);

    @Before
    public void setUp() throws Exception {
        testForm = new HighScoreForm(1);
        testGame = new Game(0, 0, mockedLogger, false);
    }

    /**
     * Test whether the created HighScoreBoard is initialized correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testHighScoreBoardNotNull() throws Exception {
        assertNotNull(testForm);
    }

    /**
     * Test whether update() works correctly, and thus, does nothing.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        testForm.update(mockedContainer, mockedGame, 0);
    }

    /**
     * Test whether keyReleased() works correctly with an unused key released.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedDefault() throws Exception {
        testForm.keyReleased(Input.KEY_YEN, 'a');
    }

    /**
     * Test whether keyReleased() works correctly with the Enter key released.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedEnter() throws Exception {
//        testForm.keyReleased(Input.KEY_ENTER, 'a');
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals(1, testForm.getID());
    }

    @Test
    public void testGetName() throws Exception {

    }

    @Test
    public void testSetName() throws Exception {

    }
}