<<<<<<< HEAD:src/main/java/Todo.java
/**
 * Represents a to-do task without any associated date or time.
 */
=======
package tasky;

<<<<<<< HEAD
>>>>>>> 3e560c7 (A-Packages: move classes into tasky package):src/main/java/tasky/Todo.java
class Todo extends Task {
    /**
     * Constructs a to-do task with the given description.
     *
     * @param description Description of the task.
     */
=======
public class Todo extends Task {

>>>>>>> af965df (Add Gradle build support)
    public Todo(String description) {
        super(description);
        this.type = TaskType.TODO;
    }
}
