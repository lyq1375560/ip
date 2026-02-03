<<<<<<< HEAD:src/main/java/Task.java
/**
 * Represents a task managed by the Tasky chatbot.
 * Each task has a description, completion status, and type.
 */
=======
package tasky;

>>>>>>> 3e560c7 (A-Packages: move classes into tasky package):src/main/java/tasky/Task.java
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

<<<<<<< HEAD
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
=======
    public void markDone() { isDone = true; }
    public void unmarkDone() { isDone = false; }
>>>>>>> 533a17a (Level-7: save tasks to disk)

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

<<<<<<< HEAD
    /**
     * Returns a string representation of this task for display.
     *
     * @return Formatted task string.
     */
=======
    public String toFileString() {
        return type.name() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

>>>>>>> 533a17a (Level-7: save tasks to disk)
    @Override
    public String toString() {
        return type.getSymbol() + getStatusIcon() + " " + description;
    }
}
