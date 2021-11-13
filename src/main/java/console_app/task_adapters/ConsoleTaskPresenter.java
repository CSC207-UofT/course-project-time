package main.java.console_app.task_adapters;

import main.java.services.task_presentation.TaskInfo;
import main.java.services.task_presentation.TodoListPresenter;
import main.java.services.task_presentation.TodoListsInfo;

import java.util.List;

public class ConsoleTaskPresenter implements TodoListPresenter {

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
