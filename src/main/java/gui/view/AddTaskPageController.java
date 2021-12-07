package gui.view;

import com.jfoenix.controls.JFXDrawer;
import gui.utility.NavigationHelper;
import gui.viewmodel.AddTaskPageViewModel;
import gui.viewmodel.ViewModel;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
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
    private DialogPane message;

    private final Pattern dueTimeHoursPattern = Pattern.compile("[01]?[0-9]|2[0-4]");
    private final Pattern dueTimeMinutesPattern = Pattern.compile("[0-5][0-9]");
    private final Pattern durationPattern = Pattern.compile("[0-9]+");

    public void saveTask(MouseEvent event) {
        Label messageLabel;
        HBox messageBox;
        if ("".equals(taskName.getText())) {
            messageLabel = new Label("Task creation failed: task name cannot be empty");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
        } else if (dueDate.getValue() == null) {
            messageLabel = new Label("Task creation failed: due date cannot be empty");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
        } else if (!dueTimeHoursPattern.matcher(dueTimeHours.getText()).matches()) {
            messageLabel = new Label("Task creation failed: invalid input for due time hours");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
        } else if (!dueTimeMinutesPattern.matcher(dueTimeMinutes.getText()).matches()) {
            messageLabel = new Label("Task creation failed: invalid input for due time minutes");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
        } else if (!durationPattern.matcher(duration.getText()).matches()) {
            messageLabel = new Label("Task creation failed: invalid input for duration");
            messageBox = new HBox(messageLabel);
            message.setContent(messageBox);
        } else {
            viewModel.addTask(taskName.getText(), dueDate.getValue(), dueTimeHours.getText(),
                    dueTimeMinutes.getText(), duration.getText());

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
