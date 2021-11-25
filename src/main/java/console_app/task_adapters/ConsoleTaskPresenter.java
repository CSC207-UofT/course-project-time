package console_app.task_adapters;

import console_app.ApplicationDriver;
import services.task_presentation.TaskInfo;
import services.task_presentation.TodoListPresenter;
import services.task_presentation.TodoListsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleTaskPresenter implements TodoListPresenter {
    private final ApplicationDriver applicationDriver;

    public ConsoleTaskPresenter(ApplicationDriver applicationDriver) {
        this.applicationDriver = applicationDriver;
    }

    /**
     * Formats tasks' information and presents them on the console.
     * @param todoListInfo the todolist DTO whose tasks are to be presented
     */
    @Override
    public void presentTasks(TodoListsInfo todoListInfo) {
        List<String> taskFormattedInfo = new ArrayList<>();
        List<TaskInfo> tasks = todoListInfo.getAllTasks();
        for (TaskInfo ti : tasks) {

            String name = ti.getName();

            String deadline;
            if (ti.getDeadline() != null) {
                deadline = ti.getDeadline().toString();
            } else {
                deadline = "NO DEADLINE";
            }

            String subtasks = ti.getSubtasks().toString();
            boolean completed = ti.getCompleted();

            String output = "Task: " + name + ", "
                    + "deadline = " + deadline + ", "
                    + "subtasks = " + subtasks + ", "
                    + "completed = " + completed;
            taskFormattedInfo.add(output);
        }
        applicationDriver.printTasks(taskFormattedInfo);
    }

    /**
     * Formats the tasks' information in an ordered list and presents them.
     * Passes the mapping of the position of task in the list to the task's actual id
     * to the ApplicationDriver by setting it as an attribute of the ApplicationDriver.
     * @param todoListInfo the todolist DTO whose tasks are to be presented in an ordered list
     * @return a mapping of task's position in the presented list and id
     */
    @Override
    public Map<Integer, Long> presentTasksForUserSelection(TodoListsInfo todoListInfo) {
        List<String> taskFormattedInfo = new ArrayList<>();
        Map<Integer, Long> positionToIdMapping = new HashMap<>();
        List<TaskInfo> tasks = todoListInfo.getAllTasks();
        int counter = 1;
        for (TaskInfo ti : tasks) {
            positionToIdMapping.put(counter, ti.getId());

            String name = ti.getName();

            String deadline;
            if (ti.getDeadline() != null) {
                deadline = ti.getDeadline().toString();
            } else {
                deadline = "NO DEADLINE";
            }

            String output = counter + ") "
                    + "Task: " + name + ", "
                    + "deadline = " + deadline;

            taskFormattedInfo.add(output);
            counter++;
        }
        applicationDriver.printTasks(taskFormattedInfo);
        return positionToIdMapping;
    }
}
