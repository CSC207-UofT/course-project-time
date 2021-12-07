package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import datagateway.event.EventReader;
import datagateway.task.TaskReader;
import gui.utility.NavigationHelper;
import gui.viewmodel.MainPageViewModel;
import gui.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class MainPageController implements Initializable, ViewModelBindingController {

    private MainPageViewModel viewModel;
    final double labelFontSize = 15;

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private JFXListView<HBox> eventListView;

    @FXML
    private JFXListView<HBox> taskListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);
    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (MainPageViewModel) viewModel;

        this.viewModel.addObserver(this::acceptReaders);

        updateListView(taskListView, formatTaskReaders(this.viewModel.getRelevantTasks()));
        updateEventListView(eventListView, formatEventReaders(this.viewModel.getRelevantEvents()));

    }

    /**
     * Note: removes visibility of all previously rendered labels in the list view before flushing it out.
     * @param listView the list view to update
     * @param map a mapping of name and time for today's relevant items
     */
    private void updateListView(JFXListView<HBox> listView, Map<String, String> map) {
        listView.getItems().forEach(i -> i.setVisible(false));
        listView.getItems().clear();
        for (Map.Entry<String, String> taskInfo : map.entrySet()) {
            Label taskName = new Label(taskInfo.getKey());
            Label deadline = new Label(taskInfo.getValue());
            taskName.setFont(new Font(labelFontSize));
            deadline.setFont(new Font(labelFontSize));
            taskName.setMinWidth(550);
            taskName.setMaxWidth(550);
            HBox task = new HBox(taskName, deadline);
            listView.getItems().add(task);
        }
        listView.refresh();

    }

    /**
     * Note: removes visibility of all previously rendered labels in the list view before flushing it out.
     * @param eventListView the list view to update
     * @param map a mapping of name and time for today's relevant items
     */
    private void updateEventListView(JFXListView<HBox> eventListView, Map<String, List<String>> map) {
        eventListView.getItems().forEach(i -> i.setVisible(false));
        eventListView.getItems().clear();
        for (Map.Entry<String, List<String>> eventInfo : map.entrySet()) {
            Label eventName = new Label(eventInfo.getKey());
            Label startTime = new Label("Start Time is " + eventInfo.getValue().get(0));
            Label endTime = new Label("End Time is " + eventInfo.getValue().get(1));
            eventName.setFont(new Font(labelFontSize));
            startTime.setFont(new Font(labelFontSize));
            endTime.setFont(new Font(labelFontSize));
            eventName.setMinWidth(200);
            eventName.setMaxWidth(200);
            startTime.setMinWidth(300);
            startTime.setMaxWidth(300);

            HBox task = new HBox(eventName, startTime, endTime);
            eventListView.getItems().add(task);
        }
        eventListView.refresh();

    }


    /**
     * Method listening to updates from the {@link MainPageViewModel}.
     * @param readers the lists of updated readers
     */
    private void acceptReaders(MainPageViewModel.Readers readers) {
        updateListView(taskListView, formatTaskReaders(readers.taskReaders));
        updateEventListView(eventListView, formatEventReaders(readers.eventReaders));
    }

    private Map<String, String> formatTaskReaders(List<TaskReader> taskReaders) {
        Map<String, String> taskMap = new HashMap<>();
        if (taskReaders.isEmpty()){
            taskMap.put("No Scheduled Tasks", null);
        }
        for (TaskReader task: taskReaders) {
            String taskName = task.getName();
            String deadline = formatDuration(task.getDeadline());
            taskMap.put(taskName, deadline);
        }
        return taskMap;
    }

    private String formatDuration(LocalDateTime localDateTime) {
        return localDateTime.format(
                DateTimeFormatter.ofLocalizedDateTime(
                        FormatStyle.MEDIUM,
                        FormatStyle.SHORT));
    }

    private Map<String, List<String>> formatEventReaders(List<EventReader> eventReaders) {
        Map<String, List<String>> eventMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        if (eventReaders.isEmpty()){
            eventMap.put("No Scheduled Events", null);
        }

        for (EventReader event: eventReaders) {
            String eventName = event.getName();

            String startTime = event.getStartTime().format(
                    DateTimeFormatter.ofLocalizedTime(
                            FormatStyle.SHORT));

            String endTime = event.getEndTime().format(
                    DateTimeFormatter.ofLocalizedTime(
                            FormatStyle.SHORT));
            list.add(startTime);
            list.add(endTime);
            eventMap.put(eventName, list);
        }
        return eventMap;
    }

}
