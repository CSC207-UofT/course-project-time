import datagateway.pomodoro.PomodoroManager;
import entity.PomodoroTimer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PomodoroSaverAndLoaderTest {

    PomodoroManager pomodoroManager;
    PomodoroTimer pomodoroTimer;

    @BeforeEach
    void SetUp() {
        pomodoroManager = new PomodoroManager();
        pomodoroManager.createTimer(0, true, 25, 5);
        pomodoroTimer = pomodoroManager.getPomodoroTimer();
    }

    @Test
    void saveAndLoadPomodoro() throws IOException {
        pomodoroManager.saveTimer();
        pomodoroManager.loadTimer("PomodoroData.json");
        assertEquals(pomodoroTimer.getStartTime(), pomodoroManager.getPomodoroTimer().getStartTime());
    }
}
