package main.java.services.pomodoro_running;

import main.java.services.pomodoro_running.PomodoroRunner;
import main.java.services.pomodoro_running.PomodoroTimerTask;

public class PomodoroObserver{
    private PomodoroRunner pomodoroRunner;

    public PomodoroObserver(PomodoroRunner pomodoroRunner) {
        this.pomodoroRunner = pomodoroRunner;
    }

    /**
     * track when to switch from work to break or from break to work intervals
     * @return true when the pomodoro timer needs to start the next timer
     */
    public boolean startTracking() {
        PomodoroTimerTask pomodoroTimerTask = pomodoroRunner.getPomodoroTimerTask();
        boolean switchNow = false;
        while(!switchNow || pomodoroRunner.getPomodoroTimer().getCanceled()) {
            switchNow = pomodoroTimerTask.getSwitchNow();
        }
        return true;
//        if (!pomodoroRunner.getPomodoroTimer().getCanceled()) {
//            pomodoroRunner.startTimer(!pomodoroRunner.getPomodoroTimer().getIsWorking());
//        }
    }
}
