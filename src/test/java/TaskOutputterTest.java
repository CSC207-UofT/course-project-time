import datagateway.task.TaskReader;
import datagateway.task.TodoListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.taskcreation.TodoListTaskCreationModel;
import services.taskpresentation.TaskOutputter;
import services.taskpresentation.TodoListPresenter;
import services.taskpresentation.TodoListsInfo;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskOutputterTest {

    TaskOutputter taskOutputter;
    MockTodoListManager manager;
    MockTodoListPresenter presenter;

    @BeforeEach
    void setup() {
        manager = new MockTodoListManager();
        presenter = new MockTodoListPresenter();
        taskOutputter = new TaskOutputter(manager, presenter);
    }

    @Test
    void presentAllTasks() {
        taskOutputter.presentAllTasks();
        assertTrue(presenter.isPresentTasksSuccess());
    }

    @Test
    void presentAllTasksForUserSelection() {
        Map<Integer, Long> expected = new HashMap<>();
        Map<Integer, Long> actual = taskOutputter.presentAllTasksForUserSelection();
        assertEquals(expected, actual);
    }

    private static class MockTodoListManager implements TodoListManager {

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
            return new HashMap<>();
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

    private static class MockTodoListPresenter implements TodoListPresenter {

        private boolean presentTasksSuccess = false;

        @Override
        public void presentTasks(TodoListsInfo todoListInfo) {
            if (todoListInfo.getAllTasks().equals(new ArrayList<>())) {
                presentTasksSuccess = true;
            }
        }

        @Override
        public Map<Integer, Long> presentTasksForUserSelection(TodoListsInfo todoListInfo) {
            if (todoListInfo.getAllTasks().equals(new ArrayList<>())) {
                return new HashMap<>();
            } else {
                return null;
            }
        }

        public boolean isPresentTasksSuccess() {
            return presentTasksSuccess;
        }
    }
}
