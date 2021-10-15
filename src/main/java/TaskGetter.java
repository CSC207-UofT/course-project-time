package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskGetter {
    private TodoList todoList;

    public List<HashMap<String, String>> getTasks() {
        List<HashMap<String, String>> task_data = new ArrayList<>();
        for(Task task : todoList.getTasks()) {
            task_data.add(getTask(task));
        }

        return task_data;
    }

    private HashMap<String, String> getTask(Task task)
    {
        HashMap<String, String> task_data = new HashMap<>();
        task_data.put("name", task.getTaskName());
        task_data.put("deadline", task.getDeadline().toString());

        return task_data;
    }
}
