package main.java.use_case;

import main.java.entity_gateway.TaskReader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoListInfoFromTaskReaders implements TodoListOutputDTO {

    private final Map<Integer, Map<Integer, TaskReader>> taskMap;
    private final List<TaskReader> taskReaders;

    public TodoListInfoFromTaskReaders(Map<Integer, List<TaskReader>> taskReaders) {
        taskMap = convertInnerListToMap(taskReaders);
        this.taskReaders = flattenMap(taskReaders);
    }

    private List<TaskReader> flattenMap(Map<Integer, List<TaskReader>> taskReaders) {
        List<TaskReader> readers = new ArrayList<>();

        for (Map.Entry<Integer, List<TaskReader>> e : taskReaders.entrySet())
            readers.addAll(e.getValue());

        return readers;
    }

    private Map<Integer, Map<Integer, TaskReader>> convertInnerListToMap(Map<Integer, List<TaskReader>> readers) {
        Map<Integer, Map<Integer, TaskReader>> map = new HashMap<>();

        for (Map.Entry<Integer, List<TaskReader>> e : readers.entrySet()) {
            Map<Integer, TaskReader> todoListMap = new HashMap<>();
            for (TaskReader tr : e.getValue())
                todoListMap.put(tr.getId(), tr);
            map.put(e.getKey(), todoListMap);
        }
        return map;
    }

    private TaskReader getReader(int todoListId, int taskId) {
        return taskMap.get(todoListId).get(taskId);
    }

    @Override
    public String getName(int todoListId, int taskId) {
        return getReader(todoListId, taskId).getName();
    }

    @Override
    public LocalDateTime getDeadline(int todoListId, int taskId) {
        return getReader(todoListId, taskId).getDeadline();
    }

    @Override
    public Duration getDuration(int todoListId, int taskId) {
        return getReader(todoListId, taskId).getDuration();
    }

    @Override
    public List<String> getSubtasks(int todoListId, int taskId) {
        return getReader(todoListId, taskId).getSubtasks();
    }

    @Override
    public List<TaskOutputDTO> getAllTasks() {
        List<TaskOutputDTO> taskOutputDTOs = new ArrayList<>();
        for (TaskReader tr : taskReaders)
            taskOutputDTOs.add(new TaskInfoFromTaskReader(tr));
        return taskOutputDTOs;
    }
}
