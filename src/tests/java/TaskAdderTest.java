

import data_gateway.TaskReader;
import data_gateway.TodoListManager;
import console_app.task_adapters.NewTodoListTaskData;
import services.task_creation.TaskAdder;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_creation.TodoListTaskCreationModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskAdderTest {

    TodoListTaskCreationModel buildRequestModel(int todoListId) {
        String taskName = "Test task";
        Duration timeNeeded = Duration.ofMinutes(30);
        LocalDateTime deadline = LocalDateTime.of(2021, 10, 24, 11, 28);
        List<String> subtasks = new ArrayList<>();

        return new NewTodoListTaskData(todoListId, taskName, timeNeeded, deadline, subtasks);

    }

    @Test
    void addTask() {
        TodoListManager todoListManager = new MockTodoListManager();
        TodoListTaskCreationBoundary taskAdder = new TaskAdder(todoListManager);

        int newTodoListId = todoListManager.createTodoList();
        TodoListTaskCreationModel newTaskData = buildRequestModel(newTodoListId);
        taskAdder.addTask(newTaskData);

        long todoListId = newTaskData.getTodoListId();
        long taskId = 0;
        TaskReader sentTask = todoListManager.getTask(todoListId, taskId);

        assertEquals(sentTask.getName(), newTaskData.getName());
        assertEquals(sentTask.getDuration(), newTaskData.getDuration());
        assertEquals(sentTask.getDeadline(), newTaskData.getDeadline());
        assertArrayEquals(sentTask.getSubtasks().toArray(), newTaskData.getSubtasks().toArray());

    }

    private static class MockTodoListManager implements TodoListManager {

        private TodoListTaskCreationModel sentTask;

        @Override
        public int addTask(TodoListTaskCreationModel taskData) {
            sentTask = taskData;
            return 0;
        }

        @Override
        public int createTodoList() {
            return 0;
        }

        @Override
        public TaskReader getTask(long todoListId, long taskId) {
            return new MockTaskReader(sentTask);
        }

        @Override
        public Map<Long, List<TaskReader>> getAllTasks() {
            return null;
        }

        @Override
        public boolean completeTask(long taskId) {
            return false;
        }

        @Override
        public void loadTodo(String filepath) {

        }

        @Override
        public void saveTodo(String filepath) {

        }
    }

    private static class MockTaskReader implements TaskReader {
        private final TodoListTaskCreationModel taskData;
        public MockTaskReader(TodoListTaskCreationModel taskData) {
            this.taskData = taskData;
        }

        @Override
        public long getId() {
            return 0;
        }

        @Override
        public String getName() {
            return taskData.getName();
        }

        @Override
        public Duration getDuration() {
            return taskData.getDuration();
        }

        @Override
        public LocalDateTime getDeadline() {
            return taskData.getDeadline();
        }

        @Override
        public List<String> getSubtasks() {
            return taskData.getSubtasks();
        }

        @Override
        public boolean getCompleted() {
            return false;
        }
    }

}
