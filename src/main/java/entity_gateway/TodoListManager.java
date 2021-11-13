package main.java.entity_gateway;

import main.java.use_case.TodoListTaskCreationModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TodoListManager {

    int addTask(TodoListTaskCreationModel taskData);

    int createTodoList();

    TaskReader getTask(long todoListId, long taskId);

    Map<Long, List<TaskReader>> getAllTasks();

    void loadTodo(String filepath) throws FileNotFoundException;

    void saveTodo(String filepath) throws IOException;

}
