package consoleapp;

import datagateway.OldPomodoroTimerManager;
import services.oldpomodororunning.CancelTimerInput;
import services.oldpomodororunning.PomodoroObserver;
import services.oldpomodororunning.PomodoroRunner;

public class OldPomodoroController {
    private PomodoroRunner pomodoroRunner;
    private final OldPomodoroTimerManager oldPomodoroTimerManager = new OldPomodoroTimerManager();
    private boolean switched = false;
    private final CancelTimerInput cancelTimerInput = new CancelTimerInput();

    /**
     * start the timer according to whichever work/break interval the user is on
     * @return true if timer has to continue to run, false if timer has been cancelled
     */
    public boolean startTimer(){
        //switched is whether or not the pomodoro timer has switched ie. switched from work to break
        //true means it's a work interval, false means it's a break interval
        pomodoroRunner.startTimer(!switched);
        PomodoroObserver pomodoroObserver = new PomodoroObserver(pomodoroRunner, cancelTimerInput);

        //this assignment happens so that the next call the startTimer will start with the opposite interval
        //ex. if this interval was a work interval, this assignment makes the next interval a break interval
        switched = !switched;
        return pomodoroObserver.startTracking();
    }

    /**
     * starts the thread to check if the user cancels the pomodoro timer
     */
    public synchronized void checkUserInput() {
        cancelTimerInput.setCancel(false);
        Thread thread = new Thread(cancelTimerInput);
        thread.start();
    }

    public void stopTimer() {
        pomodoroRunner.stopTimer();
    }

    public void setPomodoroRunner(int workTime, int breakTime) {
        this.pomodoroRunner = new PomodoroRunner(oldPomodoroTimerManager.createPomodoroTimer(workTime, breakTime));
    }

}
