package services.task_presentation;

import java.util.Map;

public interface TodoListPresenter {

    void presentTasks(TodoListsInfo todoListInfo);

    Map<Integer, Long> presentTasksForUserSelection(TodoListsInfo todoListInfo);

}
