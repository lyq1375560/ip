package tasky;

/**
 * Represents a task without any associated date or time.
 */
public class Todo extends Task {

    /**
     * Creates a todo task with the given description.
     *
     * @param description Description of the todo task
     */
    public Todo(String description) {
        super(description);
        this.type = TaskType.TODO;
    }
}
