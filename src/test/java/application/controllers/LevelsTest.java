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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
     */
    @Before
    public void setUp() {
        testLevels = new Levels(1);
        testMain = new Main("Test");
        testGame = new Game(0, 0, mockedLogger, false);
        testLevel = new Level();
        testLevel.setBackground("background.jpg");
        testLevel.settAliens(new ArrayList<>());
        testGame.setLevel(testLevel);
        testMain.setGame(testGame);
        testLevels.tMain = testMain;

        when(mockedGame.getGame()).thenReturn(testGame);
    }

    /**
     * Test whether the created controller is initialized correctly.
     */
    @Test
    public void testLevelsNotNull() {
        assertNotNull(testLevels);
    }

    /**
     * Test whether getID() returns the correct ID.
     */
    @Test
    public void testGetID() {
        assertEquals(1, testLevels.getID());
    }

    @Test
    public void testUpdateHasWon() throws Exception {
        testLevels.pause = false;
        testGame.setHasWon(true);
        testLevels.update(mockedContainer, mockedGame, 0);
    }

    @Test
    public void testUpdateHasLost() throws Exception {
        testLevels.pause = false;
        testGame.setHasLost(true);
        testLevels.update(mockedContainer, mockedGame, 0);
    }

    @Test
    public void testKeyPressedLeft() {
        testLevels.keyPressed(Input.KEY_LEFT, 'a');

        assertTrue(testLevels.tMain.getGame().getPlayer().getGoLeft());
    }

    @Test
    public void testKeyPressedRight() {
        testLevels.keyPressed(Input.KEY_RIGHT, 'a');

        assertTrue(testLevels.tMain.getGame().getPlayer().getGoRight());
    }
}
