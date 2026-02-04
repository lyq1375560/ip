package tasky;

/**
 * Represents a task that occurs during a specific time period.
 */
public class Event extends Task {

    /** Start time or date of the event. */
    private final String from;

    /** End time or date of the event. */
    private final String to;

    /**
     * Creates an event task with a start and end time.
     *
     * @param description Description of the event
     * @param from Start time of the event
     * @param to End time of the event
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
