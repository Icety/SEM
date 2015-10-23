package application.controllers;

import application.Main;
import application.core.Game;
import application.core.Level;
import application.logger.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for Levels.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier"
})
public class LevelsTest {
    private Levels testLevels;
    private Main testMain;
    private Game testGame;
    private Level testLevel;

    @Mock
    public final Main mockedGame = mock(Main.class);
    @Mock
    public final GameContainer mockedContainer = mock(GameContainer.class);
    @Mock
    public final Logger mockedLogger = mock(Logger.class);

    /**
     * Initialize variables for the testing process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testLevels = new Levels(1);
        testMain = new Main("Test");
        testGame = new Game(0, 0, mockedLogger, 1);
        testLevel = new Level(1, testGame.getPlayerController());
        testLevel.setBackground("background.jpg");
        testLevel.settAliens(new ArrayList<>());
        testLevel.setMusic("normalmusic.wav");
        testLevel.setTheme("");
        testLevels.tTheme = "";
        testGame.setLevel(testLevel);
        testMain.setGame(testGame);
        testLevels.tMain = testMain;

        when(mockedGame.getGame()).thenReturn(testGame);
    }

    /**
     * Test whether the created controller is initialized correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testLevelsNotNull() throws Exception {
        assertNotNull(testLevels);
    }

    /**
     * Test whether getID() returns the correct ID.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetID() throws Exception {
        assertEquals(1, testLevels.getID());
    }

    /**
     * Test whether keyPressed() works correctly with left button pressed.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedLeft() throws Exception {
        testLevels.keyPressed(Input.KEY_LEFT, 'a');

        assertTrue(testLevels.tMain.getGame().getPlayerController().getPlayers().get(0).isGoLeft());
    }

    /**
     * Test whether keyPressed() works correctly with right button pressed.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedRight() throws Exception {
        testLevels.keyPressed(Input.KEY_RIGHT, 'a');

        assertTrue(testLevels.tMain.getGame().getPlayerController().getPlayers().get(0).isGoRight());
    }

    /**
     * Test whether keyPressed() works correctly with up pressed.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedUp() throws Exception {
        testLevels.keyPressed(Input.KEY_UP, 'a');

        assertTrue(testLevels.tMain.getGame().getPlayerController().getPlayers().get(0).isShoot());
    }

    /**
     * Test whether keyPressed() works correctly with the escapeKey pressed.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedEscape() throws Exception {
        testLevels.keyPressed(Input.KEY_ESCAPE, 'a');

        assertTrue(testLevels.pause);
    }

    /**
     * Test whether keyPressed() works correctly with an unused, non-used key.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedDefault() throws Exception {
        testLevels.keyPressed(Input.KEY_YEN, 'a');
    }

    /**
     * Test whether keyReleased() works correctly with the escape key.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedEscape() throws Exception {
        testLevels.keyReleased(Input.KEY_ESCAPE, 'a');

        assertFalse(testLevels.pause);
    }

    /**
     * Test whether keyReleased() works correctly with an unused key.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedDefault() throws Exception {
        testLevels.keyReleased(Input.KEY_YEN, 'a');
    }
}
