package main.java;

import java.util.ArrayList;
import java.util.List;

public class CalendarBuilder {
    Calendar calendar;
    public CalendarBuilder()
    {
        Event[] eventList = {};
        calendar = new Calendar("Calendar", eventList );
    }

    public Calendar getCalendar()
    {
        return this.calendar;
    }
}
