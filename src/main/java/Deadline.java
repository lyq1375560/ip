/**
 * Represents a task that must be completed by a specific deadline.
 */
class Deadline extends Task {
    protected String by;

    /**
     * Constructs a deadline task.
     *
     * @param description Description of the task.
     * @param by Deadline information.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }


    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}