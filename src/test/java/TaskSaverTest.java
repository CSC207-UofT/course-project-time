import data_gateway.task.TaskReader;
import data_gateway.task.TodoListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.task_creation.TaskSaver;
import services.task_creation.TodoListTaskCreationModel;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskSaverTest {

    TaskSaver taskSaver;
    MockTodoListManager manager;

    @BeforeEach
    void setup() {
        manager = new MockTodoListManager();
        taskSaver = new TaskSaver(manager);
    }

    @Test
    void save() throws IOException {
        taskSaver.save("mock");
        assertTrue(manager.isSaveTodoSuccess());
    }

    private static class MockTodoListManager implements TodoListManager {

        private boolean saveTodoSuccess = false;

        @Override
        public long addTask(TodoListTaskCreationModel taskData) {
            return 0;
        }

        @Override
        public TaskReader getTask(long taskId) {
            return null;
        }

        @Override
        public Map<Long, List<TaskReader>> getAllTasks() {
            return null;
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
            if (filepath.equals("mock")) {
                saveTodoSuccess = true;
            }
        }

        public boolean isSaveTodoSuccess() {
            return saveTodoSuccess;
        }
    }
}
