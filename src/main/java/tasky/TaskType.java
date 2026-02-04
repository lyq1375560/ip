<<<<<<< HEAD:src/main/java/TaskType.java
<<<<<<< HEAD
/**
 * Represents the type of a task managed by Tasky.
 * Each type corresponds to a specific task category.
 */
=======
>>>>>>> 533a17a (Level-7: save tasks to disk)
=======
package tasky;

<<<<<<< HEAD
>>>>>>> 3e560c7 (A-Packages: move classes into tasky package):src/main/java/tasky/TaskType.java
enum TaskType {
=======
public enum TaskType {
>>>>>>> af965df (Add Gradle build support)
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
