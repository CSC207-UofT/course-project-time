package tests.use_cases;

import main.java.entity_accessors.NewTaskData;
import main.java.entity_accessors.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestClassesBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskAdderTest {

    NewTaskData newTaskData;

    @BeforeEach
    void setUp() {
        newTaskData = TestClassesBuilder.createNewTaskData();
    }

    @Test
    void addTask() {
        List<NewTaskData> dataBuffer = new ArrayList<>();
        TaskManager mockManager = new MockTestManager(dataBuffer);
        mockManager.addTask(newTaskData);

        NewTaskData addedTask = dataBuffer.get(0);
        assertEquals(newTaskData.taskName, addedTask.taskName);
        assertEquals(newTaskData.deadline, addedTask.deadline);
        assertEquals(newTaskData.timeNeeded, addedTask.timeNeeded);
        assertArrayEquals(newTaskData.subTasks.toArray(), addedTask.subTasks.toArray());

    }

    private static class MockTestManager implements TaskManager {

        List<NewTaskData> newDataBuffer;

        MockTestManager(List<NewTaskData> newDataBuffer) {
            this.newDataBuffer = newDataBuffer;
        }

        @Override
        public boolean addTask(NewTaskData task) {
            newDataBuffer.add(task);
            return true;
        }
    }

}
