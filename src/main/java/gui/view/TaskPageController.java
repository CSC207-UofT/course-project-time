package gui.view;

import com.jfoenix.controls.JFXDrawer;
import gui.utility.NavigationHelper;
import gui.viewmodel.TaskPageViewModel;
import gui.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskPageController implements Initializable, ViewModelBindingController {

    private TaskPageViewModel viewModel;

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private AnchorPane mainBackground;

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

    public void displayTask(long taskId) {

    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (TaskPageViewModel) viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);
    }
}
