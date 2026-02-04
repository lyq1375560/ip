<<<<<<< HEAD:src/main/java/Deadline.java
<<<<<<< HEAD
/**
 * Represents a task that must be completed by a specific deadline.
 */
class Deadline extends Task {
    private String by;

    /**
     * Constructs a deadline task.
     *
     * @param description Description of the task.
     * @param by Deadline information.
     */
    public Deadline(String description, String by) {
=======
=======
package tasky;

>>>>>>> 3e560c7 (A-Packages: move classes into tasky package):src/main/java/tasky/Deadline.java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed before a specific date.
 */
public class Deadline extends Task {

    /** Deadline date of the task. */
    private final LocalDate by;

    /** Date format used for parsing input dates. */
    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Date format used for displaying dates to the user. */
    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

<<<<<<< HEAD
<<<<<<< HEAD
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
>>>>>>> 8cec3f5 (Level-8: support dates for deadlines)
=======
=======
    /**
     * Creates a deadline task with the given description and date.
     *
     * @param description Description of the task
     * @param by Deadline date in yyyy-MM-dd format
     */
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
    public Deadline(String description, String by) {
>>>>>>> af965df (Add Gradle build support)
        super(description);
        this.by = LocalDate.parse(by, INPUT);
        this.type = TaskType.DEADLINE;
    }

<<<<<<< HEAD

=======
    /**
     * Returns a human-readable string representation of this deadline task.
     *
     * @return String representation including the deadline date
     */
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(OUTPUT) + ")";
    }

    /**
     * Converts this deadline task into a string format suitable for file storage.
     *
     * @return File-formatted string representation of this deadline task
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by;
    }
}
