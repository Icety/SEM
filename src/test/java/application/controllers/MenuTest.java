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
public class MenuTest {
    private Menu testMenu;

    @Mock
    public final Main mockedGame = mock(Main.class);
    @Mock
    public final GameContainer mockedContainer = mock(GameContainer.class);

    @Before
    public void setUp() throws Exception {
        testMenu = new Menu(1);
        testMenu.tMain = mockedGame;
    }

    @Test
    public void testUpdate() throws Exception {
        testMenu.tExit = true;
        testMenu.update(mockedContainer, mockedGame, 0);

        verify(mockedContainer).exit();
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals(1, testMenu.getID());
    }

    @Test
    public void testKeyReleased1() throws Exception {
        testMenu.keyReleased(Input.KEY_1, 'a');
    }

    @Test
    public void testKeyReleased2() throws Exception {
        testMenu.keyReleased(Input.KEY_2, 'a');
    }

    @Test
    public void testKeyReleased3() throws Exception {
        testMenu.keyReleased(Input.KEY_3, 'a');

        assertTrue(testMenu.tExit);
    }

    @Test
    public void testKeyReleased4() throws Exception {
        testMenu.keyReleased(Input.KEY_4, 'a');
    }

    @Test
    public void testKeyReleased5() throws Exception {
        testMenu.keyReleased(Input.KEY_5, 'a');
    }

    @Test
    public void testKeyReleasedDefault() throws Exception {
        testMenu.keyReleased(Input.KEY_YEN, 'a');
    }
}