package tasky;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Tasky application.
 * <p>
 * This class encapsulates the internal task collection and provides
 * operations to add, remove, and retrieve tasks.
 */
public class TaskList {

    /** Internal list used to store tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index Zero-based index of the task to remove
     * @return The removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Zero-based index of the task
     * @return The task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Total number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying list of all tasks.
     * <p>
     * This method is mainly used for persistence operations.
     *
     * @return List containing all tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }
}
