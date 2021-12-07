import datagateway.task.TaskReader;
import datagateway.task.TodoListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.taskcreation.TodoListTaskCreationModel;
import services.updateentities.TaskUpdater;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskUpdaterTest {

    TaskUpdater taskUpdater;
    MockTodoListManager manager;

    @BeforeEach
    void setup() {
        manager = new MockTodoListManager();
        taskUpdater = new TaskUpdater(manager);
    }

    @Test
    void updateNmae() {
        taskUpdater.updateName(20L, "mockname");
        assertTrue(manager.isUpdateNameSuccess());
    }

    @Test
    void updateDuration() {
        taskUpdater.updateDuration(21L, Duration.ofHours(2));
        assertTrue(manager.isUpdateDurationSuccess());
    }

    @Test
    void updateDeadline() {
        taskUpdater.updateDeadline(22L, LocalDateTime.of(2021, 12, 17, 12, 0));
        assertTrue(manager.isUpdateDeadlineSuccess());
    }

    @Test
    void addSubtask() {
        taskUpdater.addSubtask(23L, "mocksub");
        assertTrue(manager.isAddSubtaskSuccess());
    }

    @Test
    void removeSubtask() {
        taskUpdater.removeSubtask(24L, "outdated");
        assertTrue(manager.isRemoveSubtaskSuccess());
    }

    @Test
    void completeTask() {
        taskUpdater.completeTask(25L);
        assertTrue(manager.isCompleteTaskSuccess());
    }

    private static class MockTodoListManager implements TodoListManager {

        private boolean updateNameSuccess = false;
        private boolean updateDurationSuccess = false;
        private boolean updateDeadlineSuccess = false;
        private boolean addSubtaskSuccess = false;
        private boolean removeSubtaskSuccess = false;
        private boolean completeTaskSuccess = false;

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
            return null;
        }

        @Override
        public void completeTask(long taskId) {
            if (taskId == 25L) {
                completeTaskSuccess = true;
            }
        }

        @Override
        public void updateName(long id, String newName) {
            if (id == 20L && newName.equals("mockname")) {
                updateNameSuccess = true;
            }
        }

        @Override
        public void updateDuration(long id, Duration newDuration) {
            if (id == 21L && newDuration.equals(Duration.ofHours(2))) {
                updateDurationSuccess = true;
            }
        }

        @Override
        public void updateDeadline(long id, LocalDateTime newDeadline) {
            if (id == 22L && newDeadline.equals(LocalDateTime.of(2021, 12, 17, 12, 0))) {
                updateDeadlineSuccess = true;
            }
        }

        @Override
        public void addSubtask(long id, String subtask) {
            if (id == 23L && subtask.equals("mocksub")) {
                addSubtaskSuccess = true;
            }
        }

        @Override
        public void removeSubtask(long id, String subtask) {
            if (id == 24L && subtask.equals("outdated")) {
                removeSubtaskSuccess = true;
            }
        }

        @Override
        public void loadTodo(String filepath) throws IOException {

        }

        @Override
        public void saveTodo(String filepath) throws IOException {

        }

        public boolean isUpdateNameSuccess() {
            return updateNameSuccess;
        }

        public boolean isUpdateDurationSuccess() {
            return updateDurationSuccess;
        }

        public boolean isUpdateDeadlineSuccess() {
            return updateDeadlineSuccess;
        }

        public boolean isAddSubtaskSuccess() {
            return addSubtaskSuccess;
        }

        public boolean isRemoveSubtaskSuccess() {
            return removeSubtaskSuccess;
        }

        public boolean isCompleteTaskSuccess() {
            return completeTaskSuccess;
        }
    }
}
