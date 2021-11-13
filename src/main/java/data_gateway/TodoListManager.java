package main.java.data_gateway;

import main.java.services.task_creation.TodoListTaskCreationModel;

import java.util.List;
import java.util.Map;

public interface TodoListManager {

    int addTask(TodoListTaskCreationModel taskData);

    int createTodoList();

    TaskReader getTask(long todoListId, long taskId);

    Map<Long, List<TaskReader>> getAllTasks();

}
