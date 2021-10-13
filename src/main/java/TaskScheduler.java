package main.java;

import java.util.*;

public class TaskScheduler {

    static Calendar calendar;

    private static int menuQuery()
    {

        Map<String, String> input_messages = new HashMap<>();
        input_messages.put("0", "View my events");
        input_messages.put("1", "View all tasks");
        input_messages.put("2", "Create a new task");
        input_messages.put("3", "Schedule a time for my tasks");
        input_messages.put("Q", "Quit");


        for(String key: input_messages.keySet())
        {
            System.out.println(key + "- " + input_messages.get(key));
        }

        String input = getResult(input_messages.keySet());
        return 0;

    }

    private static String getResult(Collection<String> options)
    {
        Scanner command_input = new Scanner(System.in);
        String input = command_input.nextLine();

        while(!options.contains(input))
        {
            System.out.println("\" " + input +  " \" is not a valid input, try again");
            input = command_input.nextLine();
        }

        return input;
    }

    public static void main(String[] args)
    {
        menuQuery();
    }
}
