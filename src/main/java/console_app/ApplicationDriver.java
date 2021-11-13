package main.java.console_app;

import main.java.services.task_presentation.TaskInfo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


public class ApplicationDriver {

    private final MainController controller;

    private static final Map<String, String> queryMenu = createdQueryMap();

    public ApplicationDriver() {
        this.controller = new MainController(this);
    }

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
        Scanner commandInput = new Scanner(System.in);
        String input = commandInput.nextLine();

        while(!queryMenu.containsKey(input)) {
            System.out.println("\" " + input +  " \" is not a valid input, try again.");
            input = commandInput.nextLine();
        }

        return input;
    }

    /**
     * Handle the input based on the mapping queryMenu.
     * @param input string that correspond to a key in queryMenu
     * @return whether to continue asking for inputs
     */
    private boolean handleQueryInput(String input) {
        System.out.println("###############");
        switch (input) {
            case "0":
                return false;
            case "1":
                this.controller.getEvents();
                break;
            case "2":
                this.controller.getTasks();
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
                // TODO: print tasks for user to select
                TaskInfo task = chooseTask();
                success = this.controller.suggestTimeToUser(task);
                if (success) {
                    System.out.println("Event created from task");
                } else {
                    // todo use exceptions in the future to track reason of failure
                    System.out.println("Failed to create event from task");
                }
                break;
            case "6":
                // TODO: print tasks for user to select
                TaskInfo taskManual = chooseTask();
                LocalDateTime userSuggestedTime;
                boolean timeAvailable;

                do {
                    userSuggestedTime = inputTime();
                    timeAvailable = this.controller.checkUserSuggestedTime(taskManual, userSuggestedTime);
                    if (!timeAvailable) {
                        System.out.println("Time not available, please retry.");
                    }
                } while (!timeAvailable);

                success = this.controller.createEvent(taskManual.getName(), userSuggestedTime.toLocalTime(),
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
    private boolean handleCreateEvent() {
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

        return this.controller.createEvent(eventName, eventStartTime, eventEndTime, eventTags, eventDate);
    }

    /**
     * prompts the user for inputs needed to create a new task
     * and passes the information to a controller to create the task
     * in the database
     * @return whether the task has been created
     */
    private boolean handleCreateTask() {
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

        return this.controller.createTask(taskName, taskDuration, taskDeadline, taskSubtasks);
    }

    /**
     * Displays tasks on the console
     * @param taskInfo the list of tasks' information to be displayed
     */
    public void printTasks(List<String> taskInfo) {
        if (taskInfo.size() == 0) {
            System.out.println("No tasks have been created");
        }
        for (String task : taskInfo) {
            System.out.println(task);
        }
    }

    /**
     * Displays events on the console
     * @param eventInfo the list of events' information to be displayed
     */
    public void printEvents(List<String> eventInfo) {
        for (String event : eventInfo) {
            System.out.println(event);
        }

    }

    /**
     * Prompts the user to choose a Task among the list of Tasks
     * @return the chosen Task
     */
    private TaskInfo chooseTask() {
        this.controller.getTasks();
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

        return this.controller.getTaskByName(chosen);
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
        ApplicationDriver applicationDriver = new ApplicationDriver();

        boolean askForInput;
        do {
            System.out.println("\n###############");
            for(String key: queryMenu.keySet()) {
                System.out.println(key + "- " + queryMenu.get(key));
            }

            String input = getQueryInput();
            askForInput = applicationDriver.handleQueryInput(input);
            // if the above line returns true, then ask for input again
        } while (askForInput);

        System.out.println("Bye!");
    }
}
