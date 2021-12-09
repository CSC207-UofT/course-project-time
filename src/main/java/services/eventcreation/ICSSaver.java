package services.eventcreation;

import datagateway.ICSGateway;
import datagateway.event.CalendarManager;
import datagateway.event.ObservableEventRepository;

import java.time.LocalDateTime;

public class ICSSaver {

    final ICSGateway gateway;
    final CalendarManager cal;
    public ICSSaver(ICSGateway gateway, CalendarManager cal) {
        this.gateway = gateway;
        this.cal= cal;
    }

    public void export(LocalDateTime from, LocalDateTime to) {
        this.gateway.saveICS(cal, from, to);
    }
}
