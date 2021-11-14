package main.java.interface_adapters;

import main.java.CancelTimerInput;
import main.java.entity_gateway.PomodoroTimerManager;
import main.java.use_case.PomodoroObserver;
import main.java.use_case.PomodoroRunner;

public class PomodoroController {
    private PomodoroRunner pomodoroRunner;
    private PomodoroTimerManager pomodoroTimerManager = new PomodoroTimerManager();
    private boolean switched = false;

    public synchronized boolean startTimer() {
        pomodoroRunner.startTimer(!switched);
        PomodoroObserver pomodoroObserver = new PomodoroObserver(pomodoroRunner);
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
