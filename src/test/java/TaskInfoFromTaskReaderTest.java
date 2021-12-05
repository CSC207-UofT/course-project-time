import data_gateway.task.TaskReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.task_presentation.TaskInfoFromTaskReader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskInfoFromTaskReaderTest {

    TaskInfoFromTaskReader taskInfoFromTaskReader;
    TaskReader reader;

    @BeforeEach
    void setup() {
        reader = new MockTaskReader();
        taskInfoFromTaskReader = new TaskInfoFromTaskReader(reader);
    }

    @Test
    void getId() {
        long expected = 0;
        long actual = taskInfoFromTaskReader.getId();
        assertEquals(expected, actual);
    }

    @Test
    void getName() {
        String expected = "mock";
        String actual = taskInfoFromTaskReader.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getDuration() {
        Duration expected = Duration.ofHours(1);
        Duration actual = taskInfoFromTaskReader.getDuration();
        assertEquals(expected, actual);
    }

    @Test
    void getDeadline() {
        LocalDateTime expected = LocalDateTime.of(2021, 12, 1, 12, 0);
        LocalDateTime actual = taskInfoFromTaskReader.getDeadline();
        assertEquals(expected, actual);
    }

    @Test
    void getSubtasks() {
        List<String> expected = new ArrayList<>();
        expected.add("sub1");
        expected.add("sub2");

        List<String> actual = taskInfoFromTaskReader.getSubtasks();
        assertEquals(expected, actual);
    }

    @Test
    void getCompleted() {
        assertTrue(taskInfoFromTaskReader.getCompleted());
    }

    private static class MockTaskReader implements TaskReader {

        @Override
        public long getId() {
            return 0;
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
            return LocalDateTime.of(2021, 12, 1, 12, 0);
        }

        @Override
        public List<String> getSubtasks() {
            List<String> subtasks = new ArrayList<>();
            subtasks.add("sub1");
            subtasks.add("sub2");
            return subtasks;
        }

        @Override
        public boolean getCompleted() {
            return true;
        }
    }
}
