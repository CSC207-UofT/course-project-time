package datagateway;

import datagateway.event.CalendarManager;
import datagateway.event.ObservableEventRepository;

import java.time.LocalDateTime;

public interface ICSGateway {
    void saveICS(CalendarManager events, LocalDateTime from, LocalDateTime to);
}
