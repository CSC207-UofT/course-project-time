package main.java.interface_adapters;

import main.java.entity_gateway.PomodoroTimerManager;
import main.java.use_case.PomodoroObserver;
import main.java.use_case.PomodoroRunner;

public class PomodoroController {
    private PomodoroRunner pomodoroRunner;
    private PomodoroTimerManager pomodoroTimerManager = new PomodoroTimerManager();

    public synchronized void startTimer() {
        Thread thread = new Thread(new PomodoroObserver(pomodoroRunner));
        thread.start();
        pomodoroRunner.startTimer(true);
    }

    public boolean stopTimer() {
        return pomodoroTimerManager.stopPomodoroTimer();
    }

    public void setPomodoroRunner(int workTime, int breakTime) {
        this.pomodoroRunner = new PomodoroRunner(pomodoroTimerManager.createPomodoroTimer(workTime, breakTime));
    }
}
