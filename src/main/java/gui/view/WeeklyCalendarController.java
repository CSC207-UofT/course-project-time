package gui.view;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.DateControl;
import com.calendarfx.view.page.WeekPage;
import com.jfoenix.controls.JFXDrawer;
import gui.utility.NavigationHelper;
import gui.view_model.ViewModel;
import gui.view_model.WeeklyCalendarViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WeeklyCalendarController implements Initializable, ViewModelBindingController {

    private WeeklyCalendarViewModel viewModel;

    @FXML
    private WeekPage weekPage;

    private final ObservableList<Entry<String>> entryList = FXCollections.observableArrayList();

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private ComboBox<String> calendarType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);

        calendarType.getItems().addAll("Month", "Week", "Day");
    }

    @FXML
    void calendarTypeSelected(ActionEvent event) throws IOException {
        String selected = calendarType.getValue();
        NavigationHelper.switchCalendarPageType(event, selected);
    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (WeeklyCalendarViewModel) viewModel;
        Bindings.bindContentBidirectional(this.entryList, this.viewModel.getEntryList());

        Calendar calendar = new Calendar("all");
        for (Entry<String> entry : this.entryList) {
            entry.setCalendar(calendar);
        }
        CalendarSource source = new CalendarSource();
        source.getCalendars().add(calendar);
        weekPage.getCalendarSources().add(source);

        EventHandler<CalendarEvent> updateEntryHandler = this::handleUpdateEntry;
        calendar.addEventHandler(updateEntryHandler);

        Callback<DateControl.CreateEntryParameter, Entry<?>> defaultFactory = weekPage.getEntryFactory();
        weekPage.setEntryFactory(new EventCreationHandler(this.entryList, defaultFactory, (WeeklyCalendarViewModel) viewModel));
    }

    private void handleUpdateEntry(CalendarEvent event) {
        this.viewModel.updateEventFromView(event);
    }

    private record EventCreationHandler(
            ObservableList<Entry<String>> entryList,
            Callback<DateControl.CreateEntryParameter, Entry<?>> defaultFactory,
            WeeklyCalendarViewModel viewModel)
            implements Callback<DateControl.CreateEntryParameter, Entry<?>> {

        @Override
        public Entry<?> call(DateControl.CreateEntryParameter param) {
            Entry<?> defaultEntry = defaultFactory.call(param);
            viewModel.addEventFromView((Entry<String>) defaultEntry);
            return defaultEntry;
        }
    }
}
