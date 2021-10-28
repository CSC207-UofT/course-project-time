package main.java.interface_adapters;

import main.java.use_case.CalendarEventPresenter;
import main.java.use_case.EventInfo;

import java.util.List;

public class EventPresenter implements CalendarEventPresenter {

    @Override
    public void presentEvents(List<EventInfo> eventInfos) {
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
            System.out.println(output);
        }
    }
}
