<<<<<<< HEAD:src/main/java/Todo.java
/**
 * Represents a to-do task without any associated date or time.
 */
=======
package tasky;

>>>>>>> 3e560c7 (A-Packages: move classes into tasky package):src/main/java/tasky/Todo.java
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