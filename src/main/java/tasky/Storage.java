package tasky;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Handles loading tasks from and saving tasks to the hard disk.
 * <p>
 * This class is responsible for persistent storage of tasks
 * using a plain text file in a human-readable format.
 */
public class Storage {

    /** Directory used to store the data file. */
    private final String dataDir;

    /** File path used to persist task data. */
    private final String dataFile;

    /**
     * Creates a storage handler with the specified directory and file path.
     *
     * @param dataDir Directory used to store task data
     * @param dataFile File path of the task data file
     */
    public Storage(String dataDir, String dataFile) {
        this.dataDir = dataDir;
        this.dataFile = dataFile;
    }

    /**
     * Loads tasks from the data file.
     * <p>
     * If the data directory or file does not exist, they will be created.
     *
     * @return A list of tasks loaded from the file
     * @throws TaskyException If loading fails due to I/O errors or corrupted data
     */
    public ArrayList<Task> load() throws TaskyException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Files.createDirectories(Paths.get(dataDir));
            File file = new File(dataFile);

            if (!file.exists()) {
                file.createNewFile();
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                tasks.add(parseTask(line));
            }
            reader.close();
            return tasks;

        } catch (IOException e) {
            throw new TaskyException("Failed to load saved tasks.");
        }
    }

    /**
     * Saves the given list of tasks to the data file.
     *
     * @param tasks The list of tasks to be saved
     * @throws TaskyException If saving fails due to I/O errors
     */
    public void save(ArrayList<Task> tasks) throws TaskyException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new TaskyException("Failed to save tasks.");
        }
    }

    /**
     * Parses a single line from the data file into a {@link Task} object.
     *
     * @param line A line read from the data file
     * @return The task represented by the line
     * @throws TaskyException If the line format is invalid or corrupted
     */
    private Task parseTask(String line) throws TaskyException {
        try {
            String[] parts = line.split(" \\| ");
            TaskType type = TaskType.valueOf(parts[0]);
            boolean done = parts[1].equals("1");
            String description = parts[2];

            Task task;
            if (type == TaskType.TODO) {
                task = new Todo(description);
            } else if (type == TaskType.DEADLINE) {
                task = new Deadline(description, parts[3]);
            } else {
                task = new Event(description, parts[3], parts[4]);
            }

            if (done) {
                task.markDone();
            }
            return task;

        } catch (Exception e) {
            throw new TaskyException("Corrupted data file.");
        }
    }
}
