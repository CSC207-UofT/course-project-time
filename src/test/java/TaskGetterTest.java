import datagateway.task.TaskReader;
import datagateway.task.TodoListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.taskpresentation.TaskGetter;
import services.taskpresentation.TaskInfo;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskGetterTest {

    TaskGetter taskGetter;
    MockTodoListManager manager;

    @BeforeEach
    void setup() {
        manager = new MockTodoListManager();
        taskGetter = new TaskGetter(manager);
    }

    @Test
    void getTasksById() {
        TaskInfo actual = taskGetter.getTaskById(1L);
        assertEquals(actual.getId(), 1L);
        assertTrue(actual.getCompleted());
        assertEquals(actual.getDeadline(), LocalDateTime.of(2021, 12, 10, 12, 0));
        assertEquals(actual.getDuration(), Duration.ofHours(1));
        assertEquals(actual.getName(), "mock");
        assertEquals(actual.getSubtasks(), new ArrayList<>());
    }

    private static class MockTodoListManager implements TodoListManager {

        @Override
        public long addTask(String name, Duration duration, LocalDateTime deadline, List<String> subtasks) {
            return 0;
        }

        @Override
        public TaskReader getTask(long taskId) {
            return null;
        }

        @Override
        public Map<Long, List<TaskReader>> getAllTasks() {
            Map<Long, List<TaskReader>> tasksMap = new HashMap<>();
            List<TaskReader> tasks = new ArrayList<>();
            TaskReader task = new MockTaskReader();
            tasks.add(task);
            tasksMap.put(2L, tasks);
            return tasksMap;
        }

        @Override
        public void completeTask(long taskId) {

        }

        @Override
        public void updateName(long id, String newName) {

        }

        @Override
        public void updateDuration(long id, Duration newDuration) {

        }

        @Override
        public void updateDeadline(long id, LocalDateTime newDeadline) {

        }

        @Override
        public void addSubtask(long id, String subtask) {

        }

        @Override
        public void removeSubtask(long id, String subtask) {

        }

        @Override
        public void loadTodo(String filepath) throws IOException {

        }

        @Override
        public void saveTodo(String filepath) throws IOException {

        }
    }

    private static class MockTaskReader implements TaskReader {

        @Override
        public long getId() {
            return 1;
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
            return LocalDateTime.of(2021, 12, 10, 12, 0);
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
