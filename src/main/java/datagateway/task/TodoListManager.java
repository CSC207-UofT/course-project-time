package datagateway.task;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoListManager {

    long addTask(String name, Duration duration, LocalDateTime deadline, List<String> subtasks);

    void deleteTask(long taskId);

    TaskReader getTask(long taskId);

    List<TaskReader> getAllTasks();

    void completeTask(long taskId);

    void updateName(long id, String newName);

    void updateDuration(long id, Duration newDuration);

    void updateDeadline(long id, LocalDateTime newDeadline);

    void addSubtask(long id, String subtask);

    void removeSubtask(long id, String subtask);

    void loadTodo(String filepath) throws IOException;

    void saveTodo(String filepath) throws IOException;

}
