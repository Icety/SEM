package application.controllers;

import application.Main;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test class for Menu.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier"
})
public class MenuTest {
    private Menu testMenu;

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
        testMenu = new Menu(1);
        testMenu.tMain = mockedGame;
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        testMenu.tExit = true;
        testMenu.update(mockedContainer, mockedGame, 0);

        verify(mockedContainer).exit();
    }

    /**
     * Test whether getID() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetID() throws Exception {
        assertEquals(1, testMenu.getID());
    }

    /**
     * Test whether keyReleased() works correctly;
     * Key: 1.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased1() throws Exception {
        testMenu.keyReleased(Input.KEY_1, 'a');
    }

    /**
     * Test whether keyReleased() works correctly;
     * Key: 2.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased2() throws Exception {
        testMenu.keyReleased(Input.KEY_2, 'a');
    }

    /**
     * Test whether keyReleased() works correctly;
     * Key: 3.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased3() throws Exception {
        testMenu.keyReleased(Input.KEY_3, 'a');

        assertTrue(testMenu.tExit);
    }

    /**
     * Test whether keyReleased() works correctly;
     * Key: 4.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased4() throws Exception {
        testMenu.keyReleased(Input.KEY_4, 'a');
    }

    /**
     * Test whether keyReleased() works correctly;
     * Key: 5.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased5() throws Exception {
        testMenu.keyReleased(Input.KEY_5, 'a');
    }

    /**
     * Test whether keyReleased() works correctly;
     * Key: yen.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedDefault() throws Exception {
        testMenu.keyReleased(Input.KEY_YEN, 'a');
    }
}