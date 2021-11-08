package main.java.interface_adapters;

import main.java.use_case.CalendarEventPresenter;
import main.java.use_case.EventOutputDTO;

import java.util.List;

public class ConsoleEventPresenter implements CalendarEventPresenter {

    @Override
    public void presentEvents(List<EventOutputDTO> eventOutputDTOs) {
        if (eventOutputDTOs.size() == 0) {
            System.out.println("No events have been created");
        }
        for (EventOutputDTO ei : eventOutputDTOs) {

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
