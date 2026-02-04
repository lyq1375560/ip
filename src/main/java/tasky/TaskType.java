<<<<<<< HEAD:src/main/java/TaskType.java
<<<<<<< HEAD
/**
 * Represents the type of a task managed by Tasky.
 * Each type corresponds to a specific task category.
 */
=======
>>>>>>> 533a17a (Level-7: save tasks to disk)
=======
package tasky;

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 3e560c7 (A-Packages: move classes into tasky package):src/main/java/tasky/TaskType.java
enum TaskType {
=======
public enum TaskType {
>>>>>>> af965df (Add Gradle build support)
=======
/**
 * Represents the supported types of tasks.
 */
public enum TaskType {

    /** Todo task without date or time. */
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
    TODO("[T]"),

    /** Deadline task with a due date. */
    DEADLINE("[D]"),

    /** Event task with a start and end time. */
    EVENT("[E]");

    /** Symbol used to represent the task type in output. */
    private final String symbol;

    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol representing this task type.
     *
     * @return Task type symbol
     */
    public String getSymbol() {
        return symbol;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 533a17a (Level-7: save tasks to disk)
