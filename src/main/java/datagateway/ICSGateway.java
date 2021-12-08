package datagateway;

import datagateway.event.CalendarManager;
import datagateway.event.ObservableEventRepository;

public interface ICSGateway {
    void saveICS(CalendarManager events);
}
