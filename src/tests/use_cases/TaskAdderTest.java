package tests.use_cases;

import main.java.entity_accessors.NewTaskData;
import main.java.entity_accessors.TaskManager;
import main.java.use_cases.TaskAdder;
import main.java.use_cases.TaskCreationBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestClassesBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskAdderTest {

    TaskCreationBoundary taskAdder;
    List<NewTaskData> addedDataBuffer;

    @BeforeEach
    void setUp() {
        addedDataBuffer = new ArrayList<>();

        TaskManager mockManager = new MockTestManager(addedDataBuffer);
        taskAdder = new TaskAdder(mockManager);
    }

    @Test
    void addTask() {

        NewTaskData newTaskData = TestClassesBuilder.createNewTaskData();
        taskAdder.createNewTask(newTaskData);
        NewTaskData addedTask = addedDataBuffer.get(0);

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
