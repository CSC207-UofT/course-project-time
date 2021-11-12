package main.java.interface_adapters;

import main.java.entity_gateway.PomodoroTimerManager;
import main.java.use_case.PomodoroObserver;

public class PomodoroController {
    private PomodoroObserver pomodoroObserver;
    private PomodoroTimerManager pomodoroTimerManager = new PomodoroTimerManager();

    public PomodoroController(int workTime, int breakTime) {
        this.pomodoroObserver = new PomodoroObserver(pomodoroTimerManager.createPomodoroTimer(workTime, breakTime));
    }

    public void startTimer(int workTime, int breakTime) {
        pomodoroObserver.startTimer(true);
    }

    public void stopTimer() {
        pomodoroTimerManager.stopPomodoroTimer();
    }
}
