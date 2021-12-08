package services.taskpresentation;

import java.util.List;

public interface TodoListRequestBoundary {
    TaskInfo getTaskById(Long id);
    List<TaskInfo> getTasks();
}
