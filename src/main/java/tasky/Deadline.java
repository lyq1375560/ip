package tasky;

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

    /**
     * Creates a deadline task with the given description and date.
     *
     * @param description Description of the task
     * @param by Deadline date in yyyy-MM-dd format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, INPUT);
        this.type = TaskType.DEADLINE;
    }

    /**
     * Returns a human-readable string representation of this deadline task.
     *
     * @return String representation including the deadline date
     */
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
