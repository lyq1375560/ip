/**
 * Represents a task managed by the Tasky chatbot.
 * Each task has a description, completion status, and type.
 */
abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a task with the given description.
     *
     * @param description Description of the task.
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

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns a string representation of this task for display.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return type.getSymbol() + getStatusIcon() + " " + description;
    }
}
