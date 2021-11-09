package main.java;

import main.java.entity.TodoList;
import main.java.interface_adapters.MainController;
import main.java.entity.Task;
import main.java.use_case.TaskInfo;
import main.java.use_case.TodoListsInfo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ApplicationDriver {

    private static final MainController controller = new MainController();

    private static final Map<String, String> queryMenu = createdQueryMap();

    /**
     * Creates a mapping of query and input by user
     * @return a map storing the mapping of user input to the query
     */
    private static Map<String, String> createdQueryMap() {
        Map<String, String> queryMenu = new HashMap<>();
        queryMenu.put("0", "Quit");
        queryMenu.put("1", "View all events");
        queryMenu.put("2", "View all tasks");
        queryMenu.put("3", "Create a new task");
        queryMenu.put("4", "Create a new event");
        queryMenu.put("5", "Auto schedule a task");
        queryMenu.put("6", "Manually schedule a task");
        return queryMenu;
    }

    /**
     * prompts user for an input until a valid input is received
     * @return a valid input from the user
     */
    private static String getQueryInput() {
        System.out.print("Please enter your input: ");
        Scanner command_input = new Scanner(System.in);
        String input = command_input.nextLine();

        while(!queryMenu.containsKey(input)) {
            System.out.println("\" " + input +  " \" is not a valid input, try again.");
            input = command_input.nextLine();
        }

        return input;
    }

    /**
     * Handle the input based on the mapping queryMenu.
     * @param input string that correspond to a key in queryMenu
     * @return whether to continue asking for inputs
     */
    private static boolean handleQueryInput(String input) {
        System.out.println("###############");
        switch (input) {
            case "0":
                return false;
            case "1":
                controller.presentEvents();
                break;
            case "2":
                printTasks();
                break;
            case "3":
                boolean success = handleCreateTask();
                if (success) {
                    System.out.println("Task created");
                } else {
                    System.out.println("Failed to create task");
                }
                break;
            case "4":
                success = handleCreateEvent();
                if (success) {
                    System.out.println("Event created");
                } else {
                    System.out.println("Failed to create event");
                }
                break;
            case "5":
                printTasks();
                TaskInfo task = chooseTask();
                success = controller.suggestTimeToUser(task);
                if (success) {
                    System.out.println("Event created from task");
                } else {
                    // todo use exceptions in the future to track reason of failure
                    System.out.println("Failed to create event from task");
                }
                break;
            case "6":
                printTasks();
                TaskInfo taskManual = chooseTask();
                LocalDateTime userSuggestedTime;
                boolean timeAvailable;

                do {
                    userSuggestedTime = inputTime();
                    timeAvailable = controller.checkUserSuggestedTime(taskManual, userSuggestedTime);
                    if (!timeAvailable) {
                        System.out.println("Time not available, please retry.");
                    }
                } while (!timeAvailable);

                success = controller.createEvent(taskManual.getName(), userSuggestedTime.toLocalTime(),
                        userSuggestedTime.toLocalTime().plus(taskManual.getDuration()), new HashSet<>(), userSuggestedTime.toLocalDate());

                if (success) {
                    System.out.println("Event created from task");
                } else {
                    System.out.println("Failed to create event from task");
                }
                break;
            default:
                break;
        }
        // if this line is reached, then we did not receive a 'quit' input
        return true;
    }


    /**
     * prompts the user for inputs needed to create a new event
     * and passes the information to a controller to create the event
     * in the database
     * @return whether the event has been created
     */
    private static boolean handleCreateEvent() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter event name: ");
        String eventName = input.nextLine();

        String timeFormat = "HH:mm";
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat);

        System.out.print("Enter start time for event in (" + timeFormat + ") (24 hour time): ");
        String startTimeResponse = input.nextLine(); // TODO exception handling
        LocalTime eventStartTime = LocalTime.parse(startTimeResponse, timeFormatter);

        System.out.print("Enter end time for event in (" + timeFormat + ") (24 hour time): ");
        String endTimeResponse = input.nextLine(); // todo exception handling
        LocalTime eventEndTime = LocalTime.parse(endTimeResponse, timeFormatter);

        String dateFormat = "yyyy-MM-dd";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

        System.out.print("Enter date for event in (" + dateFormat + "): ");
        String dateResponse = input.nextLine();  // todo exception handling
        LocalDate eventDate = LocalDate.parse(dateResponse, dateFormatter);

        System.out.print("Enter tags for event, separated by space, or press enter if there are no tags: ");
        String tagResponse = input.nextLine(); // todo exception handling
        String[] tagArray = tagResponse.split(" ");
        HashSet<String> eventTags = new HashSet<>(Arrays.asList(tagArray));

        return controller.createEvent(eventName, eventStartTime, eventEndTime, eventTags, eventDate);
    }

    /**
     * prompts the user for inputs needed to create a new task
     * and passes the information to a controller to create the task
     * in the database
     * @return whether the task has been created
     */
    private static boolean handleCreateTask() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter task name: ");
        String taskName = input.nextLine();

        System.out.print("Enter approximate duration needed in minutes: ");
        int durationResponse = Integer.parseInt(input.nextLine());
        Duration taskDuration = Duration.ofMinutes(durationResponse);

        String format = "yyyy/MM/dd-HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        System.out.print("Input deadline for task in (" + format + ") (24 hour time), " +
                            "or 'n' if there is no deadline: ");
        String deadlineResponse = input.nextLine(); // TODO exception handling
        LocalDateTime taskDeadline;
        if (Objects.equals(deadlineResponse, "n")) {
            taskDeadline = null;
        } else {
            taskDeadline = LocalDateTime.parse(deadlineResponse, formatter);
        }

        System.out.print("Enter any subtasks for task, separated by a space, or press enter if there are no subtasks: ");
        String subtaskResponse = input.nextLine();  // TODO exception handling

        String[] subtaskArray = subtaskResponse.split(" ");
        ArrayList<String> taskSubtasks = new ArrayList<>(Arrays.asList(subtaskArray));

        return controller.createTask(taskName, taskDuration, taskDeadline, taskSubtasks);
    }

    /**
     * Print all tasks
     */
    private static void printTasks() {
        TodoListsInfo todoListsInfo = controller.getTasks();
        List<TaskInfo> allTasksData = todoListsInfo.getAllTasks();
        if (allTasksData.size() == 0) {
            System.out.println("No tasks have been created");
        }
        for (TaskInfo ti : allTasksData) {

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

    /**
     * Prompts the user to choose a Task among the list of Tasks
     * @return the chosen Task
     */
    private static TaskInfo chooseTask() {
        TodoListsInfo todoListsInfo = controller.getTasks();
        List<TaskInfo> taskInfos = todoListsInfo.getAllTasks();
        List<String> taskNames = new ArrayList<>();
        for (TaskInfo ti: taskInfos) {
            taskNames.add(ti.getName());
        }

        Scanner scanner = new Scanner(System.in);
        String chosen;

        // todo in the future lift the assumption where names are unique
        do {
            System.out.print("Please choose a task by typing its name (case-sensitive): ");
            chosen = scanner.nextLine();
        } while (!taskNames.contains(chosen));

        return controller.getTaskByName(chosen);
    }

    /**
     * Prompts the user to input a time
     * @return the time the user inputted
     */
    private static LocalDateTime inputTime() {
        String format = "yyyy/MM/dd-HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your desired time in (" + format + ") (24 hour time)");
        String timeString = scanner.nextLine();
        return LocalDateTime.parse(timeString, formatter);
    }

    public static void main(String[] args) {
        boolean askForInput;
        do {
            System.out.println("\n###############");
            for(String key: queryMenu.keySet()) {
                System.out.println(key + "- " + queryMenu.get(key));
            }

            String input = getQueryInput();
            askForInput = handleQueryInput(input);
            // if the above line returns true, then ask for input again
        } while (askForInput);

        System.out.println("Bye!");
    }
}
