package main.java.entity_gateway;

import main.java.use_case.TodoListTaskCreationModel;

import java.util.List;
import java.util.Map;

public interface TodoListManager {

    int addTask(TodoListTaskCreationModel taskData);

    int createTodoList();

    TaskReader getTask(int todoListId, int taskId);

    TaskReader getTask(int taskId);

    Map<Integer, List<TaskReader>> getAllTasks();

}
