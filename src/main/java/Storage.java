import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Storage {

    private final String dataDir;
    private final String dataFile;

    public Storage(String dataDir, String dataFile) {
        this.dataDir = dataDir;
        this.dataFile = dataFile;
    }

    public void load(ArrayList<Task> tasks) throws TaskyException {
        try {
            Files.createDirectories(Paths.get(dataDir));

            File file = new File(dataFile);
            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                tasks.add(parseTask(line));
            }

            reader.close();
        } catch (IOException e) {
            throw new TaskyException("Failed to load saved tasks.");
        }
    }

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

            if (done) task.markDone();
            return task;

        } catch (Exception e) {
            throw new TaskyException("Corrupted data file.");
        }
    }
}
