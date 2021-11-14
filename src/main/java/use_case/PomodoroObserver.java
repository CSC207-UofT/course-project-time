package main.java.use_case;

public class PomodoroObserver implements Runnable{
    private PomodoroRunner pomodoroRunner;
    private boolean switchInterval = false;

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
        this.switchInterval = switchNow;
//        if (!pomodoroRunner.getPomodoroTimer().getCanceled()) {
//            pomodoroRunner.startTimer(!pomodoroRunner.getPomodoroTimer().getIsWorking());
//        }
    }

    public boolean getSwitchInterval() {
        return this.switchInterval;
    }
}
