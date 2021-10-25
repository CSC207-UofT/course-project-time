package tests.use_cases;

import main.java.entity_accessors.NewTaskData;
import main.java.entity_accessors.TaskEntityManager;
import main.java.entity_accessors.TaskManager;
import main.java.use_cases.TaskDisplayBoundary;
import main.java.use_cases.TaskGetter;
import main.java.use_cases.TaskInfo;
import main.java.use_cases.TaskViewBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.TestClassesBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskGetterTest {

    @Test
    void testSendToPresenter() {

        TaskManager taskManager = new TaskEntityManager();
        TaskDisplayBoundary taskGetter = new TaskGetter(taskManager);

        NewTaskData newTaskData = TestClassesBuilder.createNewTaskData();
        taskManager.addTask(newTaskData);

        List<TaskInfo> buffer = new ArrayList<>();
        TaskViewBoundary mockPresenter = new MockTaskPresenter(buffer);

        taskGetter.sendTasksToPresenter(mockPresenter);
        TaskInfo task = buffer.get(0);

        assertEquals(newTaskData.taskName, task.getName());
        assertEquals(newTaskData.deadline, task.getDeadline());
        assertArrayEquals(newTaskData.subTasks.toArray(), task.getSubtasks().toArray());

    }

    private static class MockTaskPresenter implements TaskViewBoundary {

        List<TaskInfo> taskOutputBuffer;

        public MockTaskPresenter(List<TaskInfo> taskOutputBuffer) {
            this.taskOutputBuffer = taskOutputBuffer;
        }

        @Override
        public void presentTasks(TaskInfo[] taskInfos) {
            this.taskOutputBuffer.addAll(Arrays.asList(taskInfos));
        }
    }

}
