package services.taskpresentation;

import java.util.Map;

public interface TodoListDisplayBoundary {
    void presentAllTasks();
    Map<Integer, Long> presentAllTasksForUserSelection();

}
