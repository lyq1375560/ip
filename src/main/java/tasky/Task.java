<<<<<<< HEAD:src/main/java/Task.java
/**
 * Represents a task managed by the Tasky chatbot.
 * Each task has a description, completion status, and type.
 */
=======
package tasky;

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 3e560c7 (A-Packages: move classes into tasky package):src/main/java/tasky/Task.java
abstract class Task {
=======
public abstract class Task {

>>>>>>> af965df (Add Gradle build support)
=======
/**
 * Represents a generic task in the application.
 * <p>
 * A task contains a description, completion status, and a task type.
 * Subclasses define specific task behaviors such as deadlines or events.
 */
public abstract class Task {

    /** Description of the task. */
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
    protected String description;

    /** Indicates whether the task has been completed. */
    protected boolean isDone;

    /** Type of the task. */
    protected TaskType type;

    /**
<<<<<<< HEAD
     * Constructs a task with the given description.
     *
     * @param description Description of the task.
=======
     * Creates a task with the given description.
     *
     * @param description Description of the task
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    /**
     * Marks this task as completed.
     */
=======
>>>>>>> af965df (Add Gradle build support)
=======
    /**
     * Marks this task as completed.
     */
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
    public void markDone() {
        isDone = true;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
    /**
     * Marks this task as not completed.
     */
    public void unmarkDone() {
        isDone = false;
    }
=======
    public void markDone() { isDone = true; }
    public void unmarkDone() { isDone = false; }
>>>>>>> 533a17a (Level-7: save tasks to disk)
=======
    public void unmarkDone() {
        isDone = false;
    }
>>>>>>> af965df (Add Gradle build support)

    /**
     * Returns the status icon representing completion state.
     *
     * @return "[X]" if completed, "[ ]" otherwise
     */
    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

<<<<<<< HEAD
<<<<<<< HEAD
    /**
     * Returns a string representation of this task for display.
     *
     * @return Formatted task string.
     */
=======
=======
    /**
     * Converts this task into a string format suitable for file storage.
     *
     * @return File-formatted string representation of this task
     */
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
    public String toFileString() {
        return type.name() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

<<<<<<< HEAD
>>>>>>> 533a17a (Level-7: save tasks to disk)
=======
    /**
     * Returns a human-readable string representation of this task.
     *
     * @return String representation of the task
     */
>>>>>>> 640c0ac (Add JavaDoc comments to core classes)
    @Override
    public String toString() {
        return type.getSymbol() + getStatusIcon() + " " + description;
    }
}

