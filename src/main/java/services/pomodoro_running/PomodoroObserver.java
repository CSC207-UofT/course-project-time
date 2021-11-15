package main.java.services.pomodoro_running;


public class PomodoroObserver{
    private final PomodoroRunner pomodoroRunner;
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
        while(!switchNow && !cancelTimer) {
            switchNow = pomodoroTimerTask.getSwitchNow();
            cancelTimer = cancelTimerInput.getCancel();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
        }
        return switchNow;
    }
}
