package gui;

import gui.utility.AdaptedFXMLLoader;
import gui.view.MonthlyCalendarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class GUIDriver extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AdaptedFXMLLoader loader = new AdaptedFXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource("/monthlyCalendar.fxml")));
        Parent root = loader.load();

        primaryStage.setTitle("Project Time");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
