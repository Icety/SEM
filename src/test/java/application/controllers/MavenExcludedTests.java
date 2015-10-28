package application.controllers;

import application.Main;
import application.core.Game;
import application.core.Level;
import application.logger.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.newdawn.slick.GameContainer;


import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for tests that should be ignored by Maven.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier"
})
public class MavenExcludedTests {
    private Levels testLevels;
    private Game testGame;
    private Level testLevel;

    @Mock
    public final Main mockedMain = mock(Main.class);
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
        testLevels.tMain = mockedMain;
        testGame = new Game(0, 0, mockedLogger, 1);
        testLevel = new Level(1, testGame.getPlayerController());
        testLevel.settAliens(new ArrayList<>());
        testGame.setLevel(testLevel);

        when(mockedMain.getGame()).thenReturn(testGame);
    }

    /**
     * Test whether update() works correctly when the level is won.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateHasWon() throws Exception {
        testLevels.pause = false;
        testGame.setHasWon(true);
        testLevels.update(mockedContainer, mockedMain, 0);
    }

    /**
     * Test whether update() works correctly when the level is lost.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateHasLost() throws Exception {
        testLevels.pause = false;
        testGame.setHasLost(true);
        testLevels.update(mockedContainer, mockedMain, 0);
    }
}
