package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextArea;
import gui.utility.NavigationHelper;
import gui.view_model.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private TextField taskName;

    @FXML
    private TextArea taskDetail;

    @Override
    public void init(ViewModel viewModel) {
        taskName.setText("Name of Task");
        taskDetail.setText("Detail of this task will be shown here");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);
    }
}
