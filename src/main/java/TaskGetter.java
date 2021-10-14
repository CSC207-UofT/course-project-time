package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskGetter {
    TodoList todoList;

    // TODO add a way to access the elements of a todo list

    private HashMap<String, String> getTasks(Task task)
    {
        String task_name = task.getTaskName();
        HashMap<String, String> task_data = new HashMap<>();
        task_data.put("name", task_name);
        return task_data;

    }
}
