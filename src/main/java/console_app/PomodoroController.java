package main.java.console_app;

import main.java.services.pomodoro_running.CancelTimerInput;
import main.java.data_gateway.PomodoroTimerManager;
import main.java.services.pomodoro_running.PomodoroObserver;
import main.java.services.pomodoro_running.PomodoroRunner;

public class PomodoroController {
    private PomodoroRunner pomodoroRunner;
    private PomodoroTimerManager pomodoroTimerManager = new PomodoroTimerManager();
    private boolean switched = false;
    private CancelTimerInput cancelTimerInput = new CancelTimerInput();

    public boolean startTimer() {
        pomodoroRunner.startTimer(!switched);
        PomodoroObserver pomodoroObserver = new PomodoroObserver(pomodoroRunner, cancelTimerInput);
        switched = !switched;
        return pomodoroObserver.startTracking();
    }

    public synchronized void checkUserInput() {
        Thread thread = new Thread(cancelTimerInput);
        thread.start();
    }

    public boolean stopTimer() {
        pomodoroRunner.stopTimer();
        return pomodoroTimerManager.stopPomodoroTimer();
    }

    public void setPomodoroRunner(int workTime, int breakTime) {
        this.pomodoroRunner = new PomodoroRunner(pomodoroTimerManager.createPomodoroTimer(workTime, breakTime));
    }

}
