package gui.view;

import com.jfoenix.controls.JFXDrawer;
import gui.utility.NavigationHelper;
import gui.viewmodel.SettingsViewModel;
import gui.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class SettingsController implements Initializable, ViewModelBindingController{

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button exportButton;
    private SettingsViewModel viewModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);
    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (SettingsViewModel)viewModel;
    }

    public void exportICS() {
        LocalDateTime fiveYearsAgo = LocalDateTime.now().minusYears(5);
        LocalDateTime fiveYearsFromNow = LocalDateTime.now().plusYears(5);
        viewModel.exportICS(fiveYearsAgo, fiveYearsFromNow);
    }

}
