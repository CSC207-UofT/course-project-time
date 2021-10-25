package main.java.use_cases;

import main.java.entity_accessors.TaskManager;
import main.java.entity_accessors.TaskReader;

import java.util.Map;

public class TaskGetter implements TaskDisplayBoundary {

    private final TaskManager taskManager;

    public TaskGetter(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void sendTasksToPresenter(TaskViewBoundary presenter) {
        Map<Integer, TaskReader> readerMap = taskManager.getTaskReaders();
        TaskInfo[] taskInfos = new TaskInfo[readerMap.size()];
        int i = 0;
        for (TaskReader tr : readerMap.values()) {
            TaskInfo ti = new TaskInfoUsingReader(tr);
            taskInfos[i++] = ti;
        }

        presenter.presentTasks(taskInfos);
    }
}
