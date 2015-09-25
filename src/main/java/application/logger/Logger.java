package application.logger;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Thomas on 18-09-15.
 */

/**
 * Class that provides static methods to create a HTML LogFile
 */
public class Logger {
    protected ArrayList<Log> tLogs = new ArrayList<Log>();
    protected HtmlFormatter tFormatter = new HtmlFormatter();
    protected PrintWriter tWriter;

    /**
     * Starts the logging, opens the logging file and sets the header HTML.
     */
    public void startLogging() {
        try {
            tWriter = new PrintWriter("log.html", "UTF-8");
            tWriter.println(tFormatter.getHeader());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the logging, adds the footer data and then closes the file
     */
    public void stopLogging() {
        tWriter.println(tFormatter.getFooter());
        tWriter.close();
    }

    /**
     * Parse the log data to a HTML based string and write it to the logfile
     * @param log   The log file that is to be parsed and added to the logfile
     * @throws IOException
     */
    protected void writeLog(Log log) {
        tWriter.println(tFormatter.format(log));
    }

    /**
     * Create a new log based on the message and importancy and store it in the arraylist
     * After that try to write the log to the logfile
     * @param message   The message that is to be logged to the logfile
     * @param important     The level of importancy, 0 = SEVERE, 1 = WARNING, 2 = INFO, 3 = CONFIG, 4 = FINE
     *                      5 = FINER, 6 = FINEST
     */
    public void setLog(String message, int important) {
        Log log = new Log(message, important);
        tLogs.add(log);
        writeLog(log);
    }
}
