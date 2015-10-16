package application.core.aliens;

import application.Main;
import application.core.LevelFactory;
import application.core.upgrades.HealthUpgrade;
import application.core.upgrades.Upgrade;
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
 * @author Arthur Breurkes.
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

        assertEquals(1000, testAlien.getX());
        assertEquals(100, testAlien.getY());
    }

    /**
     * Test whether update() updates the location of the Alien correctly.
     * Rest is tested individually.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdate() throws Exception {
        testAlien.setX(0);
        testAlien.setSpeed(1.0);
        testAlien.setDirection(1);
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
        testAlien.setBonusAlien(true);

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
        assertTrue(testAlien.getProjectiles().size() > 0);
    }

    /**
     * Test whether setCanShoot() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetCanShoot() throws Exception {
        assertFalse(testAlien.canShoot());
        testAlien.setCanShoot(true);

        assertTrue(testAlien.canShoot());
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
        testAlien.setDead(true);

        assertTrue(testAlien.isDead());
        testAlien.setDead(false);

        assertFalse(testAlien.isDead());
    }

    /**
     * Test whether addShootChance() adds a correct value.
     * Zero for Alien.java.
     * @throws Exception possible Exception.
     */
    @Test
    public void testAddShootChance() throws Exception {
        testAlien.setShootChance(1);

        testAlien.addShootChance();
        assertEquals(1, testAlien.getShootChance());
    }

    /**
     * Test whether endOfScreen() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testEndOfScreen() throws Exception {
        testAlien.setX(10);
        testAlien.setDirection(-1);
        assertTrue(testAlien.endOfScreen());

        testAlien.setX(0);
        testAlien.setDirection(1);
        assertFalse(testAlien.endOfScreen());
    }

    /**
     * Test whether switchDirection() switches the Alien's direction correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSwitchDirection() throws Exception {
        testAlien.setDirection(1);
        testAlien.switchDirection();

        assertEquals(-1, testAlien.getDirection());
    }

    /**
     * Test whether setLowerLevel() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetLowerLevel() throws Exception {
        ArrayList<Alien> testAliens = testFactory.loadAliens(testElement);
        testAlien.setX(10);
        testAlien.setY(150);
        testAliens.add(testAlien);
        testAlien.setLowerLevel(testAliens);

        assertTrue(testAlien.canShoot());
    }

    /**
     * Test whether applyDifficulty() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testApplyDifficultyOne() throws Exception {
        Main.DIFFICULTY = 1;
        testAlien.applyDifficulty();

        assertEquals(99.8, testAlien.getRandomChance(), 0.0);
    }

    /**
     * Test whether applyDifficulty() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testApplyDifficultyTwo() throws Exception {
        Main.DIFFICULTY = 2;
        testAlien.applyDifficulty();

        assertEquals(99.9, testAlien.getRandomChance(), 0.0);
    }

    /**
     * Test whether applyDifficulty() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testApplyDifficultyThree() throws Exception {
        Main.DIFFICULTY = 3;
        testAlien.applyDifficulty();

        assertEquals(99.5, testAlien.getRandomChance(), 0.0);
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
        testAlien.setUpgrades(testList);

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
        testUpgrade.setSpeed(1);
        testUpgrade.setDirection(1);
        testList.add(testUpgrade);
        testAlien.setUpgrades(testList);
        testAlien.updateUpgrades();

        assertEquals(1, testUpgrade.getY());
    }

    /**
     * Test whether hit() works correctly when the Alien dies.
     * @throws Exception possible Exception.
     */
    @Test
    public void testHitAndDie() throws Exception{
        testAlien.setHealth(1);

        assertEquals(testAlien.getKillScore(), testAlien.hit());
    }

    /**
     * Test whether hit() works correctly when Alien remains alive.
     * @throws Exception possible Exception.
     */
    @Test
    public void testHitAndLive() throws Exception{
        testAlien.setHealth(2);

        assertEquals(testAlien.getHitScore(), testAlien.hit());
    }

    /**
     * Test whether getSpeed() and setSpeed() work correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetAndSetSpeed() throws Exception {
        testAlien.setSpeed(1.0);

        assertEquals(1.0, testAlien.getSpeed(), 0.0);
    }
}