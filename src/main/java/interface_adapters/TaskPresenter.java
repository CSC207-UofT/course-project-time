package main.java.interface_adapters;

import main.java.use_case.TaskInfo;
import main.java.use_case.TodoListPresenter;
import main.java.use_case.TodoListsInfo;

import java.util.List;

public class TaskPresenter implements TodoListPresenter {

    @Override
    public void presentTasks(TodoListsInfo todoListInfo) {
        List<TaskInfo> tasks = todoListInfo.getAllTasks();
        if (tasks.size() == 0) {
            System.out.println("No tasks have been created");
        }
        for (TaskInfo ti : tasks) {

            String name = ti.getName();
            String deadline = ti.getDeadline().toString();
            String subtasks = ti.getSubtasks().toString();
            boolean completed = ti.getCompleted();

            String output = "Task: " + name + ", "
                    + "deadline = " + deadline + ", "
                    + "subtasks = " + subtasks + ", "
                    + "completed = " + completed;
            System.out.println(output);
        }
    }
}
