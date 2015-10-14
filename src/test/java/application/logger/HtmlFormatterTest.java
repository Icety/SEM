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
public class HtmlFormatterTest {
    private Log testLog;
    private HtmlFormatter testFormatter;

    @Before
    public void setUp() throws Exception {
        testLog = new Log("Test", 3);
        testFormatter = new HtmlFormatter();
    }

    @Test
    public void testFormat() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        Date resultdate = new Date(testLog.getTime());
        String dateTime = dateFormat.format(resultdate);

        String wanted = "<tr>\n" +
                "\t<th style=\"color: purple\">CONFIG<th>\n" +
                "<td>Test</td>\n" +
                "<td>"+ dateTime +"</td></tr>\n";

        assertEquals(wanted, testFormatter.format(testLog));
    }

    @Test
    public void testParseTime() throws Exception {

    }

    @Test
    public void testGetHeader() throws Exception {

    }

    @Test
    public void testGetFooter() throws Exception {

    }
}