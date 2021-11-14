package tests;

import main.java.data_gateway.TaskReader;
import main.java.data_gateway.TodoListManager;
import main.java.services.task_presentation.TaskGetter;
import main.java.services.task_presentation.TodoListDisplayBoundary;
import main.java.services.task_presentation.TodoListPresenter;
import main.java.services.task_creation.TodoListTaskCreationModel;
import main.java.services.task_presentation.TodoListsInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskGetterTest {

    int TASK_ID = 1;
    int TODOLIST_ID = 0;

    @Test
    void testTaskPresenting() {
        TodoListManager todoListManager = new MockTodoListManager();
        List<TodoListsInfo> buffer = new ArrayList<>();
        TodoListPresenter todoListPresenter = new MockTodoListPresenter(buffer);

        TodoListTaskCreationModel taskData = new MockTaskData(TODOLIST_ID);
        todoListManager.addTask(taskData);

        TodoListDisplayBoundary taskGetter = new TaskGetter(todoListManager, todoListPresenter);
        taskGetter.presentAllTodoLists();

        TodoListsInfo addedInfo = buffer.get(0);
        assertEquals(addedInfo.getName(TODOLIST_ID, TASK_ID), taskData.getName());
        assertEquals(addedInfo.getDeadline(TODOLIST_ID, TASK_ID), taskData.getDeadline());
        assertEquals(addedInfo.getDuration(TODOLIST_ID, TASK_ID), taskData.getDuration());
        assertArrayEquals(addedInfo.getSubtasks(TODOLIST_ID, TASK_ID).toArray(), taskData.getSubtasks().toArray());
    }

    private class MockTodoListManager implements TodoListManager {

        private TodoListTaskCreationModel addedTask;

        @Override
        public int addTask(TodoListTaskCreationModel taskData) {
            addedTask = taskData;
            return TASK_ID;
        }

        @Override
        public int createTodoList() {
            return TODOLIST_ID;
        }

        @Override
        public TaskReader getTask(long todoListId, long taskId) {
            return null;
        }

        @Override
        public Map<Long, List<TaskReader>> getAllTasks() {
            List<TaskReader> readers = new ArrayList<>();
            readers.add(new MockTaskReader(addedTask));

            Map<Long, List<TaskReader>> taskMap = new HashMap<>();
            taskMap.put((long) TODOLIST_ID, readers);
            return taskMap;
        }

        @Override
        public void loadTodo(String filepath) throws IOException {

        }

        @Override
        public void saveTodo(String filepath) throws IOException {

        }
    }

    private class MockTaskReader implements TaskReader {

        private final TodoListTaskCreationModel taskData;

        public MockTaskReader(TodoListTaskCreationModel taskData) {
            this.taskData = taskData;
        }

        @Override
        public long getId() {
            return TASK_ID;
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

    private class MockTodoListPresenter implements TodoListPresenter {

        private final List<TodoListsInfo> presenterBuffer;

        public MockTodoListPresenter(List<TodoListsInfo> buffer) {
            this.presenterBuffer = buffer;
        }

        @Override
        public void presentTasks(TodoListsInfo todoListInfo) {
            presenterBuffer.add(todoListInfo);
        }
    }

    private class MockTaskData implements TodoListTaskCreationModel {

        int todoListId;
        String taskName = "Test task";
        Duration timeNeeded = Duration.ofMinutes(30);
        LocalDateTime deadline = LocalDateTime.of(2021, 10, 24, 11, 28);
        List<String> subtasks = new ArrayList<>();


        public MockTaskData(int todoListId) {
            this.todoListId = todoListId;
        }

        @Override
        public long getTodoListId() {
            return todoListId;
        }

        @Override
        public String getName() {
            return taskName;
        }

        @Override
        public Duration getDuration() {
            return timeNeeded;
        }

        @Override
        public LocalDateTime getDeadline() {
            return deadline;
        }

        @Override
        public List<String> getSubtasks() {
            return subtasks;
        }
    }

}
