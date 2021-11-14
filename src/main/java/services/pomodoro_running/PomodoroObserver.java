package main.java.services.pomodoro_running;

import java.util.Scanner;

public class PomodoroObserver{
    private PomodoroRunner pomodoroRunner;
    private CancelTimerInput cancelTimerInput;

    public PomodoroObserver(PomodoroRunner pomodoroRunner, CancelTimerInput cancelTimerInput) {
        this.pomodoroRunner = pomodoroRunner;
        this.cancelTimerInput = cancelTimerInput;
    }

    /**
     * track when to switch from work to break or from break to work intervals or if the user cancels the timer
     * @return true when the pomodoro timer needs to start the next timer, false when user cancels timer
     */
    public boolean startTracking() {
        PomodoroTimerTask pomodoroTimerTask = pomodoroRunner.getPomodoroTimerTask();
        boolean switchNow = false;
        boolean cancelTimer = false;
        while(!switchNow /*&& !cancelTimer*/) {
            switchNow = pomodoroTimerTask.getSwitchNow();
            //cancelTimer = cancelTimerInput.getCancel();
        }
        return switchNow;
    }
}
