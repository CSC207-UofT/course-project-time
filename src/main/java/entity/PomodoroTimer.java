package main.java.entity;

public class PomodoroTimer {
    private int workLength;
    private int breakLength;
    private boolean isWorking = false;
    private boolean isOnBreak = false;

    /***
     *
     * @param workLength  the length of time in minutes the user wants to work for
     * @param breakLength the length of time in minutes the user wants to break for
     */
    public PomodoroTimer(int workLength, int breakLength) {
        this.workLength = workLength;
        this.breakLength = breakLength;
    }

    /***
     * default constructor if the user doesn't specify lengths of time
     */
    public PomodoroTimer() {
        this.workLength = 25;
        this.breakLength = 5;
    }
}
