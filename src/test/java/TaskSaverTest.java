import datagateway.task.TaskReader;
import datagateway.task.TodoListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.taskcreation.TaskSaver;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
        public long addTask(String name, Duration duration, LocalDateTime deadline, List<String> subtasks) {
            return 0;
        }

        @Override
        public void deleteTask(long taskId) {

        }

        @Override
        public TaskReader getTask(long taskId) {
            return null;
        }

        @Override
        public List<TaskReader> getAllTasks() {
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
