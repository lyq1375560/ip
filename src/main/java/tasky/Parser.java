package tasky;

import java.util.ArrayList;

/**
 * Parses user input commands and executes the corresponding actions.
 * <p>
 * This class is responsible for interpreting the raw user input,
 * validating command formats, and delegating task operations to
 * {@link TaskList}, {@link Ui}, and {@link Storage}.
 */
public class Parser {
    
    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param input The full command entered by the user
     * @param tasks The task list to operate on
     * @param ui The UI used to display messages
     * @param storage The storage used to persist task data
     * @throws TaskyException If the input is invalid or an error occurs
     */
    public static void parseAndExecute(
            String input,
            TaskList tasks,
            Ui ui,
            Storage storage
    ) throws TaskyException {

        assert input != null : "User input should not be null";
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        // AI-Assisted improvement: better Parser structure
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {

        case "bye":
            handleBye(ui);
            return;

        case "list":
            handleList(tasks, ui);
            return;

        case "mark":
            handleMark(input, tasks, ui, storage);
            return;

        case "unmark":
            handleUnmark(input, tasks, ui, storage);
            return;

        case "delete":
            handleDelete(input, tasks, ui, storage);
            return;

        case "todo":
            handleTodo(input, tasks, ui, storage);
            return;

        case "deadline":
            handleDeadline(input, tasks, ui, storage);
            return;

        case "event":
            handleEvent(input, tasks, ui, storage);
            return;

        case "find":
            handleFind(input, tasks, ui);
            return;

        case "help":
            handleHelp(ui);
            return;

        default:
            throw new TaskyException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses and validates a task index provided by the user.
     *
     * @param s The raw index string entered by the user
     * @param size The current number of tasks
     * @return Zero-based index of the task
     * @throws TaskyException If the index is invalid or out of range
     */
    private static int parseIndex(String s, int size) throws TaskyException {
        try {
            int index = Integer.parseInt(s) - 1;
            if (index < 0 || index >= size) {
                throw new TaskyException("That task number does not exist.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TaskyException("Please enter a valid task number.");
        }
    }

    /**
     * Displays a confirmation message after a task is added.
     *
     * @param ui The UI used to display messages
     * @param task The task that was added
     * @param count The total number of tasks after addition
     */
    private static void printAdd(Ui ui, Task task, int count) {
        ui.showMessage(
                " Got it. I've added this task:",
                "   " + task,
                " Now you have " + count + " tasks in the list."
        );
    }

    /**
     * Handles the bye command.
     */
    private static void handleBye(Ui ui) {
        ui.showMessage(" Bye. Hope to see you again soon!");
    }

    /**
     * Handles the list command.
     */

    private static void handleList(TaskList tasks, Ui ui) {
        // AI-Assisted improvement: Empty list handling
        if (tasks.size() == 0) {
            ui.showMessage(" Your task list is empty.");
            return;
        }
        ui.showMessage(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Handles the mark command.
     */
    private static void handleMark(
            String input,
            TaskList tasks,
            Ui ui,
            Storage storage
    ) throws TaskyException {
        int index = parseIndex(input.substring(5), tasks.size());
        tasks.get(index).markDone();
        saveTasks(storage, tasks);
        ui.showMessage(
                " Nice! I've marked this task as done:",
                "   " + tasks.get(index)
        );
    }

    /**
     * Handles the unmark command.
     */
    private static void handleUnmark(
            String input,
            TaskList tasks,
            Ui ui,
            Storage storage
    ) throws TaskyException {
        int index = parseIndex(input.substring(7), tasks.size());
        tasks.get(index).unmarkDone();
        saveTasks(storage, tasks);
        ui.showMessage(
                " OK, I've marked this task as not done yet:",
                "   " + tasks.get(index)
        );
    }

    /**
     * Handles the delete command.
     */
    private static void handleDelete(
            String input,
            TaskList tasks,
            Ui ui,
            Storage storage
    ) throws TaskyException {
        int index = parseIndex(input.substring(7), tasks.size());
        Task removed = tasks.remove(index);
        saveTasks(storage, tasks);
        ui.showMessage(
                " Noted. I've removed this task:",
                "   " + removed,
                " Now you have " + tasks.size() + " tasks in the list."
        );
    }

    /**
     * Handles the todo command.
     */
    private static void handleTodo(
            String input,
            TaskList tasks,
            Ui ui,
            Storage storage
    ) throws TaskyException {
        if (input.equals("todo")) {
            throw new TaskyException("The description of a todo cannot be empty.");
        }

        Task task = new Todo(input.substring(5));
        tasks.add(task);
        saveTasks(storage, tasks);
        printAdd(ui, task, tasks.size());
    }

    /**
     * Handles the deadline command.
     */
    private static void handleDeadline(
            String input,
            TaskList tasks,
            Ui ui,
            Storage storage
    ) throws TaskyException {
        if (!input.contains(" /by ")) {
            throw new TaskyException("A deadline must have a /by date.");
        }

        String[] parts = input.substring(9).split(" /by ");
        Task task = new Deadline(parts[0], parts[1]);
        tasks.add(task);
        saveTasks(storage, tasks);
        printAdd(ui, task, tasks.size());
    }

    /**
     * Handles the event command.
     */
    private static void handleEvent(
            String input,
            TaskList tasks,
            Ui ui,
            Storage storage
    ) throws TaskyException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new TaskyException("An event must have /from and /to dates.");
        }

        String[] parts = input.substring(6).split(" /from ");
        String[] times = parts[1].split(" /to ");
        Task task = new Event(parts[0], times[0], times[1]);
        tasks.add(task);
        saveTasks(storage, tasks);
        printAdd(ui, task, tasks.size());
    }

    /**
     * Handles the find command.
     */
    private static void handleFind(
            String input,
            TaskList tasks,
            Ui ui
    ) throws TaskyException {
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new TaskyException("Please provide a keyword to search for.");
        }

        ArrayList<Task> matches = tasks.findByKeyword(keyword);
        ui.showMessage(" Here are the matching tasks in your list:");

        for (int i = 0; i < matches.size(); i++) {
            ui.showMessage(" " + (i + 1) + "." + matches.get(i));
        }
    }

    /**
     * Displays help information about available commands.
     *
     * @param ui User interface used to display messages
     */
    private static void handleHelp(Ui ui) {

        ui.showMessage(
                " Here are the commands you can use:",
                "",
                " list",
                "   Show all tasks",
                "",
                " todo <description>",
                "   Add a todo task",
                "",
                " deadline <description> /by <yyyy-mm-dd>",
                "   Add a deadline task",
                "",
                " event <description> /from <time> /to <time>",
                "   Add an event task",
                "",
                " mark <task number>",
                "   Mark a task as done",
                "",
                " unmark <task number>",
                "   Mark a task as not done",
                "",
                " delete <task number>",
                "   Delete a task",
                "",
                " find <keyword>",
                "   Find tasks containing the keyword",
                "",
                " bye",
                "   Exit Tasky"
        );
    }
    
    /**
     * Saves the task list to storage.
     */
    private static void saveTasks(Storage storage, TaskList tasks)
            throws TaskyException {
        storage.save(tasks.getAll());
    }
}
