package application.logger;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Test class for Log.java.
 * @author Arthur Breurkes.
 */
public class LogTest {
    private Log testLog;

    /**
     * Initialize variables for the test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testLog = new Log("Test", 0);
    }

    /**
     * Test whether getType() works correctly with message: SEVERE.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetTypeSevere() throws Exception {
        testLog.tImportant = 0;

        assertEquals("SEVERE", testLog.getType());
    }

    /**
     * Test whether getType() works correctly with message: WARNING.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetTypeWarning() throws Exception {
        testLog.tImportant = 1;

        assertEquals("WARNING", testLog.getType());
    }

    /**
     * Test whether getType() works correctly with message: INFO.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetTypeInfo() throws Exception {
        testLog.tImportant = 2;

        assertEquals("INFO", testLog.getType());
    }

    /**
     * Test whether getType() works correctly with message: CONFIG.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetTypeConfig() throws Exception {
        testLog.tImportant = 3;

        assertEquals("CONFIG", testLog.getType());
    }

    /**
     * Test whether getType() works correctly with message: FINE.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetTypeFine() throws Exception {
        testLog.tImportant = 4;

        assertEquals("FINE", testLog.getType());
    }

    /**
     * Test whether getType() works correctly with message: FINER.
     * @throws Exception possible Exception.
     */
    @Test
     public void testGetTypeFiner() throws Exception {
        testLog.tImportant = 5;

        assertEquals("FINER", testLog.getType());
    }

    /**
     * Test whether getType() works correctly with message: FINEST.
     * @throws Exception possible Exception.
     */
    @Test
     public void testGetTypeFinest() throws Exception {
        testLog.tImportant = 6;

        assertEquals("FINEST", testLog.getType());
    }

    /**
     * Test whether getType() works correctly with message: INFO.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetTypeElse() throws Exception {
        testLog.tImportant = 7;

        assertEquals("INFO", testLog.getType());
    }

    /**
     * TEst whether getMessage() returns the correct message.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetMessage() throws Exception {
        assertEquals("Test", testLog.getMessage());
    }

    /**
     * Test whether getColor() returns the correct value: red.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetColorRed() throws Exception {
        testLog.tImportant = 0;

        assertEquals("red", testLog.getColor());
    }

    /**
     * Test whether getColor() returns the correct value: orange.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetColorOrange() throws Exception {
        testLog.tImportant = 1;

        assertEquals("orange", testLog.getColor());
    }

    /**
     * Test whether getColor() returns the correct value: blue.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetColorBlue() throws Exception {
        testLog.tImportant = 2;

        assertEquals("blue", testLog.getColor());
    }

    /**
     * Test whether getColor() returns the correct value: purple.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetColorPurple() throws Exception {
        testLog.tImportant = 3;

        assertEquals("purple", testLog.getColor());
    }

    /**
     * Test whether getColor() returns the correct value: yellow.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetColorYellow() throws Exception {
        testLog.tImportant = 4;

        assertEquals("yellow", testLog.getColor());
    }

    /**
     * Test whether getColor() returns the correct value: light-green.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetColorLightGreen() throws Exception {
        testLog.tImportant = 5;

        assertEquals("light-green", testLog.getColor());
    }

    /**
     * Test whether getColor() returns the correct value: green.
     * @throws Exception possible Exception.
     */
    @Test
     public void testGetColorGreen() throws Exception {
        testLog.tImportant = 6;

        assertEquals("green", testLog.getColor());
    }

    /**
     * Test whether getColor() returns the correct value: blue.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetColorElse() throws Exception {
        testLog.tImportant = 7;

        assertEquals("blue", testLog.getColor());
    }

    /**
     * Test whether getTime() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetTime() throws Exception {
        testLog.tTime = 1;

        assertEquals(1, testLog.getTime());
    }
}