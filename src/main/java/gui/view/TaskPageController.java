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
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    private JFXListView<TextField> subtaskList;

    @FXML
    private Button addSubtask;

    @FXML
    private Button removeSubtask;

    @FXML
    private Button schedule;

    @FXML
    private DialogPane message;

    private final Pattern dueTimeHoursPattern = Pattern.compile("[01]?[0-9]|2[0-4]");
    private final Pattern dueTimeMinutesPattern = Pattern.compile("[0-5][0-9]");
    private final Pattern durationPattern = Pattern.compile("[0-9]+");

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private List<String> oldSubtaskList;  // track the subtasks before updating

    public void addSubtask() {
        TextField newSubtask = new TextField();
        subtaskList.getItems().add(newSubtask);
    }

    public void removeSubtask() {
        if (subtaskList.getItems().size() - 1 >= 0) {
            subtaskList.getItems().remove(subtaskList.getItems().size() - 1);
        } else {
            Label messageLabel = new Label("No more subtasks to remove!");
            HBox messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
            message.setVisible(true);
        }
    }

    public void displayTask() {
        taskName.setText(taskInfoMap.get("taskName"));

        boolean taskIsCompleted = "yes".equals(taskInfoMap.get("completed"));
        setEditable(!taskIsCompleted);

        if (!"".equals(taskInfoMap.get("dueDate"))) {
            dueDate.setValue(LocalDate.parse(taskInfoMap.get("dueDate"), dateFormat));
            dueTimeHours.setText(taskInfoMap.get("dueTimeHours"));
            dueTimeMinutes.setText(taskInfoMap.get("dueTimeMinutes"));
        }

        duration.setText(taskInfoMap.get("duration"));
        completed.setSelected(taskIsCompleted);

        String subtasks = taskInfoMap.get("subtasks");
        oldSubtaskList = new ArrayList<>();
        if (subtasks != null) {
            String[] subtaskArray = subtasks.split(" ");
            for (String s : subtaskArray) {
                TextField newSubtask = new TextField();
                newSubtask.setText(s);
                subtaskList.getItems().add(newSubtask);
            }
            oldSubtaskList.addAll(List.of(subtaskArray));  // record the subtasks before any changes
        }
    }

    /**
     * Set the editable status of the following boxes to the input
     * - due date box
     * - due time hour box
     * - due time minute box
     * - duration box
     * - completed button
     * - subtask addition button
     * - subtask removal button
     * - subtask names
     * If not editable, the "schedule as event" button will disappear
     * @param editable whether we want the above boxes to be editable
     */
    private void setEditable(boolean editable) {
        dueDate.setDisable(!editable);
        dueTimeHours.setDisable(!editable);
        dueTimeMinutes.setDisable(!editable);
        duration.setDisable(!editable);
        completed.setDisable(!editable);
        addSubtask.setDisable(!editable);
        removeSubtask.setDisable(!editable);
        subtaskList.setDisable(!editable);
        schedule.setVisible(editable);
    }

    public void updateTask(MouseEvent event) {
        Label messageLabel;
        HBox messageBox;
        if ("".equals(taskName.getText())) {
            messageLabel = new Label("Task modification failed: task name cannot be empty");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
            message.setVisible(true);
        } else if (dueDate.getValue() != null && !dueTimeHoursPattern.matcher(dueTimeHours.getText()).matches()) {
            // do not check for due time if there is no due date
            // since without due date we would not need the due time either
            messageLabel = new Label("Task modification failed: invalid input for due time hours");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
            message.setVisible(true);
        } else if (dueDate.getValue() != null && !dueTimeMinutesPattern.matcher(dueTimeMinutes.getText()).matches()) {
            messageLabel = new Label("Task modification failed: invalid input for due time minutes");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
            message.setVisible(true);
        } else if (!durationPattern.matcher(duration.getText()).matches()) {
            messageLabel = new Label("Task modification failed: invalid input for duration");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
            message.setVisible(true);
        } else {
            // convert the subtasks JFXListView to Java List
            List<String> newSubtaskList = new ArrayList<>();
            for (TextField item : subtaskList.getItems()) {
                newSubtaskList.add(item.getText());
            }

            if (dueDate.getValue() == null) {
                viewModel.updateTask(taskName.getText(), duration.getText(), oldSubtaskList, newSubtaskList,
                        completed.isSelected());
            } else {
                viewModel.updateTask(taskName.getText(), dueDate.getValue(), dueTimeHours.getText(),
                        dueTimeMinutes.getText(), duration.getText(), oldSubtaskList, newSubtaskList,
                        completed.isSelected());
            }

            enterTodoListPage(event);
        }
    }

    public void deleteTask(MouseEvent event) {
        viewModel.deleteTask();
        enterTodoListPage(event);
    }

    public void taskToEvent(MouseEvent event) {
        viewModel.taskToEvent();
        enterCalendarPage(event);
    }

    public void enterTodoListPage(MouseEvent event) {
        try {
            NavigationHelper.enterTodoListPage(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterCalendarPage(MouseEvent event) {
        try {
            NavigationHelper.enterMonthlyCalendarPage(event);
        } catch (IOException e) {
            e.printStackTrace();
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
