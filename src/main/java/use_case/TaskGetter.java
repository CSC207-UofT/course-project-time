package main.java.use_case;

import main.java.entity.Task;
import main.java.entity.TodoList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TaskGetter {
    /**
     * @param todoData (stores a todolist which stores a list of tasks)
     * @return a list of tasks organized in map format, with
     * "name", "deadline", "subtasks", and "completed" as keys
     */
    public List<HashMap<String, String>> getTasks(AccessTodoData todoData) {
        TodoList todoList = todoData.getTodoList();
        List<HashMap<String, String>> task_data = new ArrayList<>();
        for(Task task : todoList.getTasks()) {
            task_data.add(getTask(task));
        }

        return task_data;
    }

    /**
     * @param task
     * @return task data organized in map format, with
     * "name", "deadline", "subtasks", and "completed" as keys
     */
    private HashMap<String, String> getTask(Task task) {
        HashMap<String, String> task_data = new HashMap<>();
        task_data.put("name", task.getTaskName());
        if(task.getDeadline() != null) {
            task_data.put("deadline", task.getDeadline().toString());
        } else {
            task_data.put("deadline", "NO DEADLINE");
        }
        task_data.put("subtasks", Arrays.toString(task.getSubTasks().toArray()));
        task_data.put("completed", Boolean.toString(task.getCompleted()));

        return task_data;
    }
}
