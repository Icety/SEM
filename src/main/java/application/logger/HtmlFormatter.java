package application.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class that generates html header and footer data for the logfile and parses logs to html.
 * @author Thomas Oomens.
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class HtmlFormatter {

    /**
     * THe method that format a log to a html representation.
     * @param log The log that is to be formatted.
     * @return A String containing html, representing the given log.
     */
    public String format(Log log) {
        StringBuffer buf = new StringBuffer(1000);
        buf.append("<tr>\n");
        //The color + log type
        buf.append("\t<th style=\"color: " + log.getColor() + "\">");
        buf.append(log.getType());
        buf.append("<th>\n");
        //The message
        buf.append("<td>" + log.getMessage() + "</td>\n");
        //The time
        buf.append("<td>" + this.parseTime(log.getTime()) + "</td>");
        buf.append("</tr>\n");

        return buf.toString();
    }

    /**
     * Parses the given milliseconds time to a good textual representation.
     * @param time  The time given in milliseconds.
     * @return      The time given in dd-MMM-yyyy HH:mm format.
     */
    protected String parseTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        Date resultdate = new Date(time);
        return dateFormat.format(resultdate);
    }

    /**
     * Returns html for the header of the logfile.
     * @return  Html for the header of the logfile.
     */
    public String getHeader() {
        return "<!DOCTYPE html>\n<head>\n<style>\n"
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
    }

    /**
     * Returns html for the footer of the logfile.
     * @return  Html for the footer of the logfile.
     */
    public String getFooter() {
        return "</table>\n</body>\n</html>";
    }

}
