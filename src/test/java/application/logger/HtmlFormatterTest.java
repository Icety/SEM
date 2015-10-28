package application.logger;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test class for HtmlFormatter.java.
 * @author Arthur Breurkes
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class HtmlFormatterTest {
    private Log testLog;
    private HtmlFormatter testFormatter;

    /**
     * Setup variables for the test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testLog = new Log("Test", 3);
        testFormatter = new HtmlFormatter();
    }

    /**
     * Test whether format() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testFormat() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        Date resultDate = new Date(testLog.getTime());
        String dateTime = dateFormat.format(resultDate);

        String wanted = "<tr>\n"
                + "\t<th style=\"color: purple\">CONFIG<th>\n"
                + "<td>Test</td>\n"
                + "<td>"+ dateTime + "</td></tr>\n";

        assertEquals(wanted, testFormatter.format(testLog));
    }

    /**
     * Test whether parseTime() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testParseTime() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        Date resultDate = new Date(testLog.getTime());
        String dateTime = dateFormat.format(resultDate);

        assertEquals(dateTime, testFormatter.parseTime(testLog.getTime()));
    }

    /**
     * Test whether getHeader() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetHeader() throws Exception {
        String desired = "<!DOCTYPE html>\n<head>\n<style>\n"
                + "table { width: 100% }\n"
                + "th { font:bold 10pt Tahoma; }\n"
                + "td { font:normal 10pt Tahoma; }\n"
                + "h1 {font:normal 11pt Tahoma;}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>" + (new Date()) + "</h1>\n"
                + "<table border=\"0\" cellpadding=\"5\" cellspacing=\"3\">\n"
                + "<tr align=\"left\">\n"
                + "\t<th style=\"width:10%\">Loglevel</th>\n"
                + "\t<th style=\"width:15%\">Time</th>\n"
                + "\t<th style=\"width:75%\">Log Message</th>\n"
                + "</tr>\n";

        assertEquals(desired, testFormatter.getHeader());
    }

    /**
     * Test whether getFooter() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetFooter() throws Exception {
        String desired = "</table>\n</body>\n</html>";

        assertEquals(desired, testFormatter.getFooter());
    }
}