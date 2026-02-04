package tasky;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void markDone_newTask_taskIsMarkedDone() {
        Task task = new Todo("read book");

        task.markDone();

        assertTrue(task.isDone);
    }

    @Test
    public void unmarkDone_doneTask_taskIsNotDone() {
        Task task = new Todo("read book");
        task.markDone();

        task.unmarkDone();

        assertFalse(task.isDone);
    }
}
