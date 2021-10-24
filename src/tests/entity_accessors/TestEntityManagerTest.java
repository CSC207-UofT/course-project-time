package tests.entity_accessors;

import main.java.entity_accessors.NewTaskData;
import main.java.entity_accessors.TaskEntityManager;
import main.java.entity_accessors.TaskManager;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestEntityManagerTest {

    @Test
    void addNewTask() {

        String taskName = "Test task";
        Duration timeNeeded = Duration.ofMinutes(10);
        LocalDateTime deadline = LocalDateTime.of(2021, 10, 23, 7, 10);
        List<String> subTasks = new ArrayList<>();

        NewTaskData newTaskData = new NewTaskData(taskName, timeNeeded, deadline, subTasks);

        TaskManager tm = new TaskEntityManager();
        tm.addTask(newTaskData);

    }

}
