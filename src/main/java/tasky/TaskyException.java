<<<<<<< HEAD:src/main/java/TaskyException.java
/**
 * Represents exceptions specific to the Tasky chatbot.
 */
=======
package tasky;

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 3e560c7 (A-Packages: move classes into tasky package):src/main/java/tasky/TaskyException.java
class TaskyException extends Exception {
=======
public class TaskyException extends Exception {

>>>>>>> af965df (Add Gradle build support)
=======
/**
 * Represents exceptions specific to the Tasky application.
 */
public class TaskyException extends Exception {

    /**
     * Creates a new TaskyException with the given message.
     *
     * @param message Error description
     */
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
    public TaskyException(String message) {
        super(message);
    }
}
