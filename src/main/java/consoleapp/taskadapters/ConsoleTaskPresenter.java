package consoleapp.taskadapters;

import consoleapp.ApplicationDriver;
import services.taskpresentation.TaskInfo;
import services.taskpresentation.TodoListPresenter;

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
     * @param taskInfos the tasks DTO whose tasks are to be presented
     */
    @Override
    public void presentTasks(List<TaskInfo> taskInfos) {
        List<String> taskFormattedInfo = new ArrayList<>();
        for (TaskInfo ti : taskInfos) {

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
     * @param taskInfos the task DTO whose tasks are to be presented in an ordered list
     * @return a mapping of task's position in the presented list and id
     */
    @Override
    public Map<Integer, Long> presentTasksForUserSelection(List<TaskInfo> taskInfos) {
        List<String> taskFormattedInfo = new ArrayList<>();
        Map<Integer, Long> positionToIdMapping = new HashMap<>();
        int counter = 1;
        for (TaskInfo ti : taskInfos) {
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
