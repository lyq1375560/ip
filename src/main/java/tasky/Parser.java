package tasky;

public class Parser {

    public static void parseAndExecute(
            String input,
            TaskList tasks,
            Ui ui,
            Storage storage
    ) throws TaskyException {

        if (input.equals("bye")) {
            ui.showMessage(" Bye. Hope to see you again soon!");
            return;
        }

        if (input.equals("list")) {
            ui.showMessage(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage(" " + (i + 1) + "." + tasks.get(i));
            }
            return;
        }

        if (input.startsWith("mark ")) {
            int index = parseIndex(input.substring(5), tasks.size());
            tasks.get(index).markDone();
            storage.save(tasks.getAll());

            ui.showMessage(" Nice! I've marked this task as done:");
            ui.showMessage("   " + tasks.get(index));
            return;
        }

        if (input.startsWith("unmark ")) {
            int index = parseIndex(input.substring(7), tasks.size());
            tasks.get(index).unmarkDone();
            storage.save(tasks.getAll());

            ui.showMessage(" OK, I've marked this task as not done yet:");
            ui.showMessage("   " + tasks.get(index));
            return;
        }

        if (input.startsWith("delete ")) {
            int index = parseIndex(input.substring(7), tasks.size());
            Task removed = tasks.remove(index);
            storage.save(tasks.getAll());

            ui.showMessage(" Noted. I've removed this task:");
            ui.showMessage("   " + removed);
            ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
            return;
        }

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

        throw new TaskyException("I'm sorry, but I don't know what that means.");
    }

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

    private static void printAdd(Ui ui, Task task, int count) {
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + task);
        ui.showMessage(" Now you have " + count + " tasks in the list.");
    }
}
