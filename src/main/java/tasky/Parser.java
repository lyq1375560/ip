package tasky;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static void parse(String input, TaskList tasks, Ui ui, Storage storage)
            throws TaskyException, ExitException {

        if (input.equals("bye")) {
            throw new ExitException();
        }

        if (input.equals("list")) {
            ui.showLine();
            ui.showMessage(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage(" " + (i + 1) + "." + tasks.get(i));
            }
            ui.showLine();
            return;
        }

        if (input.startsWith("todo")) {
            if (input.equals("todo")) {
                throw new TaskyException("The description of a todo cannot be empty.");
            }
            Task t = new Todo(input.substring(5));
            tasks.add(t);
            storage.save(tasks.getAll());
            printAdd(ui, t, tasks.size());
            return;
        }

        if (input.startsWith("deadline")) {
            if (!input.contains(" /by ")) {
                throw new TaskyException("A deadline must have a /by date.");
            }
            String[] parts = input.substring(9).split(" /by ");
            try {
                LocalDate date = LocalDate.parse(parts[1]);
                Task t = new Deadline(parts[0], date);
                tasks.add(t);
                storage.save(tasks.getAll());
                printAdd(ui, t, tasks.size());
                return;
            } catch (DateTimeParseException e) {
                throw new TaskyException("Please use yyyy-MM-dd format for dates.");
            }
        }

        throw new TaskyException("I'm sorry, but I don't know what that means.");
    }

    private static void printAdd(Ui ui, Task task, int count) {
        ui.showLine();
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + task);
        ui.showMessage(" Now you have " + count + " tasks in the list.");
        ui.showLine();
    }
}