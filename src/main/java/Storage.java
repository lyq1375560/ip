import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Handles loading and saving Tasky tasks to disk.
 * Tasks are stored in a human-editable text file.
 */
public class Storage {

    private static final String DATA_DIR = "data";
    private static final String FILE_PATH = "data/tasky.txt";

    // Load tasks at startup
    /**
     * Loads tasks from the data file.
     *
     * @return List of tasks loaded from disk.
     * @throws IOException If an I/O error occurs.
     */
    public static ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks; // first run, nothing to load
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            tasks.add(deserialize(line));
        }

        reader.close();
        return tasks;
    }

    // Save tasks whenever they change
    /**
     * Saves all tasks to the data file.
     *
     * @param tasks List of tasks to save.
     * @throws IOException If an I/O error occurs.
     */
    public static void save(ArrayList<Task> tasks) throws IOException {
        createIfMissing();

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Task task : tasks) {
            writer.write(serialize(task));
            writer.newLine();
        }
        writer.close();
    }

    // ---------------- helper methods ----------------

    private static void createIfMissing() throws IOException {
        Files.createDirectories(Paths.get(DATA_DIR));
        if (!Files.exists(Paths.get(FILE_PATH))) {
            Files.createFile(Paths.get(FILE_PATH));
        }
    }

    private static String serialize(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
        }
        if (task instanceof Deadline d) {
            return "D | " + (task.isDone ? "1" : "0")
                    + " | " + d.description + " | " + d.by;
        }
        if (task instanceof Event e) {
            return "E | " + (task.isDone ? "1" : "0")
                    + " | " + e.description + " | " + e.from + " | " + e.to;
        }
        throw new IllegalArgumentException("Unknown task type");
    }

    private static Task deserialize(String line) {
        String[] parts = line.split(" \\| ");

        Task task;
        switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new IllegalArgumentException("Corrupted data file");
        }

        if (parts[1].equals("1")) {
            task.markDone();
        }
        return task;
    }
}
