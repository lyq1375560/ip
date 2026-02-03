import java.util.ArrayList;

/**
 * Tasky is a command-line chatbot that helps users manage tasks.
 * It supports adding, listing, marking, deleting, finding, and
 * persisting tasks across program runs.
 */
public class Tasky {

    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = "data/tasky.txt";

<<<<<<< HEAD
    /**
     * Runs the Tasky chatbot application.
     * Reads user commands from standard input and executes them
     * until the user exits the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
<<<<<<< HEAD

        // Level-7: load tasks from disk
        ArrayList<Task> tasks;
        try {
            tasks = Storage.load();
        } catch (Exception e) {
            tasks = new ArrayList<>();
=======
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage(DATA_DIR, DATA_FILE);
=======
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
>>>>>>> 0f2887f (A-MoreOOP: extract Ui, Parser, and TaskList classes)

    public Tasky() {
        ui = new Ui();
        storage = new Storage(DATA_DIR, DATA_FILE);

        ArrayList<Task> loaded;
        try {
            loaded = new ArrayList<>();
            storage.load(loaded);
        } catch (TaskyException e) {
<<<<<<< HEAD
            System.out.println("⚠ " + e.getMessage());
>>>>>>> 533a17a (Level-7: save tasks to disk)
=======
            ui.showLoadingError(e.getMessage());
            loaded = new ArrayList<>();
>>>>>>> 0f2887f (A-MoreOOP: extract Ui, Parser, and TaskList classes)
        }
        tasks = new TaskList(loaded);
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            try {
<<<<<<< HEAD
                if (input.equals("bye")) {
                    System.out.println(LINE);
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    break;
                }

                if (input.equals("list")) {
                    System.out.println(LINE);
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println(LINE);
                    continue;
                }

                if (input.startsWith("mark ")) {
                    int index = parseIndex(input.substring(5), tasks.size());
                    tasks.get(index).markDone();
                    storage.save(tasks);

                    // save after modification
                    Storage.save(tasks);

                    System.out.println(LINE);
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(index));
                    System.out.println(LINE);
                    continue;
                }

                if (input.startsWith("unmark ")) {
                    int index = parseIndex(input.substring(7), tasks.size());
                    tasks.get(index).unmarkDone();
                    storage.save(tasks);

                    // save after modification
                    Storage.save(tasks);

                    System.out.println(LINE);
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(index));
                    System.out.println(LINE);
                    continue;
                }

<<<<<<< HEAD
                // delete
=======
>>>>>>> 533a17a (Level-7: save tasks to disk)
                if (input.startsWith("delete ")) {
                    int index = parseIndex(input.substring(7), tasks.size());
                    Task removed = tasks.remove(index);
                    storage.save(tasks);

                    // save after modification
                    Storage.save(tasks);

                    System.out.println(LINE);
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(LINE);
                    continue;
                }

                if (input.startsWith("todo")) {
                    if (input.equals("todo")) {
                        throw new TaskyException("The description of a todo cannot be empty.");
                    }

                    Task task = new Todo(input.substring(5));
                    tasks.add(task);
<<<<<<< HEAD

                    // save after modification
                    Storage.save(tasks);

                    printAddMessage(task, tasks.size());
=======
                    storage.save(tasks);
                    printAdd(task, tasks.size());
>>>>>>> 533a17a (Level-7: save tasks to disk)
                    continue;
                }

                // ===== Level-8 FIXED: deadline with LocalDate =====
                if (input.startsWith("deadline")) {
                    if (!input.contains(" /by ")) {
                        throw new TaskyException("A deadline must have a /by date.");
                    }

                    String[] parts = input.substring(9).split(" /by ");
<<<<<<< HEAD
                    Task task = new Deadline(parts[0], parts[1]);
                    tasks.add(task);
<<<<<<< HEAD

                    // save after modification
                    Storage.save(tasks);

                    printAddMessage(task, tasks.size());
=======
                    storage.save(tasks);
                    printAdd(task, tasks.size());
>>>>>>> 533a17a (Level-7: save tasks to disk)
                    continue;
=======

                    try {
                        LocalDate byDate = LocalDate.parse(parts[1]);
                        Task task = new Deadline(parts[0], byDate);
                        tasks.add(task);
                        storage.save(tasks);
                        printAdd(task, tasks.size());
                        continue;

                    } catch (DateTimeParseException e) {
                        throw new TaskyException(
                                "Please use yyyy-MM-dd format for dates."
                        );
                    }
>>>>>>> 8cec3f5 (Level-8: support dates for deadlines)
                }

                if (input.startsWith("event")) {
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new TaskyException("An event must have /from and /to times.");
                    }

                    String[] parts = input.substring(6).split(" /from ");
                    String[] times = parts[1].split(" /to ");
                    Task task = new Event(parts[0], times[0], times[1]);
                    tasks.add(task);
<<<<<<< HEAD

                    // save after modification
                    Storage.save(tasks);

                    printAddMessage(task, tasks.size());
                    continue;
                }

                // find
                if (input.startsWith("find ")) {
                    String keyword = input.substring(5);

                    if (keyword.isBlank()) {
                        throw new TaskyException("Please provide a keyword to search for.");
                    }

                    printFindResults(tasks, keyword);
                    continue;
                }

=======
                    storage.save(tasks);
                    printAdd(task, tasks.size());
                    continue;
                }

>>>>>>> 533a17a (Level-7: save tasks to disk)
                throw new TaskyException("I'm sorry, but I don't know what that means.");

            } catch (Exception e) {
                System.out.println(LINE);
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println(LINE);
            }
        }

        scanner.close();
    }

<<<<<<< HEAD
/**
 * Helper methods for command parsing and output formatting.
 */

    /**
     * Parses a user-provided task index and validates its range.
     *
     * @param input The raw index string entered by the user.
     * @param size The current number of tasks.
     * @return Zero-based index of the task.
     * @throws TaskyException If the index is invalid or out of range.
     */
    private static int parseIndex(String input, int size) throws TaskyException {
=======
    private static int parseIndex(String s, int size) throws TaskyException {
>>>>>>> 533a17a (Level-7: save tasks to disk)
        try {
            int i = Integer.parseInt(s) - 1;
            if (i < 0 || i >= size) {
                throw new TaskyException("That task number does not exist.");
            }
            return i;
        } catch (NumberFormatException e) {
            throw new TaskyException("Please enter a valid task number.");
        }
    }

<<<<<<< HEAD
    /**
     * Prints a confirmation message after a task is added.
     *
     * @param task The task that was added.
     * @param count The total number of tasks after addition.
     */
    private static void printAddMessage(Task task, int count) {
=======
    private static void printAdd(Task task, int count) {
>>>>>>> 533a17a (Level-7: save tasks to disk)
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println(LINE);
=======
                String input = ui.readCommand();
                Parser.parse(input, tasks, ui, storage);
            } catch (ExitException e) {
                ui.showGoodbye();
                break;
            } catch (TaskyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Tasky().run();
>>>>>>> 0f2887f (A-MoreOOP: extract Ui, Parser, and TaskList classes)
    }

    /**
     * Prints all tasks whose descriptions contain the given keyword.
     *
     * @param tasks The list of tasks to search.
     * @param keyword The keyword to match against task descriptions.
     */
    private static void printFindResults(ArrayList<Task> tasks, String keyword) {
        System.out.println(LINE);
        System.out.println(" Here are the matching tasks in your list:");

        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(keyword)) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
                count++;
            }
        }

        if (count == 0) {
            System.out.println(" No matching tasks found.");
        }

        System.out.println(LINE);
    }
}
