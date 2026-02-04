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

    private final LocalDate by;

    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

<<<<<<< HEAD
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
>>>>>>> 8cec3f5 (Level-8: support dates for deadlines)
=======
    public Deadline(String description, String by) {
>>>>>>> af965df (Add Gradle build support)
        super(description);
        this.by = LocalDate.parse(by, INPUT);
        this.type = TaskType.DEADLINE;
    }


    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(OUTPUT) + ")";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by;
    }
}
