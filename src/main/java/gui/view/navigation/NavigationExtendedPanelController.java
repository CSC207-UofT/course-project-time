package gui.view.navigation;

import com.jfoenix.controls.JFXDrawer;
import gui.utility.NavigationHelper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class NavigationExtendedPanelController {

    @FXML
    void enterCalendarPage(MouseEvent event) {
        try {
            NavigationHelper.enterMonthlyCalendarPage(event);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void enterHomePage(MouseEvent event) {
        try {
            NavigationHelper.enterHomePage(event);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void enterPomodoroPage(MouseEvent event) {
        try {
            NavigationHelper.enterPomodoroPage(event);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void enterSettingsPage(MouseEvent event) {
        try {
            NavigationHelper.enterSettingsPage(event);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void enterTodoListPage(MouseEvent event) {
        try {
            NavigationHelper.enterTodoListPage(event);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void menuIconClicked(MouseEvent event) {
        Scene currentScene = ((Node) event.getSource()).getScene();
        JFXDrawer extendedNavPanel = (JFXDrawer) currentScene.lookup("#extendedNavPanel");
        extendedNavPanel.setVisible(false);
        extendedNavPanel.close();
    }
}
