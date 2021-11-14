package main.java.use_case;

public class PomodoroObserver implements Runnable{
    private PomodoroRunner pomodoroRunner;

    public PomodoroObserver(PomodoroRunner pomodoroRunner) {
        this.pomodoroRunner = pomodoroRunner;
    }

    @Override
    public void run() {
        PomodoroTimerTask pomodoroTimerTask = pomodoroRunner.getPomodoroTimerTask();
        boolean switchNow = false;
        while(!switchNow || pomodoroRunner.getPomodoroTimer().getCanceled()) {
            switchNow = pomodoroTimerTask.getSwitchNow();
        }
        if (!pomodoroRunner.getPomodoroTimer().getCanceled()) {
            pomodoroRunner.startTimer(!pomodoroRunner.getPomodoroTimer().getIsWorking());
        }
    }
}
