<<<<<<< HEAD:src/main/java/Event.java
/**
 * Represents a task that occurs within a specific time period.
 */
=======
package tasky;

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 3e560c7 (A-Packages: move classes into tasky package):src/main/java/tasky/Event.java
class Event extends Task {
    private String from;
    private String to;
=======
=======
/**
 * Represents a task that occurs during a specific time period.
 */
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
public class Event extends Task {

    /** Start time or date of the event. */
    private final String from;

    /** End time or date of the event. */
    private final String to;
>>>>>>> af965df (Add Gradle build support)

    /**
<<<<<<< HEAD
     * Constructs an event task.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
=======
     * Creates an event task with a start and end time.
     *
     * @param description Description of the event
     * @param from Start time of the event
     * @param to End time of the event
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = TaskType.EVENT;
    }

    /**
     * Returns a human-readable string representation of this event task.
     *
     * @return String representation including event duration
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts this event task into a string format suitable for file storage.
     *
     * @return File-formatted string representation of this event task
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + from + " | " + to;
    }
}
