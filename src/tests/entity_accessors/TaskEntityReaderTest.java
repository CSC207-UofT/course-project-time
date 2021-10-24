package tests.entity_accessors;

import main.java.entities.Task;
import main.java.entity_accessors.TaskEntityReader;
import main.java.entity_accessors.TaskReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskEntityReaderTest {

    TaskReader taskReader;
    Task innerTask;

    @BeforeEach
    void setUp() {
        String taskName = "Test task";
        Duration timeNeeded = Duration.ofMinutes(10);
        LocalDateTime deadline = LocalDateTime.of(2021, 10, 23, 7, 10);
        List<String> subTasks = new ArrayList<>();

        innerTask = new Task(taskName, timeNeeded, deadline, subTasks);
        taskReader = new TaskEntityReader(innerTask);
    }

    @Test
    void testGetName() {
        assertEquals(taskReader.getName(), innerTask.getTaskName());
    }

    @Test
    void testGetDeadline() {
        assertEquals(taskReader.getDeadline(), innerTask.getDeadline());
    }

    @Test
    void testGetSubtasks() {
        List<String> readerSubtasks = taskReader.getSubtasks();
        List<String> taskSubtasks = innerTask.getSubTasks();

        String[] readerSubtaskArr = new String[readerSubtasks.size()];
        readerSubtasks.toArray(readerSubtaskArr);

        String[] taskSubtaskArr = new String[taskSubtasks.size()];
        taskSubtasks.toArray(taskSubtaskArr);

        assertArrayEquals(readerSubtaskArr, taskSubtaskArr);
    }

    @Test
    void testCompletedStatus() {
        assertEquals(taskReader.getCompleted(), innerTask.getCompleted());
    }

}
