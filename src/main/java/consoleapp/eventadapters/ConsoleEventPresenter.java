package consoleapp.eventadapters;

import consoleapp.ApplicationDriver;
import services.eventpresentation.CalendarEventPresenter;
import services.eventpresentation.EventInfo;

import java.util.ArrayList;
import java.util.List;

public class ConsoleEventPresenter implements CalendarEventPresenter {
    private final ApplicationDriver applicationDriver;

    public ConsoleEventPresenter(ApplicationDriver applicationDriver) {
        this.applicationDriver = applicationDriver;
    }

    /**
     * Given the event information in a DTO, present all events
     * by printing them on the console.
     * @param eventInfos list of information of event
     */
    @Override
    public void presentAllEvents(List<EventInfo> eventInfos) {
        List<String> eventFormattedInfo = new ArrayList<>();
        for (EventInfo ei : eventInfos) {

            String name = ei.getName();
            String tags = ei.getTags().toString();
            String when = ei.getWhen();
            String duration = ei.getDuration().toString();
            boolean completed = ei.getCompleted();

            String output = "Event: " + name + ", "
                    + "when = " + when + ", "
                    + "duration = " + duration + ", "
                    + "tags = " + tags + ", "
                    + "completed = " + completed;
            eventFormattedInfo.add(output);
        }
        applicationDriver.printEvents(eventFormattedInfo);
    }
}
