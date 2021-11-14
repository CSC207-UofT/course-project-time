package main.java.console_app;

import main.java.services.pomodoro_running.CancelTimerInput;
import main.java.data_gateway.PomodoroTimerManager;
import main.java.services.pomodoro_running.PomodoroObserver;
import main.java.services.pomodoro_running.PomodoroRunner;

public class PomodoroController {
    private PomodoroRunner pomodoroRunner;
    private PomodoroTimerManager pomodoroTimerManager = new PomodoroTimerManager();
    private boolean switched = false;

    public boolean startTimer() {
        pomodoroRunner.startTimer(!switched);
        PomodoroObserver pomodoroObserver = new PomodoroObserver(pomodoroRunner);
        switched = !switched;
        return pomodoroObserver.startTracking();
    }

    public synchronized void checkUserInput() {
        Thread thread = new Thread(new CancelTimerInput());
        thread.start();
    }


    public boolean stopTimer() {
        return pomodoroTimerManager.stopPomodoroTimer();
    }

    public PomodoroRunner getPomodoroRunner() {
        return this.pomodoroRunner;
    }

    public boolean getSwitched() {
        return this.switched;
    }

    public void setPomodoroRunner(int workTime, int breakTime) {
        this.pomodoroRunner = new PomodoroRunner(pomodoroTimerManager.createPomodoroTimer(workTime, breakTime));
    }

    public void setSwitched(boolean switched) {
        this.switched = switched;
    }
}
