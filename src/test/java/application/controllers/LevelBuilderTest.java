package application.controllers;

import application.Main;
import application.core.aliens.BigAlien;
import application.core.aliens.MiniAlien;
import application.core.aliens.SmallAlien;
import application.core.aliens.FinalBoss;
import application.core.aliens.MothershipAlien;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.gui.TextField;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for LevelBuilder.java.
 * @author Arthur Breurkes.
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})
public class LevelBuilderTest {
    private BigAlien testAlien;
    private LevelBuilder testBuilder;

    @Mock
    public final Main mockedGame = mock(Main.class);
    @Mock
    public final GameContainer mockedContainer = mock(GameContainer.class);
    @Mock
    public final TextField mockedTextField = mock(TextField.class);

    /**
     * Initialize variables for the testing process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testAlien = new BigAlien();
        testAlien.setX(10);
        testAlien.setY(10);
        testBuilder = new LevelBuilder(1);
        testBuilder.tMain = mockedGame;
        testBuilder.circle = new Circle(Main.WIDTH / 2, Main.HEIGHT / 3, 70);
        testBuilder.menuHeight = 0;
        testBuilder.saveName = mockedTextField;

        when(mockedTextField.getText()).thenReturn("Test");
    }

    /**
     * Test whether getID() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetID() throws Exception {
        assertEquals(1, testBuilder.getID());
    }

    /**
     * Test whether update() works correctly.
     * Using: up.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateUp() throws Exception {
        testBuilder.circley = 10;
        testBuilder.circleUp = true;
        testBuilder.circleDown = false;
        testBuilder.circleLeft = false;
        testBuilder.circleRight = false;
        testBuilder.update(mockedContainer, mockedGame, 0);

        assertEquals(6, testBuilder.circley);
    }

    /**
     * Test whether update() works correctly.
     * Using: down.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateDown() throws Exception {
        testBuilder.circley = 10;
        testBuilder.circleUp = false;
        testBuilder.circleDown = true;
        testBuilder.circleLeft = false;
        testBuilder.circleRight = false;
        testBuilder.update(mockedContainer, mockedGame, 0);

        assertEquals(14, testBuilder.circley);
    }

    /**
     * Test whether update() works correctly.
     * Using: right.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateRight() throws Exception {
        testBuilder.circlex = 10;
        testBuilder.circleUp = false;
        testBuilder.circleDown = false;
        testBuilder.circleLeft = false;
        testBuilder.circleRight = true;
        testBuilder.update(mockedContainer, mockedGame, 0);

        assertEquals(14, testBuilder.circlex);
    }

    /**
     * Test whether update() works correctly.
     * Using: left.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateLeft() throws Exception {
        testBuilder.circlex = 10;
        testBuilder.circleUp = false;
        testBuilder.circleDown = false;
        testBuilder.circleLeft = true;
        testBuilder.circleRight = false;
        testBuilder.update(mockedContainer, mockedGame, 0);

        assertEquals(6, testBuilder.circlex);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: 1.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased1() throws Exception {
        testBuilder.keyReleased(Input.KEY_1, 'a');

        assertTrue(testBuilder.selected instanceof MiniAlien);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: 2.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased2() throws Exception {
        testBuilder.keyReleased(Input.KEY_2, 'a');

        assertTrue(testBuilder.selected instanceof SmallAlien);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: 3.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased3() throws Exception {
        testBuilder.keyReleased(Input.KEY_3, 'a');

        assertTrue(testBuilder.selected instanceof BigAlien);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: 4.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased4() throws Exception {
        testBuilder.keyReleased(Input.KEY_4, 'a');

        assertTrue(testBuilder.selected instanceof MothershipAlien);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: 5.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleased5() throws Exception {
        testBuilder.keyReleased(Input.KEY_5, 'a');

        assertTrue(testBuilder.selected instanceof FinalBoss);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: Down.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedDown() throws Exception {
        testBuilder.circleDown = true;
        testBuilder.keyReleased(Input.KEY_DOWN, 'a');

        assertFalse(testBuilder.circleDown);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: Up.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedUp() throws Exception {
        testBuilder.circleUp = true;
        testBuilder.keyReleased(Input.KEY_UP, 'a');

        assertFalse(testBuilder.circleUp);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: Left.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedLeft() throws Exception {
        testBuilder.circleLeft = true;
        testBuilder.keyReleased(Input.KEY_LEFT, 'a');

        assertFalse(testBuilder.circleLeft);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: Right.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedRight() throws Exception {
        testBuilder.circleRight = true;
        testBuilder.keyReleased(Input.KEY_RIGHT, 'a');

        assertFalse(testBuilder.circleRight);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: Space.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedSpace() throws Exception {
        testBuilder.aliens = new ArrayList<>();
        testBuilder.selected = new SmallAlien();
        testBuilder.keyReleased(Input.KEY_SPACE, 'a');

        assertEquals(1, testBuilder.aliens.size());
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: R.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedR() throws Exception {
        testBuilder.aliens.add(testAlien);
        testBuilder.circle.setLocation(10.0f, 10.0f);
        testBuilder.keyReleased(Input.KEY_R, 'a');

        assertFalse(testBuilder.aliens.contains(testAlien));
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: Escape.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedEscape() throws Exception {
        testBuilder.focus = true;
        testBuilder.saveGame = true;
        testBuilder.keyReleased(Input.KEY_ESCAPE, 'a');

        assertFalse(testBuilder.focus);
        assertFalse(testBuilder.saveGame);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: Enter.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedEnter() throws Exception {
        testBuilder.saveGame = true;
        testBuilder.keyReleased(Input.KEY_ENTER, 'a');

        assertFalse(testBuilder.saveGame);
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: Tab.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedTab() throws Exception {
        testBuilder.keyReleased(Input.KEY_TAB, 'a');
    }

    /**
     * Test whether keyReleased() works correctly.
     * Key: Yen.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyReleasedDefault() throws Exception {
        testBuilder.keyReleased(Input.KEY_YEN, 'a');
    }

    /**
     * Test whether keyPressed() works correctly.
     * Key: Down.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedDown() throws Exception {
        testBuilder.circleDown = false;
        testBuilder.keyPressed(Input.KEY_DOWN, 'a');

        assertTrue(testBuilder.circleDown);
    }

    /**
     * Test whether keyPressed() works correctly.
     * Key: Up.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedUp() throws Exception {
        testBuilder.circleUp = false;
        testBuilder.keyPressed(Input.KEY_UP, 'a');

        assertTrue(testBuilder.circleUp);
    }

    /**
     * Test whether keyPressed() works correctly.
     * Key: Left.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedLeft() throws Exception {
        testBuilder.circleLeft = false;
        testBuilder.keyPressed(Input.KEY_LEFT, 'a');

        assertTrue(testBuilder.circleLeft);
    }

    /**
     * Test whether keyPressed() works correctly.
     * Key: Right.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedRight() throws Exception {
        testBuilder.circleRight = false;
        testBuilder.keyPressed(Input.KEY_RIGHT, 'a');

        assertTrue(testBuilder.circleRight);
    }

    /**
     * Test whether keyPressed() works correctly.
     * Key: Yen.
     * @throws Exception possible Exception.
     */
    @Test
    public void testKeyPressedDefault() throws Exception {
        testBuilder.keyPressed(Input.KEY_YEN, 'a');
    }

    /**
     * Test whether checkCollision() works on a hit.
     * @throws Exception possible Exception.
     */
    @Test
    public void testCheckCollisionHit() throws Exception {
        testBuilder.aliens.add(testAlien);
        testBuilder.circle.setLocation(10.0f, 10.0f);

        assertEquals(testAlien, testBuilder.checkCollision());
    }

    /**
     * Test whether checkCollision() works on a non-hit.
     * @throws Exception possible Exception.
     */
    @Test
    public void testCheckCollisionNoHit() throws Exception {
        assertNull(testBuilder.checkCollision());
    }

    /**
     * Test whether placeAlien() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testPlaceAlien() throws Exception {
        testBuilder.placeAlien(testAlien, 10, 10);

        assertTrue(testBuilder.aliens.contains(testAlien));
    }

    /**
     * Test whether makeAlien() works correctly.
     * Alien: MiniAlien.
     * @throws Exception possible Exception.
     */
    @Test
    public void testMakeAlienMini() throws Exception {
        testBuilder.selected = new MiniAlien();

        assertTrue(testBuilder.makeAlien() instanceof MiniAlien);
    }

    /**
     * Test whether makeAlien() works correctly.
     * Alien: SmallAlien.
     * @throws Exception possible Exception.
     */
    @Test
    public void testMakeAlienSmall() throws Exception {
        testBuilder.selected = new SmallAlien();

        assertTrue(testBuilder.makeAlien() instanceof SmallAlien);
    }

    /**
     * Test whether makeAlien() works correctly.
     * Alien: BigAlien.
     * @throws Exception possible Exception.
     */
    @Test
    public void testMakeAlienBig() throws Exception {
        testBuilder.selected = new BigAlien();

        assertTrue(testBuilder.makeAlien() instanceof BigAlien);
    }

    /**
     * Test whether makeAlien() works correctly.
     * Alien: FinalBoss.
     * @throws Exception possible Exception.
     */
    @Test
    public void testMakeAlienFinal() throws Exception {
        testBuilder.selected = new FinalBoss();

        assertTrue(testBuilder.makeAlien() instanceof FinalBoss);
    }

    /**
     * Test whether makeAlien() works correctly.
     * Alien: MothershipAlien.
     * @throws Exception possible Exception.
     */
    @Test
    public void testMakeAlienMothership() throws Exception {
        testBuilder.selected = new MothershipAlien();

        assertTrue(testBuilder.makeAlien() instanceof MothershipAlien);
    }

    /**
     * Test whether makeAlien() works correctly.
     * Alien: null.
     * @throws Exception possible Exception.
     */
    @Test
    public void testMakeAlienNull() throws Exception {
        testBuilder.selected = null;

        assertNull(testBuilder.makeAlien());
    }

    /**
     * Test whether toXML() does not return any Exceptions.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToXML() throws Exception {
        testBuilder.aliens.add(testAlien);
        testBuilder.toXML("Test");
    }

    /**
     * Test whether classToXML() works correctly.
     * Alien: MiniAlien.
     * @throws Exception possible Exception.
     */
    @Test
    public void testClassToXMLMini() throws Exception {
        assertEquals("mini", testBuilder.classToXML(new MiniAlien()));
    }

    /**
     * Test whether classToXML() works correctly.
     * Alien: SmallAlien.
     * @throws Exception possible Exception.
     */
    @Test
    public void testClassToXMLSmall() throws Exception {
        assertEquals("small", testBuilder.classToXML(new SmallAlien()));
    }

    /**
     * Test whether classToXML() works correctly.
     * Alien: BigAlien.
     * @throws Exception possible Exception.
     */
    @Test
    public void testClassToXMLBig() throws Exception {
        assertEquals("big", testBuilder.classToXML(new BigAlien()));
    }

    /**
     * Test whether classToXML() works correctly.
     * Alien: FinalBoss.
     * @throws Exception possible Exception.
     */
    @Test
    public void testClassToXMLFinal() throws Exception {
        assertEquals("boss", testBuilder.classToXML(new FinalBoss()));
    }

    /**
     * Test whether classToXML() works correctly.
     * Alien: MothershipAlien.
     * @throws Exception possible Exception.
     */
    @Test
    public void testClassToXMLMothership() throws Exception {
        assertEquals("mother", testBuilder.classToXML(new MothershipAlien()));
    }

    /**
     * Test whether classToXML() works correctly.
     * Alien: null.
     * @throws Exception possible Exception.
     */
    @Test
    public void testClassToXMLNull() throws Exception {
        assertNull(testBuilder.classToXML(null));
    }
}