package main.java.entity_gateway;

import main.java.use_case.TodoListTaskCreationModel;

import java.util.List;
import java.util.Map;

public interface TodoListManager {

    int addTask(TodoListTaskCreationModel taskData);

    int createTodoList();

    TaskReader getTask(long todoListId, long taskId);

    TaskReader getTask(long taskId);

    Map<Long, List<TaskReader>> getAllTasks();

    List<TaskReader> getAllTasksInList();

}
