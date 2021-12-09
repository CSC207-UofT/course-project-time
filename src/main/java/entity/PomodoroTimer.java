package entity;

public class PomodoroTimer {
    private long startTime;
    private boolean isWork;
    private long breakDuration;
    private long workDuration;
    private boolean newStart;

    public PomodoroTimer(long startTime, boolean isWork, long breakDuration, long workDuration, boolean newStart) {
        this.startTime = startTime;
        this.isWork = isWork;
        this.breakDuration = breakDuration;
        this.workDuration = workDuration;
        this.newStart = newStart;
    }

    public long getStartTime() {
        return startTime;
    }

    public boolean getIsWork() {
        return isWork;
    }

    public long getBreakDuration() {
        return breakDuration;
    }

    public long getWorkDuration() {
        return workDuration;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
