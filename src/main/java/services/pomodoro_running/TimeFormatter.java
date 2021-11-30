package services.pomodoro_running;

import entity.PomodoroTimer;

import java.util.Timer;

public class TimeFormatter {
//    private final PomodoroTimer pomodoroTimer;
//    private final Timer timer;
    private PomodoroTimerTask pomodoroTimerTask;

//    public PomodoroRunner(PomodoroTimer pomodoroTimer) {
//        this.pomodoroTimer = pomodoroTimer;
//        this.timer = new Timer();
//    }

    public String formatTime(double elapsedTime, long currentDuration) {
        double totalTime = (double)currentDuration;
        double timeLeft = totalTime - elapsedTime;


        String seconds = Integer.toString((int)timeLeft % 60);
        String minutes = Integer.toString((int)timeLeft % 3600);

        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        else if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }

        return minutes + ':' + seconds;
    }

    /**
     * schedule the timer according to the lengths of time that the user has inputted
     * @param isWorking whether the user is on a "work" interval or "break" interval
     */
    public void startTimer(boolean isWorking){

//        this.pomodoroTimerTask = new PomodoroTimerTask(timer, pomodoroTimer);
//        if (isWorking) {
//            timer.schedule(pomodoroTimerTask, (pomodoroTimer.getWorkLength())* 60000L);
//        }
//        else {
//            timer.schedule(pomodoroTimerTask, (pomodoroTimer.getBreakLength())* 60000L);
//        }
    }

    public void stopTimer() {
        pomodoroTimerTask.stopTimer();
    }

    public PomodoroTimerTask getPomodoroTimerTask() {
        return this.pomodoroTimerTask;
    }
}
