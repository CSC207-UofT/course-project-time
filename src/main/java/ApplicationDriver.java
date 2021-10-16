package main.java;

import main.java.controllers.MainController;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ApplicationDriver {

    static MainController controller = new MainController();

    private static final Map<String, String> queryMenu = createdQueryMap();

    /**
     * Creates a mapping of query and input by user
     * @return a map storing the mapping of user input to the query
     */
    private static Map<String, String> createdQueryMap() {
        Map<String, String> queryMenu = new HashMap<>();
        queryMenu.put("0", "Quit");
        queryMenu.put("1", "View my events");
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
            System.out.println("\" " + input +  " \" is not a valid input, try again");
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
        switch (input) {
            case "0":
                return false;
            case "1":
                List<HashMap<String, String>> allEventsData = controller.getEvents();
                for (HashMap<String, String> eventData : allEventsData) {
                    String output = "Event: " + "event name = "
                                        + eventData.get("name") + "\n"
                                        + "start time = " + eventData.get("start") + "\n"
                                        + "end time = " + eventData.get("end") + "\n";
                    System.out.println(output);
                }
                break;
            case "2":
                List<HashMap<String, String>> allTasks = controller.getTasks();
                for (HashMap<String, String> taskData : allTasks) {
                    String output = "Task: " + taskData.get("name");
                    System.out.println(output);
                }
                break;
            case "3":
                handleCreateTask();
                break;
            case "4":
                boolean success = handleCreateEvent();
                if (success) {
                    System.out.println("Event created");
                } else {
                    System.out.println("Failed to create event");
                }
                break;
            case "5":
                System.out.println("Not yet implemented");
                break;
            case "6":
                System.out.println("Not yet implemented");
                break;
            default:
                break;
        }
        // if this line is reached, then we did not receive a 'quit' input
        return true;
    }

    /**
     * prints the time to the user and receives user input
     * @param time an available time for the event
     * @return  a boolean indicating if the user agrees with the suggested time
     */
    private boolean confirmTimeWithUser(LocalDateTime time) {
        boolean scheduled;
        boolean run = true;
        int response = 0;

        Scanner scanner = new Scanner(System.in);

        do {
            try {
                System.out.println("Suggested time: " + time);
                System.out.println("Type 0 for yes, 1 for no");
                response = scanner.nextInt();
                run = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again");
            }

        }while(run);

        scheduled = response == 0;
        scanner.close();
        return scheduled;
    }

    private static boolean handleCreateEvent() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter event name: ");
        String eventName = input.nextLine();
        boolean run = true;

        String timeFormat = "HH:mm";
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat);

        LocalTime eventStartTime = null;
        LocalTime eventEndTime = null;
        LocalDate eventDate = null;
        HashSet<String> eventTags = null;

        do {
            try {
                System.out.print("Enter start time for event in (" + timeFormat + ") (24 hour time): ");
                String startTimeResponse = input.next();
                eventStartTime = LocalTime.parse(startTimeResponse, timeFormatter);

                run = false;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid input, please try again");
            }
        }while(run);

        run = true;
        do {
            try {
                System.out.print("Enter end time for event in (" + timeFormat + ") (24 hour time): ");
                String endTimeResponse = input.next();
                eventEndTime = LocalTime.parse(endTimeResponse, timeFormatter);

                run = false;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid input, please try again");
            }
        }while(run);


        run = true;
        do {
            try {
                String dateFormat = "yyyy-MM-dd";
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

                System.out.print("Enter date for event in (" + dateFormat + ")");
                String dateResponse = input.next();
                eventDate = LocalDate.parse(dateResponse, dateFormatter);

                run = false;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid input, please try again");
            }
        }while(run);


        run = true;
        do {
            try {
                System.out.print("Enter tags for event, separated by space: ");
                String tagResponse = input.nextLine();
                String[] tagArray = tagResponse.split(" ");
                eventTags = new HashSet<>(Arrays.asList(tagArray));

                run = false;
            } catch (Exception e) {
                System.out.println("Invalid input, please try again");
            }
        }while(run);

        return controller.createEvent(eventName, eventStartTime, eventEndTime, eventTags, eventDate);
    }

    private static boolean handleCreateTask() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter task name: ");
        String taskName = input.nextLine();
        Duration taskDuration = null;
        LocalDateTime taskDeadline = null;

        boolean run = true;

        do {
            try{
                System.out.print("Enter approximate duration needed in minutes");
                int durationResponse = Integer.parseInt(input.nextLine());
                taskDuration = Duration.ofMinutes(durationResponse);
                run = false;
            } catch (Exception e) {
                System.out.println("Invalid input, please try again");
            }
        }while(run);

        run = true;
        do {
            try{
                String format = "yyyy/MM/dd-HH:mm";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                System.out.println("Input deadline for task in (" + format + ") (24 hour time):");
                String deadlineResponse = input.nextLine();
                taskDeadline = LocalDateTime.parse(deadlineResponse, formatter);
                run = false;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid input, please try again");
            }
        }while(run);


        System.out.print("Enter any subtasks for task, separated by a space: ");
        String subtaskResponse = input.nextLine();

        String[] subtaskArray = subtaskResponse.split(" ");
        ArrayList<String> taskSubtasks = new ArrayList<>(Arrays.asList(subtaskArray));


        return controller.createTask(taskName, taskDuration, taskDeadline, taskSubtasks);
    }

    public static void main(String[] args) {
        for(String key: queryMenu.keySet()) {
            System.out.println(key + "- " + queryMenu.get(key));
        }

        boolean askForInput;
        do {
            String input = getQueryInput();
            askForInput = handleQueryInput(input);
            // if the above line returns true, then ask for input again
        } while (askForInput);

        System.out.println("Bye!");
    }
}
