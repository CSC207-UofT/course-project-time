package main.java.use_cases;

import main.java.entity_accessors.NewTaskData;

public interface TaskCreationBoundary {

    boolean createNewTask(NewTaskData taskData);

}
