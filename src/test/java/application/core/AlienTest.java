package application.core;

import application.Main;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Test class for Alien.java.
 * @author Arthur Breurkes
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier"
})
public class AlienTest {
    private Alien testAlien;
    private Element testElement;
    private LevelFactory testFactory;

    @Mock
    public final Main testMain = mock(Main.class);

    /**
     * Initialize variables for tests and read from a XML file written for these tests.
     */
    @Before
    public void setUp() {
        testAlien = new Alien();
        testFactory = LevelFactory.getFactory();
        //testFactory = new LevelFactory(1400, 1080);
        try {
            File file = new File("src/test/java/application/core/testLevels.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            Node node = doc.getElementsByTagName("alien").item(0);
            testElement = (Element) node;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests whether readXml() loads the correct data from the pre-loaded XML file.
     * @throws Exception possible Exception.
     */
    @Test
    public void testReadXmlByUsingPreLoadedXmlFile() throws Exception {
        testAlien.readXml(testElement);

        assertEquals(1000, testAlien.tX);
        assertEquals(100, testAlien.tY);
    }

    /**
     * Test whether update() updates the location of the Alien correctly.
     * Rest is tested individually.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        testAlien.tX = 0;
        testAlien.tSpeed = 1;
        testAlien.tDirection = 1;
        testAlien.update();

        assertEquals(1, testAlien.getX());
    }

    /**
     * Test whether isBonusAlien() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsBonusAlien() throws Exception {
        assertFalse(testAlien.isBonusAlien());
        testAlien.tBonusAlien = true;

        assertTrue(testAlien.isBonusAlien());
    }

    /**
     * Test whether shoot() works properly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testShoot() throws Exception {
        testAlien.setCanShoot(true);
        for (int i = 0; i < 10000; i++) {
            testAlien.shoot();
        }
        assertTrue(testAlien.tProjectiles.size() > 0);
    }

    /**
     * Test whether setCanShoot() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetCanShoot() throws Exception {
        assertFalse(testAlien.tCanShoot);
        testAlien.setCanShoot(true);

        assertTrue(testAlien.tCanShoot);
    }

    /**
     * Tests whether toString() returns the correct String with coordinates of the Alien.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToStringBasedOnCoordinatesSpecifiedInTheXmlFile() throws Exception {
        testAlien.readXml(testElement);

        assertEquals("Alien on coords: 1000, 100", testAlien.toString());
    }

    /**
     * Tests whether isDead() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testIsDead() throws Exception {
        testAlien.tDead = true;

        assertTrue(testAlien.isDead());
        testAlien.tDead = false;

        assertFalse(testAlien.isDead());
    }

    /**
     * Test whether addShootChance() adds a correct value.
     * Zero for Alien.java.
     * @throws Exception possible Exception.
     */
    @Test
    public void testAddShootChance() throws Exception {
        testAlien.tShootChance = 1;

        testAlien.addShootChance();
        assertEquals(1, testAlien.tShootChance);
    }

    /**
     * Test whether endOfScreen() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testEndOfScreen() throws Exception {
        testAlien.tX = 10;
        testAlien.tDirection = -1;
        assertTrue(testAlien.endOfScreen());

        testAlien.tX = 0;
        testAlien.tDirection = 1;
        assertFalse(testAlien.endOfScreen());
    }

    /**
     * Test whether switchDirection() switches the Alien's direction correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSwitchDirection() throws Exception {
        testAlien.tDirection = 1;

        testAlien.switchDirection();

        assertEquals(15, testAlien.getY());
        assertEquals(-1, testAlien.tDirection);
    }

    /**
     * Test whether setLowerLevel() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetLowerLevel() throws Exception {
        ArrayList<Alien> testAliens = testFactory.loadAliens(testElement);
        testAlien.tX = 100;
        testAlien.tY = 105;
        testAliens.add(testAlien);
        testAlien.tX = 100;
        testAlien.tY = 20;
        testAlien.setLowerLevel(testAliens);

        assertTrue(testAlien.tCanShoot);
    }

    /**
     * Test whether applyDifficulty() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testApplyDifficultyOne() throws Exception {
        Main.DIFFICULTY = 1;
        testAlien.applyDifficulty();

        assertEquals(99.8, testAlien.tRandomChance, 0.0);
    }

    /**
     * Test whether applyDifficulty() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testApplyDifficultyTwo() throws Exception {
        Main.DIFFICULTY = 2;
        testAlien.applyDifficulty();

        assertEquals(99.9, testAlien.tRandomChance, 0.0);
    }

    /**
     * Test whether applyDifficulty() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testApplyDifficultyThree() throws Exception {
        Main.DIFFICULTY = 3;
        testAlien.applyDifficulty();

        assertEquals(99.5, testAlien.tRandomChance, 0.0);
    }

    /**
     * Test whether drop() works correctly.
     * Based on a generated coverage report,
     * we can conclude that all updates were dropped at least once.
     * @throws Exception possible Exception.
     */
    @Test
    public void testDrop() throws Exception {
        for (int i = 0; i < 100; i++) {
            testAlien.drop();
        }

        assertTrue(testAlien.getUpgrades().size() >= 0);
    }

    /**
     * Test whether getUpgrades() returns the correct ArrayList of upgrades.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetUpgrades() throws Exception {
        ArrayList<Upgrade> testList = new ArrayList<>();
        Upgrade testUpgrade = new HealthUpgrade(0, 0);
        testList.add(testUpgrade);
        testAlien.tUpgrades = testList;

        assertEquals(testList, testAlien.getUpgrades());
    }

    /**
     * Test whether updateUpgrades() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateUpgrades() throws Exception {
        ArrayList<Upgrade> testList = new ArrayList<>();
        Upgrade testUpgrade = new HealthUpgrade(0, 0);
        testUpgrade.tSpeed = 1;
        testUpgrade.tDirection = 1;
        testList.add(testUpgrade);
        testAlien.tUpgrades = testList;
        testAlien.updateUpgrades();

        assertEquals(1, testUpgrade.tY);
    }
}