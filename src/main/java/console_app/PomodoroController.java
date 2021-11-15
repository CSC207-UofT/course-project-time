package main.java.console_app;

import main.java.services.pomodoro_running.CancelTimerInput;
import main.java.data_gateway.PomodoroTimerManager;
import main.java.services.pomodoro_running.PomodoroObserver;
import main.java.services.pomodoro_running.PomodoroRunner;

public class PomodoroController {
    private PomodoroRunner pomodoroRunner;
    private final PomodoroTimerManager pomodoroTimerManager = new PomodoroTimerManager();
    private boolean switched = false;
    private final CancelTimerInput cancelTimerInput = new CancelTimerInput();

    /**
     * start the timer according to whichever work/break interval the user is on
     * @return true if timer has to continue to run, false if timer has been cancelled
     */
    public boolean startTimer(){
        pomodoroRunner.startTimer(!switched);
        PomodoroObserver pomodoroObserver = new PomodoroObserver(pomodoroRunner, cancelTimerInput);
        switched = !switched;
        return pomodoroObserver.startTracking();
    }

    /**
     * starts the thread to check if the user cancels the pomodoro timer
     */
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
