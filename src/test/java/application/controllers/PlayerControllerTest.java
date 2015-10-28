package application.controllers;

import application.core.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test class for PlayerController.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})
public class PlayerControllerTest {
    PlayerController testController;

    @Mock
    public final Player mockedPlayer = mock(Player.class);

    /**
     * Initialize variables for the test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testController = new PlayerController(2);
    }

    /**
     * Test whether the object initialization was done correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testNotNull() throws Exception {
        assertNotNull(testController);
    }

    /**
     * Test whether setPlayers() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetPlayers() throws Exception {
        testController.setPlayers(1);

        assertEquals(1, testController.getPlayers().size());
    }

    /**
     * Test whether setPlayers() and getPlayers() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetAndGetPlayersWithArrayList() throws Exception {
        ArrayList<Player> list = new ArrayList<>();
        list.add(new Player());
        list.add(new Player());
        testController.setPlayers(2, list);

        assertEquals(list, testController.getPlayers());
    }

    /**
     * Test whether update() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        ArrayList<Player> list = new ArrayList<>();
        list.add(mockedPlayer);
        testController.setPlayers(1, list);
        testController.update();

        verify(mockedPlayer).update();
    }

    /**
     * Test whether getNumPlayers() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetNumPlayers() throws Exception {
        testController.setPlayers(3);

        assertEquals(3, testController.getNumPlayers());
    }
}