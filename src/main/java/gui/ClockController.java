package gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class ClockController {

    private float time_milli;
    private AnimationTimer timer;
    private GraphicsContext gc;
    private float brushSize = 10;

    private long workDuration = 20;
    private long breakDuration = 5;

    private long startTime = 0;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField workText;

    @FXML
    private TextField breakText;

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(10);

        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                long angle = now / TimeUnit.NANOSECONDS.convert(workDuration, TimeUnit.MINUTES);

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                canvas.getGraphicsContext2D().strokeArc(brushSize, brushSize, canvas.getWidth() - brushSize * 2,
                        canvas.getHeight() - brushSize * 2, 90, angle, ArcType.OPEN);
            }
        };
    }


    @FXML
    void updateWorkTime() {
        String input = workText.getText();

        if (isShortDuration(input)) {
            workDuration = Integer.parseInt(input);
        }
    }

    @FXML
    void updateBreakTime(MouseEvent event) {
        String input = breakText.getText();

        if (isShortDuration(input)) {
            breakDuration = Integer.parseInt(input);
        }
    }

    @FXML
    void startClock(MouseEvent event) {
        timer.start();
    }

    @FXML
    void resetClock(MouseEvent event) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        timer.stop();
    }

    private boolean isShortDuration(String input) {
        return Pattern.matches("\\d\\*", input);
    }


}
