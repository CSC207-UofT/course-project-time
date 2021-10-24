package main.java.entity_accessors;

import java.util.Map;

public interface TaskManager {

    boolean addTask(NewTaskData task);

    Map<Integer, TaskReader> getTaskReaders();

}
