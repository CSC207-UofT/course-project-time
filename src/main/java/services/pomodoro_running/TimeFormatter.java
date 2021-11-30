package services.pomodoro_running;

import java.util.concurrent.TimeUnit;

public class TimeFormatter {
    public String formatTime(double elapsedTime, long currentDuration) {
        double totalTime = (double) TimeUnit.SECONDS.convert(currentDuration, TimeUnit.MINUTES);
        double timeLeft = totalTime - (double)TimeUnit.SECONDS.convert((long)elapsedTime, TimeUnit.NANOSECONDS);

        String seconds = Integer.toString((int)timeLeft % 60);
        String minutes = Integer.toString(((int)timeLeft % 3600)/60);

        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        else if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }

        return minutes + ':' + seconds;
    }
}
