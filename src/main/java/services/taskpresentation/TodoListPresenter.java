package services.taskpresentation;

import java.util.List;
import java.util.Map;

public interface TodoListPresenter {

    void presentTasks(List<TaskInfo> taskInfos);

    Map<Integer, Long> presentTasksForUserSelection(List<TaskInfo> taskInfos);

}
