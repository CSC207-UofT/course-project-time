package consoleapp;

import services.eventpresentation.EventInfo;
import services.servicesfactory.BasicRepositoryFactory;
import services.servicesfactory.BasicServiceFactory;
import services.servicesfactory.RepositoryFactory;
import services.servicesfactory.ServicesFactory;
import services.strategybuilding.DatesForm;
import services.strategybuilding.MultipleRuleFormBuilder;
import services.taskpresentation.TaskInfo;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;


public class ApplicationDriver {

    private final MainController controller;

    private static final Map<String, String> queryMenu = createdQueryMap();
    public final TaskQuery taskQuery;
    public final EventQuery eventQuery;

    public ApplicationDriver() {
        RepositoryFactory repositoryFactory = new BasicRepositoryFactory();
        ServicesFactory serviceFactory = new BasicServiceFactory(repositoryFactory);
        ConsoleAppFactory consoleAppFactory = new ConsoleAppFactory(serviceFactory);
        this.controller = new MainController(this, consoleAppFactory);

        try {
            repositoryFactory.makeEventRepository().loadEvents("EventData.json");
            repositoryFactory.makeTaskRepository().loadTodo("TaskData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.taskQuery = new TaskQuery(controller);
        this.eventQuery = new EventQuery(controller);
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
        queryMenu.put("5", "Turn a task into an event");
        queryMenu.put("6", "Edit Task");
        queryMenu.put("7", "Edit Event");
        queryMenu.put("8", "Delete Task");
        queryMenu.put("9", "Delete Event");
        queryMenu.put("10", "Save my Data");
        queryMenu.put("11", "Pomodoro timer");
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
                    LocalDateTime suggestedTime = controller.getSuggestedTime(taskInfo.getDuration());
                    String format = "yyyy/MM/dd-HH:mm";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                    String formattedTime = suggestedTime.format(formatter);
                    System.out.println("Suggested time is: " + formattedTime);

                    LocalDateTime userSuggestedTime;
                    boolean timeAvailable;

                    do {
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Input your desired time in (" + format + ") (24 hour time) or 'y' to accept the suggested time: ");
                        String timeString = scanner.nextLine();
                        try {
                            userSuggestedTime = LocalDateTime.parse(timeString, formatter);
                        } catch (DateTimeParseException e) {
                            if (timeString.equalsIgnoreCase("y"))
                                userSuggestedTime = suggestedTime;
                            else
                                throw e;
                        }
                        timeAvailable = controller.checkUserSuggestedTime(taskInfo, userSuggestedTime);
                        if (!timeAvailable) {
                            System.out.println("Time not available, please retry.");
                        }
                    } while (!timeAvailable);

                    MultipleRuleFormBuilder formBuilder = new MultipleRuleFormBuilder();
                    formBuilder.addSingleOccurrence(userSuggestedTime);
                    DatesForm form = formBuilder.getForm();

                    controller.createEvent(taskInfo.getId(), form);
                    System.out.println("Event created from task");
                }
                break;
            case "6":
                positionToIdMapping = controller.presentAllTasksForUserSelection();
                TaskInfo task = chooseTask(positionToIdMapping);
                taskQuery.run(task);

                break;
            case "7":
                controller.presentAllEvents();
                EventInfo event = chooseEvent();
                eventQuery.run(event);

                break;
            case "8":
                positionToIdMapping = controller.presentAllTasksForUserSelection();
                TaskInfo chosenTask = chooseTask(positionToIdMapping);
                controller.deleteTask(chosenTask.getId());
                break;
            case "9":
                controller.presentAllEvents();
                System.out.println("Choose a task by name (case sensitive)");
                Scanner sc = new Scanner(System.in);
                String choice = sc.nextLine();
                EventInfo eventInfo = controller.getEventByName(choice);
                controller.deleteEvent(eventInfo.getId());
                break;
            case "10":
                controller.saveData();
                break;
            case "11":
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

        DatesForm form = createForm();

        String[] response;
        do {
            System.out.print("Enter how long the event will go on for (hours minutes) ");
            String startTimeResponse = input.nextLine(); // TODO exception handling
            response = startTimeResponse.split(" ");
        } while (response.length != 2);
        int hours = Integer.parseInt(response[0]);
        int minutes = Integer.parseInt(response[1]);

        Duration eventDuration = Duration.ofMinutes(hours * 60L + minutes);

        System.out.print("Enter tags for event, separated by space, or press enter if there are no tags: ");
        String tagResponse = input.nextLine(); // todo exception handling
        String[] tagArray = tagResponse.split(" ");
        HashSet<String> eventTags = new HashSet<>(Arrays.asList(tagArray));

        this.controller.createEvent(eventName, eventDuration, form, eventTags);
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

    private static LocalDateTime inputDateWithOptionalTime(LocalTime defaultTime) {
        String dateTimeFormat = "yyyy/MM/dd-HH:mm";
        String dateFormat = "yyyy/MM/dd";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Format: (" + dateTimeFormat + ") or " +
                "(" + dateFormat + ") defaulting to " + defaultTime.toString() + " (24 hour time): ");
        String timeString = scanner.nextLine();
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
            return LocalDateTime.parse(timeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
            return LocalDate.parse(timeString, dateFormatter).atTime(defaultTime);
        }
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


    private DatesForm createForm() {

        String[] daysOfWeek = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
        Set<String> daysSet = new HashSet<>(Arrays.asList(daysOfWeek));

        Scanner sc = new Scanner(System.in);
        String input;
        do {
            System.out.print("Input what day of the week you want the event to reoccur (full name like 'Monday'): ");
            input = sc.nextLine().toLowerCase();
        } while (!daysSet.contains(input));

        int index = 0;
        while (!daysOfWeek[index].equals(input))
            index++;

        DayOfWeek day = DayOfWeek.of(index + 1);

        System.out.print("When in the day do you want the event to reoccur (HH:mm)? ");
        Scanner scanner = new Scanner(System.in);
        String timeString = scanner.nextLine();
        LocalTime timeOfDay = LocalTime.parse(timeString);

        System.out.println("(Optional) Input the date from when this recurrence should start (for an event like every Monday from Jan 1) (enter nothing to not use)");
        LocalDateTime startTime;
        try {
             startTime = inputDateWithOptionalTime(LocalTime.MIDNIGHT);
        } catch (DateTimeParseException e) {
            startTime = null;
        }

        System.out.println("(Optional) Input the date from when this recurrence should end (for an event like Monday until Dec 31) (enter nothing to not use)");
        LocalDateTime endTime;
        try {
            endTime = inputDateWithOptionalTime(LocalTime.MIDNIGHT.minusMinutes(1));
        } catch (DateTimeParseException e) {
            endTime = null;
        }

        MultipleRuleFormBuilder formBuilder = new MultipleRuleFormBuilder();
        if (startTime != null && endTime != null)
            formBuilder.addWeeklyOccurrenceBetween(day, timeOfDay, startTime, endTime);
        else if (startTime != null)
            formBuilder.addWeeklyOccurrenceFrom(day, timeOfDay, startTime);
        else if (endTime != null)
            formBuilder.addWeeklyOccurrenceUntil(day, timeOfDay, endTime);
        else
            formBuilder.addWeeklyOccurrence(day, timeOfDay);

        return formBuilder.getForm();

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
