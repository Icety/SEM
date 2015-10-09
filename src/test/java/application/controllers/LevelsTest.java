package application.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Test class for Levels.java.
 *
 * @author Arthur Breurkes
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier"
})
public class LevelsTest {
    private Levels testLevels;

    @Mock
    public final StateBasedGame mockedGame = mock(StateBasedGame.class);
    @Mock
    public final GameContainer mockedContainer = mock(GameContainer.class);

    /**
     * Initialize variables for the testing process.
     */
    @Before
    public void setUp() {
        testLevels = new Levels(1);
    }

    /**
     * Test whether the created controller is initialized correctly.
     */
    @Test
    public void testLevelsNotNull() {
        assertNotNull(testLevels);
    }

    /**
     * Test whether init() works correctly.
     * @throws Exception possible Exception.
     */
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

    /**
     * Test whether getID() returns the correct ID.
     */
    @Test
    public void testGetID() {
        assertEquals(1, testLevels.getID());
    }
}
