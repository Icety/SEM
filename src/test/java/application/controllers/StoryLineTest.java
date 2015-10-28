package application.controllers;

import application.Main;
import application.core.Game;
import application.core.Level;
import application.core.Player;
import application.logger.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Test class for StoryLine.java.
 * @author Arthur Breurkes.
 */
public class StoryLineTest {
    private Game testGame;
    private Level testLevel;
    private StoryLine testStoryLine;
    private PlayerController testController;

    @Mock
    public final Main mockedGame = mock(Main.class);
    @Mock
    public final Level mockedLevel = mock(Level.class);
    @Mock
    public final Player mockedPlayer = mock(Player.class);
    @Mock
    public final Logger mockedLogger = mock(Logger.class);
    @Mock
    public final GameContainer mockedContainer = mock(GameContainer.class);

    /**
     * Setup variables for the testing process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(mockedPlayer);
        testStoryLine = new StoryLine(1);
        testStoryLine.tMain = mockedGame;
        testGame = new Game(0, 0, mockedLogger, 1);
        testController = new PlayerController(1);
        testLevel = new Level(1, testController);
        testLevel.setTheme("");
        testGame.setLevel(testLevel);
        testGame.getPlayerController().setPlayers(1, playerList);

        when(mockedPlayer.getY()).thenReturn(-151);
        when(mockedGame.getGame()).thenReturn(testGame);
        when(mockedLevel.getStoryLine()).thenReturn("");
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateSkip() throws Exception {
        testStoryLine.tCount = 1;
        testGame.setLevel(mockedLevel);
        testStoryLine.update(mockedContainer, mockedGame, 0);

        assertTrue(testStoryLine.tSkip);
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateStartAndCountIsTwo() throws Exception {
        testStoryLine.tCount = 1;
        testStoryLine.tStart = true;
        testStoryLine.update(mockedContainer, mockedGame, 0);

        verify(mockedPlayer).moveUp(testStoryLine.tCount / 50 * 2);
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateStartAndPlayerYIsLowerThanMinus150() throws Exception {
        testStoryLine.tCount = 1;
        testStoryLine.tStart = true;
        testStoryLine.update(mockedContainer, mockedGame, 0);

        assertFalse(testStoryLine.tStart);
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateNotStartAndNotDoneCountIsTwo() throws Exception {
        testStoryLine.tCount = 1;
        testStoryLine.tStart = false;
        testStoryLine.update(mockedContainer, mockedGame, 0);

        assertTrue(testStoryLine.tDone);
        verify(mockedPlayer).moveUp(Math.max(2, 10 - (int) (testStoryLine.tCount / 25)));
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateNotStartAndCountIsTwo() throws Exception {
        testStoryLine.tCount = 1;
        testStoryLine.tDone = true;
        testStoryLine.tStart = false;
        testStoryLine.tTextHeight = 0;
        testStoryLine.update(mockedContainer, mockedGame, 0);

        assertEquals(1, testStoryLine.tTextHeight);
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateNotStartMaxTextHeightHighCount() throws Exception {
        testStoryLine.tCount = 500;
        testStoryLine.tDone = true;
        testStoryLine.tStart = false;
        testStoryLine.tTextHeight = Integer.MAX_VALUE;
        testStoryLine.update(mockedContainer, mockedGame, 0);

        assertEquals(0, testStoryLine.tCount);
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateNotStartMaxTextHeightPlayerMovesUp() throws Exception {
        testStoryLine.tCount = 500;
        testStoryLine.tDone = true;
        testStoryLine.tStart = false;
        testStoryLine.tTextHeight = Integer.MAX_VALUE;
        testStoryLine.update(mockedContainer, mockedGame, 0);

        verify(mockedPlayer).moveUp(testStoryLine.tCount / 50 * 2);
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateNotStartMaxTextHeightCallsNextLevel() throws Exception {
        testStoryLine.tCount = 500;
        testStoryLine.tDone = true;
        testStoryLine.tStart = false;
        testStoryLine.tTextHeight = Integer.MAX_VALUE;
        testStoryLine.update(mockedContainer, mockedGame, 0);

        verify(mockedGame, times(7)).getGame();
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateNotStartLowTextHeightCallsNextLevel() throws Exception {
        testStoryLine.tCount = 8;
        testStoryLine.tDone = true;
        testStoryLine.tStart = false;
        testStoryLine.tTextHeight = Integer.MIN_VALUE;
        testStoryLine.update(mockedContainer, mockedGame, 0);

        verify(mockedPlayer).moveLeft();
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateNotStartLowTextHeightMoveRight() throws Exception {
        testStoryLine.tCount = 650;
        testStoryLine.tDone = true;
        testStoryLine.tStart = false;
        testStoryLine.tTextHeight = Integer.MIN_VALUE;
        testStoryLine.update(mockedContainer, mockedGame, 0);

        verify(mockedPlayer).moveRight();
    }

    /**
     * Test whether getID() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetID() throws Exception {
        assertEquals(1, testStoryLine.getID());
    }

    /**
     * Test whether resetValues() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testResetValues() throws Exception {
        testStoryLine.resetValues();

        assertFalse(testStoryLine.tDone);
        assertEquals(0, testStoryLine.tCount);
        assertEquals(-300, testStoryLine.tTextHeight);
    }

    /**
     * Test whether getStory() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetStory() throws Exception {
        assertEquals("those aliens, are they?? Pandaliens??\n\n"
                        + "Now the ship is on it's way. But wait! What are\n\n"
                        + "the aliens, so it could be destroyed.\n\n"
                        + "left it's safe home, in search for the planet of \n\n"
                        + "retaliate! Their most powerful ship: The Thomas \n\n"
                        + "planet was under attack, they would have to \n\n"
                        + "it became clear to the SEMmians that their \n\n"
                        + "After destroying the first layer of aliens",
                testStoryLine.getStory());
    }

    /**
     * Test whether keyPressed() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedSpace() throws Exception {
        testStoryLine.keyPressed(Input.KEY_SPACE, 'a');

        assertTrue(testStoryLine.tSkip);
    }

    /**
     * Test whether keyPressed() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedDefault() throws Exception {
        testStoryLine.keyPressed(Input.KEY_YEN, 'a');
    }
}