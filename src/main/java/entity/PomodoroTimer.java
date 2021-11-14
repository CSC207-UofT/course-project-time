package main.java.entity;

public class PomodoroTimer {
    private int workLength;
    private int breakLength;
    private boolean canceled;
    private boolean isWorking;

    /***
     *
     * @param workLength  the length of time in minutes the user wants to work for
     * @param breakLength the length of time in minutes the user wants to break for
     */
    public PomodoroTimer(int workLength, int breakLength) {
        this.workLength = workLength;
        this.breakLength = breakLength;
        this.canceled = false;
        this.isWorking = true;
    }

    public int getWorkLength() {
        return this.workLength;
    }

    public int getBreakLength() {
        return this.breakLength;
    }

    public boolean getCanceled() {
        return this.canceled;
    }

    public boolean getIsWorking() {
        return this.isWorking;
    }

    public void setCanceled(boolean isCanceled) {
        this.canceled = isCanceled;
    }

    public void setWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }
}
