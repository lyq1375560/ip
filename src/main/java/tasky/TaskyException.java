package tasky;

/**
 * Represents exceptions specific to the Tasky application.
 */
public class TaskyException extends Exception {

    /**
     * Creates a new TaskyException with the given message.
     *
     * @param message Error description
     */
    public TaskyException(String message) {
        super(message);
    }
}
