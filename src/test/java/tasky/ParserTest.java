package tasky;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void mark_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));

        Ui ui = new Ui();
        Storage storage = new Storage("data", "data/test.txt");

        TaskyException exception = assertThrows(
                TaskyException.class,
                () -> Parser.parseAndExecute(
                        "mark 5",
                        tasks,
                        ui,
                        storage
                )
        );

        assertEquals("That task number does not exist.", exception.getMessage());
    }
}
