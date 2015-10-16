package application.controllers;

import application.Main;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test class for Won.java.
 * @author Arthur Breurkes.
 */
public class WonTest {
    private Won testWon;

    @Mock
    public final Main mockedGame = mock(Main.class);
    @Mock
    public final GameContainer mockedContainer = mock(GameContainer.class);

    /**
     * Initialize variables for the test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testWon = new Won(1);
        testWon.tMain = mockedGame;
    }

    /**
     * Test whether update() works correctly, and thus, does nothing.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        testWon.update(mockedContainer, mockedGame, 0);
    }

    /**
     * Test whether getID() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetID() throws Exception {
        assertEquals(1, testWon.getID());
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: 1.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased1() throws Exception {
        testWon.keyReleased(Input.KEY_1, 'a');

        verify(mockedGame).newGame(false);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: 2.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased2() throws Exception {
        testWon.keyReleased(Input.KEY_2, 'a');
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: 3.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased3() throws Exception {
        testWon.keyReleased(Input.KEY_3, 'a');
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: space.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedSpace() throws Exception {
        testWon.keyReleased(Input.KEY_SPACE, 'a');

        verify(mockedGame).enterState(5);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: yen.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedDefault() throws Exception {
        testWon.keyReleased(Input.KEY_YEN, 'a');
    }
}