package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import gui.utility.NavigationHelper;
import gui.view_model.MainPageViewModel;
import gui.view_model.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable, ViewModelBindingController{
    final double labelFontSize = 15;
    private MainPageViewModel viewModel;

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private JFXListView<HBox> eventList;

    @FXML
    private JFXListView<HBox> taskList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);

    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (MainPageViewModel) viewModel;
        // event example
        Label noEvent = new Label("There is no upcoming event");
        noEvent.setFont(new Font(labelFontSize));

        HBox event = new HBox(noEvent);
        eventList.getItems().add(event);

        // task example
        Label taskName = new Label("Sleeping");
        Label deadLine = new Label("Dec 20, 2021, 10:00 PM");
        taskName.setFont(new Font(labelFontSize));
        deadLine.setFont(new Font(labelFontSize));

        taskName.setMinWidth(550);
        taskName.setMaxWidth(550);
        HBox task = new HBox(taskName, deadLine);
        taskList.getItems().add(task);


    }
}
