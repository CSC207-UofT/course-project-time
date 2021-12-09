package gui.view.todolist;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import gui.utility.NavigationHelper;
import gui.view.ViewModelBindingController;
import gui.viewmodel.todolist.AddTaskPageViewModel;
import gui.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddTaskPageController implements Initializable, ViewModelBindingController {

    private AddTaskPageViewModel viewModel;

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
    private JFXListView<TextField> subtaskList;

    @FXML
    private DialogPane message;

    private final Pattern dueTimeHoursPattern = Pattern.compile("[01]?[0-9]|2[0-4]");
    private final Pattern dueTimeMinutesPattern = Pattern.compile("[0-5][0-9]");
    private final Pattern durationPattern = Pattern.compile("[0-9]+");

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

    public void saveTask(MouseEvent event) {
        Label messageLabel;
        HBox messageBox;
        if ("".equals(taskName.getText())) {
            messageLabel = new Label("Task creation failed: task name cannot be empty");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
            message.setVisible(true);
        } else if (dueDate.getValue() != null && !dueTimeHoursPattern.matcher(dueTimeHours.getText()).matches()) {
            // do not check for due time if there is no due date
            // since without due date we would not need the due time either
            messageLabel = new Label("Task creation failed: invalid input for due time hours");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
            message.setVisible(true);
        } else if (dueDate.getValue() != null && !dueTimeMinutesPattern.matcher(dueTimeMinutes.getText()).matches()) {
            messageLabel = new Label("Task creation failed: invalid input for due time minutes");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
            message.setVisible(true);
        } else if (!durationPattern.matcher(duration.getText()).matches()) {
            messageLabel = new Label("Task creation failed: invalid input for duration");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
            message.setVisible(true);
        } else {
            // convert the subtasks JFXListView to Java List
            List<String> subTasks = new ArrayList<>();
            for (TextField item : subtaskList.getItems()) {
                subTasks.add(item.getText());
            }

            if (dueDate.getValue() == null) {
                viewModel.addTask(taskName.getText(), duration.getText(), subTasks);
            } else {
                viewModel.addTask(taskName.getText(), dueDate.getValue(), dueTimeHours.getText(),
                        dueTimeMinutes.getText(), duration.getText(), subTasks);
            }

            enterTodoListPage(event);
        }
    }

    public void enterTodoListPage(MouseEvent event) {
        try {
            NavigationHelper.enterTodoListPage(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (AddTaskPageViewModel) viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);
    }
}
