package main.java;

import main.java.controllers.ConsoleController;

import java.util.*;

public class ApplicationDriver {

    static ConsoleController controller = new ConsoleController();

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
     */
    private static void handleQueryInput(String input) {
        switch (input) {
            case "0":
                System.out.println("Bye!");
                return;
            case "1":
                ...
                break;
            case "2":
                ...
                break;
            case "3":
                ...
                break;
            case "4":
                ...
                break;
            case "5":
                ...
                break;
            case "6":
                ...
                break;
        }
    }

    public static void main(String[] args)
    {
        menuQuery();
    }
}
