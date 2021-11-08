package tests;

import main.java.entity_gateway.TaskReader;
import main.java.entity_gateway.TodoListManager;
import main.java.use_case.TaskGetter;
import main.java.use_case.TodoListDisplayBoundary;
import main.java.use_case.TodoListOutputBoundary;
import main.java.use_case.TodoListsInfo;
import main.java.use_case.TodoListTaskCreationModel;
import org.junit.jupiter.api.Test;

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
        TodoListOutputBoundary todoListPresenter = new MockTodoListPresenter(buffer);

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
        public TaskReader getTask(int todoListId, int taskId) {
            return null;
        }

        @Override
        public Map<Integer, List<TaskReader>> getAllTasks() {
            List<TaskReader> readers = new ArrayList<>();
            readers.add(new MockTaskReader(addedTask));

            Map<Integer, List<TaskReader>> taskMap = new HashMap<>();
            taskMap.put(TODOLIST_ID, readers);
            return taskMap;
        }
    }

    private class MockTaskReader implements TaskReader {

        private final TodoListTaskCreationModel taskData;

        public MockTaskReader(TodoListTaskCreationModel taskData) {
            this.taskData = taskData;
        }

        @Override
        public int getId() {
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
    }

    private class MockTodoListPresenter implements TodoListOutputBoundary {

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
        public int getTodoListId() {
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