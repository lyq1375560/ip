import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Tasky {

    private static final String LINE =
            "____________________________________________________________";
    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = "data/tasky.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage(DATA_DIR, DATA_FILE);

        // Load saved tasks
        try {
            storage.load(tasks);
        } catch (TaskyException e) {
            System.out.println("⚠ " + e.getMessage());
        }

        System.out.println(LINE);
        System.out.println(" Hello! I'm Tasky");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String input = scanner.nextLine();

            try {
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

                    System.out.println(LINE);
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(index));
                    System.out.println(LINE);
                    continue;
                }

                if (input.startsWith("delete ")) {
                    int index = parseIndex(input.substring(7), tasks.size());
                    Task removed = tasks.remove(index);
                    storage.save(tasks);

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
                    storage.save(tasks);
                    printAdd(task, tasks.size());
                    continue;
                }

                // ===== Level-8 FIXED: deadline with LocalDate =====
                if (input.startsWith("deadline")) {
                    if (!input.contains(" /by ")) {
                        throw new TaskyException("A deadline must have a /by date.");
                    }

                    String[] parts = input.substring(9).split(" /by ");

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
                }

                if (input.startsWith("event")) {
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new TaskyException("An event must have /from and /to times.");
                    }

                    String[] parts = input.substring(6).split(" /from ");
                    String[] times = parts[1].split(" /to ");
                    Task task = new Event(parts[0], times[0], times[1]);
                    tasks.add(task);
                    storage.save(tasks);
                    printAdd(task, tasks.size());
                    continue;
                }

                throw new TaskyException("I'm sorry, but I don't know what that means.");

            } catch (TaskyException e) {
                System.out.println(LINE);
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println(LINE);
            }
        }

        scanner.close();
    }

    private static int parseIndex(String s, int size) throws TaskyException {
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

    private static void printAdd(Task task, int count) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println(LINE);
    }
}



