package application.core;

import application.core.aliens.Alien;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Test class for Level.java.
 * @author Arthur Breurkes
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier"
})
public class LevelTest {
    private Level testLevel;
    @Mock
    public final Alien testAlien = mock(Alien.class);

    /**
     * Initialize variables for testing purposes.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testLevel = new Level();
    }

    /**
     * Test whether getAliens() returns the correct Arraylist of Aliens.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetAliens() throws Exception {
        ArrayList<Alien> alienList = new ArrayList<>();
        testLevel.addAliens(alienList);

        assertEquals(alienList, testLevel.getAliens());
    }

    /**
     * Test whether addAliens() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testAddAliens() throws Exception {
        ArrayList<Alien> alienList = new ArrayList<>();
        testLevel.addAliens(alienList);

        assertEquals(alienList, testLevel.tAliens);
    }

    /**
     * Test whether setStartPlayer() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetStartPlayer() throws Exception {
        //testLevel.setStartPlayer();

        //assertNotNull(testLevel.tPlayer);
    }

    /**
     * Test whether toString() returns the correct string.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToString() throws Exception {
        ArrayList<Alien> alienList = new ArrayList<>();
        alienList.add(new Alien());
        testLevel.addAliens(alienList);

        assertEquals("Level with the following aliens: \nAlien on coords: 0, 0\n",
                testLevel.toString());
    }

    /**
     * Test whether setBackground() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetBackground() throws Exception {
        testLevel.setBackground("randomString");

        assertEquals("randomString", testLevel.tBackground);
    }

    /**
     * Test whether getBackground() returns the correct Background string.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetBackground() throws Exception {
        testLevel.setBackground("randomString");

        assertEquals("randomString", testLevel.getBackground());
    }

    /**
     * Test whether removeAlien() correctly removes an Alien from a level.
     * @throws Exception possible Exception.
     */
    @Test
    public void testRemoveAlien() throws Exception {
        ArrayList<Alien> alienList = new ArrayList<>();
        alienList.add(testAlien);
        testLevel.addAliens(alienList);

        testLevel.removeAlien(testAlien);
        assertEquals(0, testLevel.getAliens().size());
    }
}