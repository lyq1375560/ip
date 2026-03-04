package tasky;

import java.util.Scanner;

/**
 * Handles all interactions between the user and the application.
 * <p>
 * This class is responsible for displaying messages to the user
 * and reading user input from standard input.
 */
public class Ui {

    /** Divider line used to format output consistently. */
    private static final String LINE =
            "____________________________________________________________";

    /** Scanner used to read user input from standard input. */
    private final Scanner scanner = new Scanner(System.in);

    private final StringBuilder response = new StringBuilder();

    /**
     * Displays the welcome message shown when the application starts.
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm Tasky");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Reads a full command entered by the user.
     *
     * @return The user input as a string
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays a normal message to the user.
     *
     * @param message Message to be shown
     */
    public void showMessage(String message) {
        response.append(message).append("\n");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message Error description
     */
    public void showError(String message) {
        response.append("OOPS!!! ").append(message).append("\n");
    }

    /**
     * Returns the accumulated response and clears the buffer.
     */
    public String getResponse() {
        String output = response.toString();
        response.setLength(0);
        return output;
    }
}
