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

public class Deadline extends Task {

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    private LocalDate by;

    public Deadline(String description, LocalDate by) {
>>>>>>> 8cec3f5 (Level-8: support dates for deadlines)
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }


    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return TaskType.DEADLINE + " | "
                + (isDone ? "1" : "0") + " | "
                + description + " | "
                + by;
    }
}
