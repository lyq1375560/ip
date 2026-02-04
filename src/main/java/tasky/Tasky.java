package tasky;

/**
 * The main entry point of the Tasky application.
 * <p>
 * Tasky is a command-line chatbot that helps users manage tasks.
 * It coordinates interactions between the user interface, task list,
 * storage, and command parser.
 */
public class Tasky {

    /** Handles loading and saving tasks to persistent storage. */
    private final Storage storage;

    /** Stores and manages the list of tasks. */
    private final TaskList tasks;

    /** Handles all user interactions (input and output). */
    private final Ui ui;

    /**
     * Constructs a Tasky application with the given data directory and file.
     * <p>
     * This constructor initializes the UI, storage, and task list.
     * If loading tasks from storage fails, an empty task list is used instead.
     *
     * @param dataDir  Directory where task data is stored
     * @param dataFile File path used to persist task data
     */
    public Tasky(String dataDir, String dataFile) {
        ui = new Ui();
        storage = new Storage(dataDir, dataFile);

        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (TaskyException e) {
            // Loading failed; notify user and start with an empty task list
            ui.showError(e.getMessage());
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    /**
     * Runs the main execution loop of the Tasky application.
     * <p>
     * This method repeatedly reads user commands, parses and executes them,
     * until the user issues the exit command.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();

                Parser.parseAndExecute(input, tasks, ui, storage);

                // Exit condition is handled after command execution
                isExit = input.equals("bye");
            } catch (TaskyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Launches the Tasky application.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Tasky("data", "data/tasky.txt").run();
    }
}
