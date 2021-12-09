package gui.viewmodel.calendar;

import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.Entry;
import datagateway.event.EventReader;
import entity.dates.TimeFrame;
import gui.utility.EventHelper;
import gui.viewmodel.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import services.eventcreation.CalendarEventCreationBoundary;
import services.eventpresentation.CalendarEventRequestBoundary;
import services.eventpresentation.EventInfo;
import services.updateentities.UpdateEventBoundary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class CalendarViewModel extends ViewModel {

    private final CalendarEventCreationBoundary eventAdder;
    private final UpdateEventBoundary eventUpdater;

    private final ObservableList<Entry<String>> entryList;
    private final Map<String, Long> entryToEventIdMapping;

    // booleans to indicate if the event given by the repositories
    // was originated from the current view model.
    private boolean eventUpdatedFromView;
    private boolean eventCreatedFromView;

    public CalendarViewModel(CalendarEventCreationBoundary eventAdder,
                             CalendarEventRequestBoundary eventGetter,
                             UpdateEventBoundary eventUpdater) {
        this.eventAdder = eventAdder;
        this.eventUpdater = eventUpdater;

        this.entryList = FXCollections.observableArrayList(new ArrayList<>());
        this.entryToEventIdMapping = new HashMap<>();
        initializeIdMappingAndEntryList(eventGetter.getEvents());

        this.entryList.addListener((ListChangeListener<Entry<String>>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    Entry<String> entry = c.getAddedSubList().get(0);
                    onCreation(entry);
                }
            }
        });
    }

    /**
     * Handles changes to the entryList when an entry is added.
     * If event was created from the view, pass it to be stored in the database.
     * Else, ignore it as the handleCreation method called by the Observable Repository will handle it.
     * @param entry the entry that has been added
     */
    private void onCreation(Entry<String> entry) {
        if (eventCreatedFromView) {
            eventAdder.addEvent(EventHelper.entryToCalendarEventModel(entry));
        }
    }


    /**
     * Initializes the mapping between entries id from the view and event id from the program.
     * @param infoList the list of event info received from the program
     */
    private void initializeIdMappingAndEntryList(List<EventInfo> infoList) {
        for (EventInfo eventInfo : infoList) {
            initializeEventInfo(eventInfo);
        }
    }

    private void initializeEventInfo(EventInfo eventInfo) {
        for (TimeFrame tr : EventHelper.getTimesFromStaticRange(eventInfo::getDatesBetween)) {
            Entry<String> entry = buildEntry(eventInfo::getName, tr.startTime, tr.startTime.plus(tr.duration));
            entryToEventIdMapping.put(entry.getId(), eventInfo.getId());
            entryList.add(entry);
        }
    }

    private void initializeEventReader(EventReader eventReader) {
        for (TimeFrame tr : EventHelper.getTimesFromStaticRange(eventReader::getDatesBetween)) {
            Entry<String> entry = buildEntry(eventReader::getName, tr.startTime, tr.startTime.plus(tr.duration));
            entryToEventIdMapping.put(entry.getId(), eventReader.getId());
            entryList.add(entry);
        }
    }

    private Entry<String> buildEntry(Supplier<String> eventNameSupplier, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Entry<String> entry = new Entry<>(eventNameSupplier.get());
        entry.changeStartTime(startDateTime.toLocalTime());
        entry.changeEndTime(endDateTime.toLocalTime());
        entry.changeStartDate(startDateTime.toLocalDate());
        entry.changeStartDate(endDateTime.toLocalDate());
        return entry;
    }

    public ObservableList<Entry<String>> getEntryList() {
        return this.entryList;
    }

    /**
     * Called by the observable repository.
     * If the event creation was originated from the current view model, add the entry
     * to event mapping.
     * Else, create a new entry and add the entry to event mapping.
     * @param eventReader data of new event created
     */
    public void handleCreation(EventReader eventReader) {
        if (!eventCreatedFromView) {
            initializeEventReader(eventReader);
        } else {
            // find the entry with no mapping and add mapping
            for (Entry<String> entry : entryList) {
                if (!entryToEventIdMapping.containsKey(entry.getId())) {
                    entryToEventIdMapping.put(entry.getId(), eventReader.getId());
                }
            }
            // set it to false when the process of event creation is over
            eventCreatedFromView = false;
        }
    }

    /**
     * Called by the observable repository.
     * If the event update was originated from the current view model, nothing is done.
     * Else, finds and updates the corresponding entry.
     * @param eventReader data of new event created
     */
    public void handleUpdate(EventReader eventReader) {
        if (!eventUpdatedFromView) {
            this.updateEntry(eventReader);
        } else {
            eventUpdatedFromView = false;
        }
    }

    /**
     * Called by the view when an event is updated. Propagates update to the repository
     * @param event the (javafx) event that triggered this call
     */
    public void updateEventFromView(CalendarEvent event) {
        eventUpdatedFromView = true;

        Entry<?> updatedEntry = event.getEntry();
        long correspondingEventId = entryToEventIdMapping.get(updatedEntry.getId());
        if (event.getEventType() == CalendarEvent.ENTRY_TITLE_CHANGED) {
            eventUpdater.updateName(correspondingEventId, updatedEntry.getTitle());
        }
        if (event.getEventType() == CalendarEvent.ENTRY_INTERVAL_CHANGED) {
            eventUpdater.updateDuration(correspondingEventId, updatedEntry.getDuration());
        }
    }

    /**
     * Handles event creation from view
     * @param newEntry the new entry creation in the view
     */
    public void addEventFromView(Entry<String> newEntry) {
        eventCreatedFromView = true;
        entryList.add(newEntry);
    }

    /**
     * Finds the corresponding entry to the eventReader and updates the information of the entry.
     * The entry is guaranteed to exist in entryToEventIdMapping and entryList.
     * @param eventReader the reader that holds relevant information that corresponds to an entry
     */
    private void updateEntry(EventReader eventReader) {
        Set<TimeFrame> intervals = EventHelper.getTimesFromStaticRange(eventReader::getDatesBetween);
        entryList.removeIf(e -> intervals.stream().anyMatch(i -> i.startTime.equals(e.getStartAsLocalDateTime())));
    }

}
