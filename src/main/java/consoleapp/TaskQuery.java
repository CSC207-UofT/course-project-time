package consoleapp;

import services.taskpresentation.TaskInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskQuery {

    private static final Map<String, String> taskQueryMenu = createTaskQuery();
    private final consoleapp.MainController controller;

    public TaskQuery(MainController controller){
        this.controller = controller;
    }

    private static Map<String, String> createTaskQuery(){
        Map<String, String> taskQueryMenu = new HashMap<>();
        taskQueryMenu.put("0", "Quit");
        taskQueryMenu.put("1", "Edit Task Name");
        taskQueryMenu.put("2", "Edit Task Duration");
        taskQueryMenu.put("3", "Edit Task Deadline");
        taskQueryMenu.put("4", "Add Subtask");
        taskQueryMenu.put("5", "Remove Subtask");
        taskQueryMenu.put("6", "Mark Task Completed");
        return taskQueryMenu;
    }

    /**
     * prompts user for an input until a valid input is received
     * @return a valid input from the user
     */
    private static String getTaskQueryInput() {
        System.out.print("Please enter your input: ");
        Scanner editTaskInput = new Scanner(System.in);
        String input = editTaskInput.nextLine();

        while(!taskQueryMenu.containsKey(input)) {
            System.out.println("\" " + input +  " \" is not a valid input, try again.");
            input = editTaskInput.nextLine();
        }
        return input;
    }

    /**
     * Handle the input based on the mapping queryMenu.
     * @param input string that correspond to a key in testQueryMenu
     * @return whether to continue asking for inputs
     */
    private boolean handleTaskQueryInput(String input, TaskInfo task) {
        System.out.println("###############");
        switch (input) {
            case "0":
                return false;
            case "1":
                long taskId = task.getId();
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new Task name: ");
                String newName;
                newName = scanner.nextLine();
                controller.updateTaskName(taskId, newName);
                System.out.println("Task Name Updated");
                break;
            case "2":
                taskId = task.getId();
                scanner = new Scanner(System.in);
                System.out.print("Enter new Task duration: ");
                String newDuration;
                newDuration = scanner.nextLine();
                controller.updateTaskDuration(taskId, Duration.parse(newDuration));
                System.out.println("Task Duration Updated");
                break;
            case "3":
                taskId = task.getId();
                scanner = new Scanner(System.in);
                System.out.print("Enter new Task deadline: ");
                String newDeadline;
                newDeadline = scanner.nextLine();
                controller.updateTaskDeadline(taskId, LocalDateTime.parse(newDeadline));
                System.out.println("Task Deadline Updated");
                break;
            case "4":
                taskId = task.getId();
                scanner = new Scanner(System.in);
                System.out.print("Enter subtask to add: ");
                String addSubtask;
                addSubtask = scanner.nextLine();
                controller.addSubtask(taskId, addSubtask);
                System.out.println("Subtask Added");
                break;
            case "5":
                taskId = task.getId();
                scanner = new Scanner(System.in);
                System.out.print("Enter subtask to remove: ");
                String removeSubtask;
                removeSubtask = scanner.nextLine();
                controller.removeSubtask(taskId, removeSubtask);
                System.out.println("Subtask Removed");
                break;
            case "6":
                taskId = task.getId();
                controller.completeTask(taskId);
                System.out.println("Task is marked completed");
                break;
            default:
                break;
        }
        return true;
    }

    public void run(TaskInfo task){
        boolean askForInput;
        do {
            System.out.println("\n###############");
            for(String key: taskQueryMenu.keySet()) {
                System.out.println(key + "- " + taskQueryMenu.get(key));
            }

            String input = getTaskQueryInput();
            askForInput = handleTaskQueryInput(input, task);
        } while (askForInput);
    }
}

