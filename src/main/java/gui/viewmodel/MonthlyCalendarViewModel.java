package gui.viewmodel;

import com.calendarfx.model.Entry;
import datagateway.Observer;
import datagateway.event.EventReader;
import gui.utility.EventHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.eventcreation.CalendarEventCreationBoundary;
import services.eventcreation.EventSaver;
import services.eventpresentation.CalendarEventRequestBoundary;
import services.eventpresentation.EventInfo;
import services.updateentities.UpdateEventBoundary;

import java.util.ArrayList;
import java.util.List;

public class MonthlyCalendarViewModel extends ViewModel {

    private final CalendarEventCreationBoundary eventAdder;
    private final CalendarEventRequestBoundary eventGetter;
    private final UpdateEventBoundary eventUpdater;
    private final EventSaver eventSaver;

    private final ObservableList<Entry<String>> entryList;

    public MonthlyCalendarViewModel(CalendarEventCreationBoundary eventAdder,
                                    CalendarEventRequestBoundary eventGetter,
                                    UpdateEventBoundary eventUpdater,
                                    EventSaver eventSaver) {
        this.eventAdder = eventAdder;
        this.eventGetter = eventGetter;
        this.eventUpdater = eventUpdater;
        this.eventSaver = eventSaver;
        List<EventInfo> eventInfoList = new ArrayList<>(); // TODO: change this to be getting events from EventGetter
        this.entryList = FXCollections.observableArrayList(EventHelper.eventInfoToEntry(eventInfoList));
    }

    public ObservableList<Entry<String>> getEntryList() {
        return this.entryList;
    }


}
