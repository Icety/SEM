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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for Lost.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})
public class LostTest {
    private Lost testLost;
    private Game testGame;

    @Mock
    public final Main mockedGame = mock(Main.class);
    @Mock
    public final Logger mockedLogger = mock(Logger.class);
    @Mock
    public final GameContainer mockedContainer = mock(GameContainer.class);

    /**
     * Initialize variables for the test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testLost = new Lost(1);
        testGame = new Game(0, 0, mockedLogger, 1);
        testLost.tMain = mockedGame;

        when(mockedGame.getGame()).thenReturn(testGame);
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        testLost.tExit = true;
        testLost.update(mockedContainer, mockedGame, 0);
    }

    /**
     * Test whether getID() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetID() throws Exception {
        assertEquals(1, testLost.getID());
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: 1.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased1() throws Exception {
        testLost.keyReleased(Input.KEY_1, 'a');
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: 2.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased2() throws Exception {
        testLost.keyReleased(Input.KEY_2, 'a');
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: 3.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased3() throws Exception {
        testLost.keyReleased(Input.KEY_3, 'a');
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: Space.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedSpace() throws Exception {
        testLost.keyReleased(Input.KEY_SPACE, 'a');

        verify(mockedGame).enterState(5);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: Yen.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedDefault() throws Exception {
        testLost.keyReleased(Input.KEY_YEN, 'a');
    }
}