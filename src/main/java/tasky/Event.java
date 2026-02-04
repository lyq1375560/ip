<<<<<<< HEAD:src/main/java/Event.java
/**
 * Represents a task that occurs within a specific time period.
 */
=======
package tasky;

<<<<<<< HEAD
>>>>>>> 3e560c7 (A-Packages: move classes into tasky package):src/main/java/tasky/Event.java
class Event extends Task {
    private String from;
    private String to;
=======
public class Event extends Task {

    private final String from;
    private final String to;
>>>>>>> af965df (Add Gradle build support)

    /**
     * Constructs an event task.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = TaskType.EVENT;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + from + " | " + to;
    }
}
