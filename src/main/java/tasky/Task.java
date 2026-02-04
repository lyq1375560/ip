package tasky;

/**
 * Represents a generic task in the application.
 * <p>
 * A task contains a description, completion status, and a task type.
 * Subclasses define specific task behaviors such as deadlines or events.
 */
public abstract class Task {

    /** Description of the task. */
    protected String description;

    /** Indicates whether the task has been completed. */
    protected boolean isDone;

    /** Type of the task. */
    protected TaskType type;

    /**
     * Creates a task with the given description.
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Returns the status icon representing completion state.
     *
     * @return "[X]" if completed, "[ ]" otherwise
     */
    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Converts this task into a string format suitable for file storage.
     *
     * @return File-formatted string representation of this task
     */
    public String toFileString() {
        return type.name() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a human-readable string representation of this task.
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return type.getSymbol() + getStatusIcon() + " " + description;
    }
}

