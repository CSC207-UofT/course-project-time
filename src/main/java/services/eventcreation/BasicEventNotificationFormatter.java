package services.eventcreation;

import java.time.LocalDate;
import java.time.LocalTime;

public class BasicEventNotificationFormatter implements EventNotificationFormatter{
    /***
     * Generate an event notification message with the format
     *          You have an upcoming event <event name> from <start time> to <end time> on <date>.
     * @param eventName the name of the event
     * @param date the date of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @return the event notification message
     */
    @Override
    public String formatMessage(String eventName, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return "You have an upcoming event " + eventName +
                " from " + startTime.toString() +
                " to " + endTime.toString() +
                " on " + date.toString();
    }
}
