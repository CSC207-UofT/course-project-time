package main.java.console_app.rescheduling_adapters;

import main.java.services.event_presentation.EventInfo;

public interface ReschedulerAutoController {
    boolean rescheduleTime(EventInfo eventInfo);
}
