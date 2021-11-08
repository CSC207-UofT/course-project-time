package main.java.use_case;

import java.util.List;

public interface CalendarEventPresenter {

    void presentEvents(List<EventOutputDTO> eventOutputDTOs);

}
