

import datagateway.task.TaskReader;
import datagateway.task.TodoListManager;
import consoleapp.taskadapters.NewTodoListTaskData;
import services.taskcreation.TaskAdder;
import services.taskcreation.TodoListTaskCreationBoundary;
import services.taskcreation.TodoListTaskCreationModel;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskAdderTest {

    TodoListTaskCreationModel buildRequestModel() {
        String taskName = "Test task";
        Duration timeNeeded = Duration.ofMinutes(30);
        LocalDateTime deadline = LocalDateTime.of(2021, 10, 24, 11, 28);
        List<String> subtasks = new ArrayList<>();

        return new NewTodoListTaskData(taskName, timeNeeded, deadline, subtasks);

    }

    @Test
    void addTask() {
        TodoListManager todoListManager = new MockTodoListManager();
        TodoListTaskCreationBoundary taskAdder = new TaskAdder(todoListManager);

        TodoListTaskCreationModel newTaskData = buildRequestModel();
        taskAdder.addTask(newTaskData);

        long taskId = 0;
        TaskReader sentTask = todoListManager.getTask(taskId);

        assertEquals(sentTask.getName(), newTaskData.getName());
        assertEquals(sentTask.getDuration(), newTaskData.getDuration());
        assertEquals(sentTask.getDeadline(), newTaskData.getDeadline());
        assertArrayEquals(sentTask.getSubtasks().toArray(), newTaskData.getSubtasks().toArray());

    }

    private static class MockTodoListManager implements TodoListManager {

        private TodoListTaskCreationModel sentTask;

        @Override
        public long addTask(TodoListTaskCreationModel taskData) {
            sentTask = taskData;
            return 0L;
        }


        @Override
        public TaskReader getTask(long taskId) {
            return new MockTaskReader(sentTask);
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
