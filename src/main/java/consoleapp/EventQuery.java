package consoleapp;

import services.eventpresentation.EventInfo;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        eventQueryMenu.put("2", "Edit Event Start Time");
        eventQueryMenu.put("3", "Edit Event End Time");
        eventQueryMenu.put("4", "Add Tag");
        eventQueryMenu.put("5", "Remove Tag");
        eventQueryMenu.put("6", "Mark Event Completed");
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
                scanner = new Scanner(System.in);
                System.out.print("Enter new Event Start Time: ");
                String newStartTime;
                newStartTime = scanner.nextLine();
                controller.updateEventStartTime(eventId, LocalTime.parse(newStartTime));
                System.out.println("Event Start Time Updated");
                break;
            case "3":
                eventId = event.getId();
                scanner = new Scanner(System.in);
                System.out.print("Enter new Event End Time: ");
                String newEndTime;
                newEndTime = scanner.nextLine();
                controller.updateEventEndTime(eventId, LocalTime.parse(newEndTime));
                System.out.println("Event End Time Updated");
                break;
            case "4":
                eventId = event.getId();
                scanner = new Scanner(System.in);
                System.out.print("Enter tag to add: ");
                String addTag;
                addTag = scanner.nextLine();
                controller.addTag(eventId, addTag);
                System.out.println("Tag Added");
                break;
            case "5":
                eventId = event.getId();
                scanner = new Scanner(System.in);
                System.out.print("Enter tag to remove: ");
                String removeTag;
                removeTag = scanner.nextLine();
                controller.removeTag(eventId, removeTag);
                System.out.println("Tag Removed");
                break;
            case "6":
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
}
