package application.logger;

/**
 * The Log Class that stores data from one logrule, contains the message,
 * the time of writing and the level of importance.
 * @author Thomas Oomens.
 */
@SuppressWarnings({
        "checkstyle:magicnumber",
        "checkstyle:visibilitymodifier",
        "checkstyle:linelength"
})
public class Log {
    protected String tType;
    protected String tMessage;
    protected String tColor;
    protected int tImportant;
    protected long tTime;

    /**
     * The constructor method that stores the given message and importancy and saves the current time in milliseconds.
     * @param message The message that is to be saved/logged.
     * @param important The level of importancy, 0 = SEVERE, 1 = WARNING, 2 = INFO, 3 = CONFIG, 4 = FINE
     * 5 = FINER, 6 = FINEST.
     */
    public Log(String message, int important) {
        tMessage = message;
        tImportant = important;
        tTime = System.currentTimeMillis();
    }

    /**
     * Returns a textual version of the level of importance.
     * @return  The level of importancy, expressed by one word.
     */
    public String getType() {
        switch (tImportant) {
            case 0:
                return "SEVERE";
            case 1:
                return "WARNING";
            case 2:
                return "INFO";
            case 3:
                return "CONFIG";
            case 4:
                return "FINE";
            case 5:
                return "FINER";
            case 6:
                return "FINEST";
            default: break;
        }
        return "INFO";
    }

    /**
     * A getter for the message.
     * @return  Return tMessage.
     */
    public String getMessage() {
        return tMessage;
    }

    /**
     * A getter for the color,
     * will return a string containing the color that belongs to the level of importance.
     * @return  A textual color representation of the level of importance.
     */
    public String getColor() {
        switch (tImportant) {
            case 0:
                return "red";
            case 1:
                return "orange";
            case 2:
                return "blue";
            case 3:
                return "purple";
            case 4:
                return "yellow";
            case 5:
                return "light-green";
            case 6:
                return "green";
            default: break;
        }
        return "blue";
    }

    /**
     * A getter for the time that the log was created.
     * @return  The time in milliseconds of the time of creation of this log.
     */
    public long getTime() {
        return tTime;
    }
}
