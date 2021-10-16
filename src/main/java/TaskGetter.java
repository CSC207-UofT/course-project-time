package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskGetter {


    public List<HashMap<String, String>> getTasks(AccessTodoData todoData) {
        TodoList todoList = todoData.getTodoList();
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
        if(task.getDeadline() != null)
        {
            task_data.put("deadline", task.getDeadline().toString());
        } else {
            task_data.put("deadline", "NO DEADLINE");
        }

        return task_data;
    }
}
