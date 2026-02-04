package tasky;

public class Tasky {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Tasky(String dataDir, String dataFile) {
        ui = new Ui();
        storage = new Storage(dataDir, dataFile);

        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (TaskyException e) {
            ui.showError(e.getMessage());
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();

                Parser.parseAndExecute(input, tasks, ui, storage);

                isExit = input.equals("bye");
            } catch (TaskyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Tasky("data", "data/tasky.txt").run();
    }
}
