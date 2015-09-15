package application.core;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import static org.junit.Assert.*;

//ToDo: testMove(), testShoot(), testEndOfScreen() and testHit() must still be written (Dependencies unreachable)
/**
 * Test class for Alien.java.
 *
 * @author Arthur Breurkes
 */
public class AlienTest {
    private Alien testAlien;
    private Element testElement;

    /**
     * Initialize variables for tests and read from a XML file written for these tests.
     */
    @Before
    public void setUp() {
        testAlien = new Alien();
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
     *
     * @throws Exception
     */
    @Test
    public void testReadXmlByUsingPreLoadedXmlFile() throws Exception {
        testAlien.readXml(testElement);

        assertEquals(1, testAlien.tX);
        assertEquals(1, testAlien.tY);
    }

//    /**
//     * Tests whether move() moves the Alien correctly.
//     * Done with a mocked image to prevent Exceptions.
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testMove() throws Exception {
//        testAlien.readXml(testElement);
//        testAlien.move();
//
//        assertEquals(2, testAlien.getX());
//    }

//    @Test

//    @Test

    //    public void testShoot() throws Exception {
//
//    }
//    public void testIsLowerLevel() throws Exception {
//
//    }

    /**
     * Tests whether toString() returns the correct String with coordinates of the Alien.
     *
     * @throws Exception
     */
    @Test
    public void testToStringBasedOnCoordinatesSpecifiedInTheXmlFile() throws Exception {
        testAlien.readXml(testElement);

        assertEquals(testAlien.toString(), "Alien on coords: 1, 1");
    }

    /**
     * Tests whether isRemoved() returns the correct boolean value.
     *
     * @throws Exception
     */
    @Test
    public void testIsRemoved() throws Exception {
        testAlien.tRemoved = true;

        assertTrue(testAlien.isRemoved());
        testAlien.tRemoved = false;

        assertFalse(testAlien.isRemoved());
    }

//    @Test
//    public void testHit() throws Exception {
//
//    }

    /**
     * Test whether addShootChance() adds a correct value.
     * Zero for Alien.java.
     *
     * @throws Exception
     */
    @Test
    public void testAddShootChance() throws Exception {
        testAlien.tShootChance = 1;

        testAlien.addShootChance();
        assertEquals(1, testAlien.tShootChance);
    }

//    @Test
//    public void testEndOfScreen() throws Exception {
//
//    }

    /**
     * Test whether switchDirection() switches the Alien's direction correctly.
     *
     * @throws Exception
     */
    @Test
    public void testSwitchDirection() throws Exception {
        testAlien.tDirection = 1;

        testAlien.switchDirection();

        assertEquals(15, testAlien.getY());
        assertEquals(-1, testAlien.tDirection);
    }
}