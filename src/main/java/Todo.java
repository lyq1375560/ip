/**
 * Represents a to-do task without any associated date or time.
 */
class Todo extends Task {
    /**
     * Constructs a to-do task with the given description.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
        this.type = TaskType.TODO;
    }
}