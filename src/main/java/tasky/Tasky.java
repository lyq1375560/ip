package tasky;

import java.util.ArrayList;

public class Tasky {

    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = "data/tasky.txt";

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public Tasky() {
        ui = new Ui();
        storage = new Storage(DATA_DIR, DATA_FILE);

        ArrayList<Task> loaded;
        try {
            loaded = new ArrayList<>();
            storage.load(loaded);
        } catch (TaskyException e) {
            ui.showLoadingError(e.getMessage());
            loaded = new ArrayList<>();
        }
        tasks = new TaskList(loaded);
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            try {
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
    }
}
