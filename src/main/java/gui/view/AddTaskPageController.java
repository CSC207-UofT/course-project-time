package gui.view;

import com.jfoenix.controls.JFXDrawer;
import gui.utility.NavigationHelper;
import gui.viewmodel.AddTaskPageViewModel;
import gui.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
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
    private Button addSubtask;

    @FXML
    private Button save;

    private Pattern dueTimeHoursPattern = Pattern.compile("[01]?[0-9]|2[0-4]");
    private Pattern dueTimeMinutesPattern = Pattern.compile("[0-5][0-9]");
    private Pattern durationPattern = Pattern.compile("[0-9]+");

    public void saveTask(MouseEvent event) {
        if (!dueTimeHoursPattern.matcher(dueTimeHours.getText()).matches()) {
            System.out.println("Invalid input for due time hours");
        } else if (!dueTimeMinutesPattern.matcher(dueTimeMinutes.getText()).matches()) {
            System.out.println("Invalid input for due time minutes");
        } else if (!durationPattern.matcher(duration.getText()).matches()) {
            System.out.println("Invalid input for duration");
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
