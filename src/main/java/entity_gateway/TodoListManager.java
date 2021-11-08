package main.java.entity_gateway;

import main.java.use_case.TodoListTaskCreationDTO;

import java.util.List;
import java.util.Map;

public interface TodoListManager {

    int addTask(TodoListTaskCreationDTO taskData);

    int createTodoList();

    TaskReader getTask(int todoListId, int taskId);

    Map<Integer, List<TaskReader>> getAllTasks();

}
