<<<<<<< HEAD
/**
 * Represents the type of a task managed by Tasky.
 * Each type corresponds to a specific task category.
 */
=======
>>>>>>> 533a17a (Level-7: save tasks to disk)
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 533a17a (Level-7: save tasks to disk)
