package services.task_presentation;

import java.util.Map;

public interface TodoListDisplayBoundary {
    void presentAllTasks();
    Map<Integer, Long> presentAllTasksForUserSelection();

}
