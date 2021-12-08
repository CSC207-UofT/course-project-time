package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import gui.utility.NavigationHelper;
import gui.viewmodel.MainPageViewModel;
import gui.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import services.eventpresentation.EventInfo;
import services.taskpresentation.TaskInfo;

import java.net.URL;
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

        updateTaskListView(formatTaskInfos(this.viewModel.getRelevantTasks()));
        updateEventListView(formatEventInfos(this.viewModel.getRelevantEvents()));

    }

    /**
     * Note: removes visibility of all previously rendered labels in the list view before flushing it out.
     * @param map a mapping of name and time for today's relevant items
     */
    private void updateTaskListView(Map<String, String> map) {
        taskListView.getItems().forEach(i -> i.setVisible(false));
        taskListView.getItems().clear();
        for (Map.Entry<String, String> taskInfo : map.entrySet()) {
            Label taskName = new Label(taskInfo.getKey());
            Label deadline = new Label(taskInfo.getValue());
            taskName.setFont(new Font(labelFontSize));
            deadline.setFont(new Font(labelFontSize));
            taskName.setMinWidth(550);
            taskName.setMaxWidth(550);
            HBox task = new HBox(taskName, deadline);
            taskListView.getItems().add(task);
        }
        taskListView.refresh();

    }

    /**
     * Note: removes visibility of all previously rendered labels in the list view before flushing it out.
     * @param map a mapping of name and time for today's relevant items
     */
    private void updateEventListView(Map<String, List<String>> map) {
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
     * @param infos the lists of updated infos
     */
    private void acceptReaders(MainPageViewModel.Infos infos) {
        updateTaskListView(formatTaskInfos(infos.taskInfos));
        updateEventListView(formatEventInfos(infos.eventInfos));
    }

    private Map<String, String> formatTaskInfos(List<TaskInfo> taskInfo) {
        Map<String, String> taskMap = new HashMap<>();
        if (taskInfo.isEmpty()){
            taskMap.put("No Scheduled Tasks", null);
        }
        for (TaskInfo task: taskInfo) {
            String taskName = task.getName();
            String deadline = this.viewModel.formatDeadline(task.getDeadline());
            taskMap.put(taskName, deadline);
        }
        return taskMap;
    }


    private Map<String, List<String>> formatEventInfos(List<EventInfo> eventInfos) {
        Map<String, List<String>> eventMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        if (eventInfos.isEmpty()){
            eventMap.put("No Scheduled Events", null);
        }

        for (EventInfo event: eventInfos) {
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