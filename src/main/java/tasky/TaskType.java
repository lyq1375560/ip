package tasky;

/**
 * Represents the supported types of tasks.
 */
public enum TaskType {

    /** Todo task without date or time. */
    TODO("[T]"),

    /** Deadline task with a due date. */
    DEADLINE("[D]"),

    /** Event task with a start and end time. */
    EVENT("[E]");

    /** Symbol used to represent the task type in output. */
    private final String symbol;

    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol representing this task type.
     *
     * @return Task type symbol
     */
    public String getSymbol() {
        return symbol;
    }
}
