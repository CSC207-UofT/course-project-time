package main.java.use_case;

public class PomodoroObserver{
    private PomodoroRunner pomodoroRunner;

    public PomodoroObserver(PomodoroRunner pomodoroRunner) {
        this.pomodoroRunner = pomodoroRunner;
    }

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
