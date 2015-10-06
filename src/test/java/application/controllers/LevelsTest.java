package application.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Test class for Levels.java.
 *
 * @author Arthur Breurkes
 */
public class LevelsTest {
    private Levels testLevels;

    @Mock
    public final StateBasedGame mockedGame = mock(StateBasedGame.class);
    @Mock
    public final GameContainer mockedContainer = mock(GameContainer.class);

    @Before
    public void setUp() {
        testLevels = new Levels(1);
    }

    @Test
    public void testLevelsNotNull() {
        assertNotNull(testLevels);
    }

    @Test
    public void testInit() throws Exception {
        try {
            testLevels.init(mockedContainer, mockedGame);
            Image actualImage = new Image("src/main/java/application/images/backgrounds/moving.jpg");

            assertNotNull(testLevels.tMain);
            assertEquals(actualImage, testLevels.tBackground);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetID() {
        assertEquals(1, testLevels.getID());
    }
}
