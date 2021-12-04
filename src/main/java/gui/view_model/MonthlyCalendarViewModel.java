package gui.view_model;

import com.calendarfx.model.Entry;
import data_gateway.event.EventReader;
import gui.model.EventModelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthlyCalendarViewModel extends ViewModel {

    private final EventModelManager manager;

    private final ObservableList<Entry<String>> entryList;

    public MonthlyCalendarViewModel(EventModelManager manager) {
        this.manager = manager;

        List<Entry<String>> newList = new ArrayList<>();

        List<EventReader> eventReaderList = this.manager.getEvents();
        for (EventReader er : eventReaderList) {
            newList.add(eventReaderToEntry(er));
        }

        this.entryList = FXCollections.observableArrayList(newList);


    }

    public ObservableList<Entry<String>> getEntryList() {
        System.out.println(this.entryList);
        return this.entryList;
    }

    private Entry<String> eventReaderToEntry(EventReader eventReader) {
        Entry<String> entry = new Entry<>(eventReader.getName());
        entry.changeStartTime(eventReader.getStartTime());
        entry.changeEndTime(eventReader.getEndTime());
        LocalDate date = LocalDate.of(2021, 12, 8);
        entry.changeStartDate(date);
        entry.changeEndDate(date);
        return entry;
    }
}
