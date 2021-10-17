package main.java.use_case;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TaskToEventConsoleController implements TaskEventAutoController, TaskEventManualController {
    @Override
    public boolean confirmTimeWithUser(LocalDateTime time) {
        boolean scheduled;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Suggested time: " + time);
        System.out.println("Type 0 for yes, 1 for no");
        int response = scanner.nextInt(); //TODO exception handling
        System.out.println("Type 0 for yes, 1 for no");
        response = scanner.nextInt();
        scheduled = response == 0;
        scanner.close();
        return scheduled;
    }

    @Override
    public LocalDateTime getUserSuggestedTime() {
        String format = "yyyy-MM-dd HH:mm";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input time for task in (" + format + ") (24 hour time):");
        String response = scanner.next(); //TODO exception handling
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.parse(response, formatter);
        scanner.close();
        return dateTime;
    }
}
