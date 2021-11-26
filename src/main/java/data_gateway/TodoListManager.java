package data_gateway;

import entity.Task;
import services.task_creation.TodoListTaskCreationModel;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TodoListManager {

    void addTask(TodoListTaskCreationModel taskData);

    TaskReader getTask(long taskId);

    Map<Long, List<TaskReader>> getAllTasks();

    void completeTask(long taskId);

    void updateName(long id, String newName);

    void updateDuration(long id, Duration newDuration);

    void updateDeadline(long id, LocalDateTime newDeadline);

    void loadTodo(String filepath) throws IOException;

    void saveTodo(String filepath) throws IOException;

}
