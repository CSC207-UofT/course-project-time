package services.eventcreation;

import datagateway.ICSGateway;
import datagateway.event.CalendarManager;
import datagateway.event.ObservableEventRepository;

public class ICSSaver {

    final ICSGateway gateway;
    final CalendarManager cal;
    public ICSSaver(ICSGateway gateway, CalendarManager cal) {
        this.gateway = gateway;
        this.cal= cal;
    }

    public void export() {
        this.gateway.saveICS(cal);
    }
}
