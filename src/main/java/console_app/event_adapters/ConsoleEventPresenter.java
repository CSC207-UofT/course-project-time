package main.java.console_app.event_adapters;

import main.java.console_app.ApplicationDriver;
import main.java.services.event_presentation.CalendarEventPresenter;
import main.java.services.event_presentation.EventInfo;

import java.util.ArrayList;
import java.util.List;

public class ConsoleEventPresenter implements CalendarEventPresenter {
    private final ApplicationDriver applicationDriver;

    public ConsoleEventPresenter(ApplicationDriver applicationDriver) {
        this.applicationDriver = applicationDriver;
    }

    @Override
    public void presentEvents(List<EventInfo> eventInfos) {
        List<String> eventFormattedInfo = new ArrayList<>();
        if (eventInfos.size() == 0) {
            System.out.println("No events have been created");
        }
        for (EventInfo ei : eventInfos) {

            String name = ei.getName();
            String startTime = ei.getStartTime().toString();
            String endTime = ei.getEndTime().toString();
            String tags = ei.getTags().toString();
            String dates = ei.getDates().toString();

            String output = "Event: " + name + ", "
                    + "start time = " + startTime + ", "
                    + "end time = " + endTime + ", "
                    + "tags = " + tags + ", "
                    + "dates = " + dates;
            eventFormattedInfo.add(output);
        }
        this.applicationDriver.printEvents(eventFormattedInfo);
    }
}
