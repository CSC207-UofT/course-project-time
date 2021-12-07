import datagateway.task.TaskReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.taskpresentation.TaskInfo;
import services.taskpresentation.TodoListInfoFromTaskReaders;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoListInfoFromTaskReadersTest {

    TodoListInfoFromTaskReaders todoListInfoFromTaskReaders;
    MockTaskReader reader;

    @BeforeEach
    void setup() {
        reader = new MockTaskReader();
        List<TaskReader> readerList = new ArrayList<>();
        readerList.add(reader);

        Map<Long, List<TaskReader>> readerMap = new HashMap<>();
        readerMap.put(3L, readerList);

        todoListInfoFromTaskReaders = new TodoListInfoFromTaskReaders(readerMap);
    }

    @Test
    void getAllTasks() {
        List<TaskInfo> actual = todoListInfoFromTaskReaders.getAllTasks();
        assertEquals(1, actual.size());

        TaskInfo actualTask = actual.get(0);
        assertEquals(2, actualTask.getId());
        assertEquals("mock", actualTask.getName());
        assertEquals(Duration.ofHours(1), actualTask.getDuration());
        assertEquals(LocalDateTime.of(2021, 12, 5, 13, 0), actualTask.getDeadline());
        assertEquals(new ArrayList<>(), actualTask.getSubtasks());
        assertTrue(actualTask.getCompleted());
    }

    private class MockTaskReader implements TaskReader {

        @Override
        public long getId() {
            return 2;
        }

        @Override
        public String getName() {
            return "mock";
        }

        @Override
        public Duration getDuration() {
            return Duration.ofHours(1);
        }

        @Override
        public LocalDateTime getDeadline() {
            return LocalDateTime.of(2021, 12, 5, 13, 0);
        }

        @Override
        public List<String> getSubtasks() {
            return new ArrayList<>();
        }

        @Override
        public boolean getCompleted() {
            return true;
        }
    }
}
