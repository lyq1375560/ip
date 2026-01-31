import java.util.ArrayList;
import java.util.Scanner;

public class Tasky {

    private static final String LINE =
            "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // Greeting
        System.out.println(LINE);
        System.out.println(" Hello! I'm Tasky");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String input = scanner.nextLine();

            try {
                // bye
                if (input.equals("bye")) {
                    System.out.println(LINE);
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    break;
                }

                // list
                if (input.equals("list")) {
                    System.out.println(LINE);
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println(LINE);
                    continue;
                }

                // mark
                if (input.startsWith("mark ")) {
                    int index = parseIndex(input.substring(5), tasks.size());
                    Task task = tasks.get(index);
                    task.markDone();

                    System.out.println(LINE);
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + task);
                    System.out.println(LINE);
                    continue;
                }

                // unmark
                if (input.startsWith("unmark ")) {
                    int index = parseIndex(input.substring(7), tasks.size());
                    Task task = tasks.get(index);
                    task.unmarkDone();

                    System.out.println(LINE);
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + task);
                    System.out.println(LINE);
                    continue;
                }

                // 🔥 NEW: delete
                if (input.startsWith("delete ")) {
                    int index = parseIndex(input.substring(7), tasks.size());
                    Task removed = tasks.remove(index);

                    System.out.println(LINE);
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(LINE);
                    continue;
                }

                // todo
                if (input.startsWith("todo")) {
                    if (input.equals("todo")) {
                        throw new TaskyException("The description of a todo cannot be empty.");
                    }

                    Task task = new Todo(input.substring(5));
                    tasks.add(task);
                    printAddMessage(task, tasks.size());
                    continue;
                }

                // deadline
                if (input.startsWith("deadline")) {
                    if (!input.contains(" /by ")) {
                        throw new TaskyException("A deadline must have a /by time.");
                    }

                    String[] parts = input.substring(9).split(" /by ");
                    Task task = new Deadline(parts[0], parts[1]);
                    tasks.add(task);
                    printAddMessage(task, tasks.size());
                    continue;
                }

                // event
                if (input.startsWith("event")) {
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new TaskyException("An event must have /from and /to times.");
                    }

                    String[] parts = input.substring(6).split(" /from ");
                    String[] times = parts[1].split(" /to ");
                    Task task = new Event(parts[0], times[0], times[1]);
                    tasks.add(task);
                    printAddMessage(task, tasks.size());
                    continue;
                }

                // unknown command
                throw new TaskyException("I'm sorry, but I don't know what that means.");

            } catch (TaskyException e) {
                System.out.println(LINE);
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println(LINE);
            }
        }

        scanner.close();
    }

    // ---------- Helper methods ----------

    private static int parseIndex(String input, int size) throws TaskyException {
        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= size) {
                throw new TaskyException("That task number does not exist.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TaskyException("Please enter a valid task number.");
        }
    }

    private static void printAddMessage(Task task, int count) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println(LINE);
    }
}

enum TaskType {
    TODO("[T]"),
    DEADLINE("[D]"),
    EVENT("[E]");

    private final String symbol;

    TaskType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
