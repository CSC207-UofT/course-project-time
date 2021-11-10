package main.java.entity_gateway;

import main.java.use_case.TodoListTaskCreationModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TodoListManager {

    int addTask(TodoListTaskCreationModel taskData);

    int createTodoList();

    TaskReader getTask(int todoListId, int taskId);

    Map<Integer, List<TaskReader>> getAllTasks();

    void loadTodo(String filepath) throws FileNotFoundException;

    void saveTodo(String filepath) throws IOException;

}
