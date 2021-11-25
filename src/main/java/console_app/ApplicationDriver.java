package console_app;

import services.event_presentation.EventInfo;
import services.task_presentation.TaskInfo;

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
        queryMenu.put("7", "Mark a task as completed");
        queryMenu.put("8", "Mark an event as completed");
        queryMenu.put("9", "Save my Data");
        queryMenu.put("10", "Pomodoro timer");
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
                controller.presentAllEvents();
                break;
            case "2":
                controller.presentAllTasks();
                break;
            case "3":
                handleCreateTask();
                System.out.println("Task created");
                break;
            case "4":
                    handleCreateEvent();
                    System.out.println("Event created");
                break;
            case "5":
                Map<Integer, Long> positionToIdMapping = controller.presentAllTasksForUserSelection();
                if (positionToIdMapping.size() != 0) {
                    TaskInfo taskInfo = chooseTask(positionToIdMapping);
                    controller.suggestTimeToUser(taskInfo);
                    System.out.println("Event created from task");
                }
                break;
            case "6":
                positionToIdMapping = controller.presentAllTasksForUserSelection();
                if (positionToIdMapping.size() != 0) {
                    TaskInfo taskManual = chooseTask(positionToIdMapping);
                    LocalDateTime userSuggestedTime;
                    boolean timeAvailable;

                    do {
                        userSuggestedTime = inputTime();
                        timeAvailable = controller.checkUserSuggestedTime(taskManual, userSuggestedTime);
                        if (!timeAvailable) {
                            System.out.println("Time not available, please retry.");
                        }
                    } while (!timeAvailable);

                    controller.createEvent(taskManual.getName(), userSuggestedTime.toLocalTime(),
                            userSuggestedTime.toLocalTime().plus(taskManual.getDuration()), new HashSet<>(),
                            userSuggestedTime.toLocalDate(), taskManual.getNotificationTimeInAdvance());
                    System.out.println("Event created from task");

                }
                break;
            case "7":
                positionToIdMapping = controller.presentAllTasksForUserSelection();
                TaskInfo completedTask = chooseTask(positionToIdMapping);
                long taskId = completedTask.getId();
                controller.completeTask(taskId);
                System.out.println("Task completed");
                break;
            case "8":
                controller.presentAllEvents();
                EventInfo completedEvent = chooseEvent();
                controller.completeEvent(completedEvent.getId());

                System.out.println("Event completed");

                break;
            case "9":
                controller.saveData();
                break;
            case "10":
                int[] intervals = inputPomodoroTime();
                System.out.println("Timer started!");
                System.out.println("Input \"c\" to end pomodoro timer");
                controller.createAndEndTimer(intervals[0], intervals[1]);
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
     */
    private void handleCreateEvent() {
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

        System.out.print("Enter notification time duration in advance (in minutes): ");
        int notificationResponse = Integer.parseInt(input.nextLine());
        Duration notificationTimeInAdvance = Duration.ofMinutes(notificationResponse);

        this.controller.createEvent(eventName, eventStartTime, eventEndTime, eventTags, eventDate, notificationTimeInAdvance);
    }

    /**
     * prompts the user for inputs needed to create a new task
     * and passes the information to a controller to create the task
     * in the database
     */
    private void handleCreateTask() {
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

        this.controller.createTask(taskName, taskDuration, taskDeadline, taskSubtasks);
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
        if (eventInfo.size() == 0) {
            System.out.println("No events have been created");
        }
        for (String event : eventInfo) {
            System.out.println(event);
        }

    }

    /**
     * Prompts the user to choose an Event from the list of Events
     * @return the chosen Event
     */
    private EventInfo chooseEvent() {
        List<HashMap<String, String>> allEventsData = controller.getEvents();
        List<String> eventNames = new ArrayList<>();
        for (HashMap<String, String> event: allEventsData ) {
            eventNames.add(event.get("name"));
        }
        Scanner scanner = new Scanner(System.in);
        String chosen;
        // todo in the future lift the assumption where names are unique
        do {
            System.out.print("Please choose an Event by typing its name (case-sensitive): ");
            chosen = scanner.nextLine();
        } while (!eventNames.contains(chosen));

        return controller.getEventByName(chosen);
    }

    /**
     * Prompts the user to choose a task among the list of tasks
     * @param mapping   the mapping of position of task to id
     * @return the chosen task with its information as a TaskInfo instance
     */
    private TaskInfo chooseTask(Map<Integer, Long> mapping) {
        Scanner scanner = new Scanner(System.in);
        String chosen;

        do {
            System.out.print("Please choose a task (input the corresponding number): ");
            chosen = scanner.nextLine();
        } while (!(Integer.parseInt(chosen) <= mapping.size()));

        return controller.getTaskById(mapping.get(Integer.parseInt(chosen)));
    }

    /**
     * Prompts the user to input a time
     * @return the time the user inputted
     */
    private static LocalDateTime inputTime() {
        String format = "yyyy/MM/dd-HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your desired time in (" + format + ") (24 hour time): ");
        String timeString = scanner.nextLine();
        return LocalDateTime.parse(timeString, formatter);
    }

    /**
     * Prompts users for their desired work and break intervals
     * @return the time intervals the user inputted
     */
    private static int[] inputPomodoroTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your desired work and break intervals in (work,break) (in minutes) (no spaces) " +
                "or 0 for default intervals (25 mins work, 5 mins break): ");

        //todo handle exceptions when user doesn't input in desired format
        String intervals = scanner.nextLine();
        if (isInt(intervals)) {
            if (Integer.parseInt(intervals) == 0) {
                return new int[] {25, 5};
            }
            else {
                System.out.print("Interval not accepted, please retry.");
                return inputPomodoroTime();
            }
        }
        else {
            String[] intervalsSplitString =  intervals.split(",");
            int[] intervalsSplit = new int[2];
            intervalsSplit[0] = Integer.parseInt(intervalsSplitString[0]);
            intervalsSplit[1] = Integer.parseInt(intervalsSplitString[1]);
            return intervalsSplit;
        }
    }

    private static boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }
        catch(NumberFormatException numberFormatException) {
            return false;
        }
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
