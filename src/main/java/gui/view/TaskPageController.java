package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import gui.utility.NavigationHelper;
import gui.viewmodel.TaskPageViewModel;
import gui.viewmodel.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TaskPageController implements Initializable, ViewModelBindingController {

    private TaskPageViewModel viewModel;

    private final ObservableMap<String, String> taskInfoMap = FXCollections.observableHashMap();

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private TextField taskName;

    @FXML
    private DatePicker dueDate;

    @FXML
    private TextField dueTimeHours;

    @FXML
    private TextField dueTimeMinutes;

    @FXML
    private TextField duration;

    @FXML
    private ToggleButton completed;

    @FXML
    private Button addSubtask;

    @FXML
    private Button scheduleAsEvent;

    @FXML
    private Button updateTask;

    @FXML
    private Button deleteTask;

    @FXML
    private JFXListView<TextField> subtaskList;

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void displayTask() {
        taskName.setText(taskInfoMap.get("taskName"));

        if (!"".equals(taskInfoMap.get("dueDate"))) {
            dueDate.setValue(LocalDate.parse(taskInfoMap.get("dueDate"), dateFormat));
            dueTimeHours.setText(taskInfoMap.get("dueTimeHours"));
            dueTimeMinutes.setText(taskInfoMap.get("dueTimeMinutes"));
        }

        duration.setText(taskInfoMap.get("duration"));
        completed.setSelected("yes".equals(taskInfoMap.get("completed")));

        String subtasks = taskInfoMap.get("subtasks");
        if (subtasks != null) {
            String[] subtaskArray = subtasks.split(" ");
            for (String s : subtaskArray) {
                TextField newSubtask = new TextField();
                newSubtask.setText(s);
                subtaskList.getItems().add(newSubtask);
            }
        }
    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (TaskPageViewModel) viewModel;
        Bindings.bindContentBidirectional(this.taskInfoMap, this.viewModel.getTaskInfoMap());

        if (!taskInfoMap.containsValue(null)) {
            displayTask();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);
    }

}
