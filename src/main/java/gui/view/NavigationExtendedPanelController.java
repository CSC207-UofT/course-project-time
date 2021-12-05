package gui.view;

import com.jfoenix.controls.JFXDrawer;
import gui.utility.NavigationHelper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class NavigationExtendedPanelController {

    private final NavigationHelper navigationHelper = new NavigationHelper();

    @FXML
    void enterCalendarPage(MouseEvent event) {
        try {
            navigationHelper.enterMonthlyCalendarPage(event);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void enterHomePage(MouseEvent event) {
        try {
            navigationHelper.enterHomePage(event);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void enterPomodoroPage(MouseEvent event) {
        try {
            navigationHelper.enterPomodoroPage(event);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void enterSettingsPage(MouseEvent event) {
        try {
            navigationHelper.enterSettingsPage(event);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void enterTodoListPage(MouseEvent event) {
        try {
            navigationHelper.enterTodoListPage(event);
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
