package tests;

import main.java.entity_accessors.NewTaskData;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestClassesBuilder {

    public static NewTaskData createNewTaskData() {
        String taskName = "Test task";
        Duration timeNeeded = Duration.ofMinutes(10);
        LocalDateTime deadline = LocalDateTime.of(2021, 10, 23, 7, 10);
        List<String> subTasks = new ArrayList<>();

        return new NewTaskData(taskName, timeNeeded, deadline, subTasks);
    }

}
