package services.task_presentation;

import data_gateway.TaskReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TodoListInfoFromTaskReaders implements TodoListsInfo {

    private final List<TaskReader> taskReaders;

    public TodoListInfoFromTaskReaders(Map<Long, List<TaskReader>> taskReaders) {
        this.taskReaders = flattenMap(taskReaders);
    }

    private List<TaskReader> flattenMap(Map<Long, List<TaskReader>> taskReaders) {
        List<TaskReader> readers = new ArrayList<>();

        for (Map.Entry<Long, List<TaskReader>> e : taskReaders.entrySet())
            readers.addAll(e.getValue());

        return readers;
    }


    @Override
    public List<TaskInfo> getAllTasks() {
        List<TaskInfo> taskInfos = new ArrayList<>();
        for (TaskReader tr : taskReaders)
            taskInfos.add(new TaskInfoFromTaskReader(tr));
        return taskInfos;
    }
}
