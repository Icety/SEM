package application.core;

import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

/**
 * Tests for the class Alien.java.
 *
 * Created by Arthur on 9/5/15.
 */
//ToDo: hitTest is yet to be written.
public class AlienTest {
    private Alien testAlien;
    private Element testElement;
    @Mock
    private final Image testImage = mock(Image.class);

    /**
     * Initialized variables for tests and read from a XML file written for these tests.
     */
    @Before
    public void setUp() {
        testAlien = new Alien();
        try {
            File file = new File("test/application/core/testLevels.xml");
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

        assertEquals(testAlien.tX, 1);
        assertEquals(testAlien.tY, 1);
    }

    /**
     * Tests whether getX() gets the correct X coordinate of the Alien.
     *
     * @throws Exception
     */
    @Test
    public void testGetXWithXSpecifiedByXmlFile() throws Exception {
        testAlien.readXml(testElement);

        assertEquals(testAlien.getX(), 1);
    }

    /**
     * Tests whether getY() gets the correct Y coordinate of the Alien.
     *
     * @throws Exception
     */
    @Test
    public void testGetYWithXSpecifiedByXmlFile() throws Exception {
        testAlien.readXml(testElement);

        assertEquals(testAlien.getY(), 1);
    }

    /**
     * Tests whether getImage() returns the correct image of the Alien.
     * Should be null, because none is ever specified in Alien.java.
     *
     * @throws Exception
     */
    @Test
    public void testGetImageWhichIsNullByDefault() throws Exception {
        testAlien.readXml(testElement);

        assertNull(testAlien.getImage());
    }

    /**
     * Tests whether move() moves the Alien correctly.
     * Done with a mocked image to prevent Exceptions.
     *
     * @throws Exception
     */
    @Test
    public void testMoveWhileLoadingAMockedImage() throws Exception {
        testAlien.readXml(testElement);
        testAlien.tImage = testImage;
        testAlien.move();

        assertEquals(2, testAlien.getX());
    }

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
}