package tasky;

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

        // Exit command
        if (input.equals("bye")) {
            ui.showMessage(" Bye. Hope to see you again soon!");
            return;
        }

        // List command
        if (input.equals("list")) {
            ui.showMessage(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage(" " + (i + 1) + "." + tasks.get(i));
            }
            return;
        }

        // Mark command
        if (input.startsWith("mark ")) {
            int index = parseIndex(input.substring(5), tasks.size());
            tasks.get(index).markDone();
            storage.save(tasks.getAll());

            ui.showMessage(" Nice! I've marked this task as done:");
            ui.showMessage("   " + tasks.get(index));
            return;
        }

        // Unmark command
        if (input.startsWith("unmark ")) {
            int index = parseIndex(input.substring(7), tasks.size());
            tasks.get(index).unmarkDone();
            storage.save(tasks.getAll());

            ui.showMessage(" OK, I've marked this task as not done yet:");
            ui.showMessage("   " + tasks.get(index));
            return;
        }

        // Delete command
        if (input.startsWith("delete ")) {
            int index = parseIndex(input.substring(7), tasks.size());
            Task removed = tasks.remove(index);
            storage.save(tasks.getAll());

            ui.showMessage(" Noted. I've removed this task:");
            ui.showMessage("   " + removed);
            ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
            return;
        }

        // Todo command
        if (input.startsWith("todo")) {
            if (input.equals("todo")) {
                throw new TaskyException("The description of a todo cannot be empty.");
            }
            Task task = new Todo(input.substring(5));
            tasks.add(task);
            storage.save(tasks.getAll());
            printAdd(ui, task, tasks.size());
            return;
        }

        // Deadline command
        if (input.startsWith("deadline")) {
            if (!input.contains(" /by ")) {
                throw new TaskyException("A deadline must have a /by date.");
            }
            String[] parts = input.substring(9).split(" /by ");
            Task task = new Deadline(parts[0], parts[1]);
            tasks.add(task);
            storage.save(tasks.getAll());
            printAdd(ui, task, tasks.size());
            return;
        }

        // Event command
        if (input.startsWith("event")) {
            if (!input.contains(" /from ") || !input.contains(" /to ")) {
                throw new TaskyException("An event must have /from and /to dates.");
            }
            String[] parts = input.substring(6).split(" /from ");
            String[] times = parts[1].split(" /to ");
            Task task = new Event(parts[0], times[0], times[1]);
            tasks.add(task);
            storage.save(tasks.getAll());
            printAdd(ui, task, tasks.size());
            return;
        }

        // Unknown command
        throw new TaskyException("I'm sorry, but I don't know what that means.");
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
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + task);
        ui.showMessage(" Now you have " + count + " tasks in the list.");
    }
}
