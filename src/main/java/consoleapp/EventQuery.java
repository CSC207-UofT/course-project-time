package consoleapp;

import services.eventpresentation.EventInfo;
import services.strategybuilding.DatesForm;
import services.strategybuilding.MultipleRuleFormBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class EventQuery {
    private static final Map<String, String> eventQueryMenu = createEventQuery();
    private final consoleapp.MainController controller;

    public EventQuery(MainController controller){
        this.controller = controller;
    }

    private static Map<String, String> createEventQuery(){
        Map<String, String> eventQueryMenu = new HashMap<>();
        eventQueryMenu.put("0", "Quit");
        eventQueryMenu.put("1", "Edit Event Name");
        eventQueryMenu.put("2", "Edit Event Date Strategy");
        eventQueryMenu.put("3", "Add Tag");
        eventQueryMenu.put("4", "Remove Tag");
        eventQueryMenu.put("5", "Mark Event Completed");
        return eventQueryMenu;
    }

    /**
     * prompts user for an input until a valid input is received
     * @return a valid input from the user
     */
    private static String getEventQueryInput() {
        System.out.print("Please enter your input: ");
        Scanner editEventInput = new Scanner(System.in);
        String input = editEventInput.nextLine();

        while(!eventQueryMenu.containsKey(input)) {
            System.out.println("\" " + input +  " \" is not a valid input, try again.");
            input = editEventInput.nextLine();
        }

        return input;
    }

    /**
     * Handle the input based on the mapping queryMenu.
     * @param input string that correspond to a key in testQueryMenu
     * @return whether to continue asking for inputs
     */
    private boolean handleEventQueryInput(String input, EventInfo event) {
        System.out.println("###############");
        switch (input) {
            case "0":
                return false;
            case "1":
                long eventId = event.getId();
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new Event name: ");
                String newName;
                newName = scanner.nextLine();
                controller.updateEventName(eventId, newName);
                System.out.println("Event Name Updated");
                break;
            case "2":
                eventId = event.getId();
                DatesForm form = createForm();
                controller.updateEventDateStrategy(eventId, form);
                System.out.println("Event Start Time Updated");
                break;
            case "3":
                eventId = event.getId();
                scanner = new Scanner(System.in);
                System.out.print("Enter tag to add: ");
                String addTag;
                addTag = scanner.nextLine();
                controller.addTag(eventId, addTag);
                System.out.println("Tag Added");
                break;
            case "4":
                eventId = event.getId();
                scanner = new Scanner(System.in);
                System.out.print("Enter tag to remove: ");
                String removeTag;
                removeTag = scanner.nextLine();
                controller.removeTag(eventId, removeTag);
                System.out.println("Tag Removed");
                break;
            case "5":
                eventId = event.getId();
                controller.completeEvent(eventId);
                System.out.println("Event is marked completed");
                break;
            default:
                break;
        }
        return true;
    }

    public void run(EventInfo event){
        boolean askForInput;
        do {
            System.out.println("\n###############");
            for(String key: eventQueryMenu.keySet()) {
                System.out.println(key + "- " + eventQueryMenu.get(key));
            }

            String input = getEventQueryInput();
            askForInput = handleEventQueryInput(input, event);
        } while (askForInput);
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
}
