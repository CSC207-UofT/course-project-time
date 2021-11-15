package data_gateway;

import services.task_creation.TodoListTaskCreationModel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TodoListManager {

    int addTask(TodoListTaskCreationModel taskData);

    int createTodoList();

    TaskReader getTask(long todoListId, long taskId);

    Map<Long, List<TaskReader>> getAllTasks();

    boolean completeTask(long taskId);

    void loadTodo(String filepath) throws IOException;

    void saveTodo(String filepath) throws IOException;

}
