package tasky;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDate by;

    private static final DateTimeFormatter INPUT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String by) {
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
