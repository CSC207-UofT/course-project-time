package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextArea;
import gui.utility.NavigationHelper;
import gui.view_model.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskBarController implements Initializable, ViewModelBindingController {

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Text taskName;

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

    @Override
    public void init(ViewModel viewModel) {
        taskName.setText("name of task");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);
    }
}
