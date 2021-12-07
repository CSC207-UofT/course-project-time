package gui.view;

import com.jfoenix.controls.JFXDrawer;
import gui.utility.NavigationHelper;
import gui.view_model.AddTaskPageViewModel;
import gui.view_model.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTaskPageController implements Initializable, ViewModelBindingController {

    private AddTaskPageViewModel viewModel;

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (AddTaskPageViewModel) viewModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);
    }
}
