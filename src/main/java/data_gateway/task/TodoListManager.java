package data_gateway.task;

import services.task_creation.TodoListTaskCreationModel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TodoListManager {

    long addTask(TodoListTaskCreationModel taskData);

    TaskReader getTask(long taskId);

    Map<Long, List<TaskReader>> getAllTasks();

    void completeTask(long taskId);

    void loadTodo(String filepath) throws IOException;

    void saveTodo(String filepath) throws IOException;

}