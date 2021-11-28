package gui.view;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BasicPageController implements Initializable {

    @FXML
    private JFXDrawer drawer;

    @FXML
    private AnchorPane mainBackground;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // this adds the navigation panel to the view page
        try {
            VBox vBox = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/navigationPanel.fxml")));
            drawer.setSidePane(vBox);
            drawer.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
