package application.logger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.PrintWriter;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Test class for Logger.java.
 * @author Arthur Breurkes.
 */
public class LoggerTest {
    private Log testLog;
    private Logger testLogger;

    @Mock
    public final PrintWriter testWriter = mock(PrintWriter.class);

    /**
     * Initialize variables for the test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testLogger = new Logger();
        testLog = new Log("Test", 3);
        testLogger.tWriter = testWriter;
    }

    /**
     * Test whether startLogging() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testStartLogging() throws Exception {
        testLogger.tWriter = null;
        testLogger.startLogging();

        assertNotNull(testLogger.tWriter);
    }

    /**
     * Test whether stopLogging() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testStopLogging() throws Exception {
        testLogger.stopLogging();

        verify(testWriter).close();
    }

    /**
     * Test whether writeLog() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testWriteLog() throws Exception {
        testLogger.writeLog(testLog);

        verify(testWriter).println(testLogger.tFormatter.format(testLog));
    }

    /**
     * Test whether setLog() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testSetLog() throws Exception {
        testLogger.setLog("Test", 3);

        verify(testWriter).println(testLogger.tFormatter.format(testLog));
    }
}