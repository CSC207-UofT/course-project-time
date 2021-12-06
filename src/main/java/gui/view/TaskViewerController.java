package gui.view;

import entity.Task;
import gui.view_model.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import services.task_presentation.TaskInfo;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TaskViewerController{

    @FXML
    private Label taskLabel;

    @FXML
    private Label timeLabel;

    Task task;

    public void setTaskData(Task task){
        this.task = task;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = task.getDeadline().format(formatter);
        taskLabel.setText(task.getTaskName());
        timeLabel.setText(formattedDateTime);
    }


}
