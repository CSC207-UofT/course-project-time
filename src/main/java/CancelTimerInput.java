package main.java;

import java.util.Objects;
import java.util.Scanner;

public class CancelTimerInput implements Runnable {
    private boolean cancel = false;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            if (Objects.equals(scanner.nextLine(), "c")) {
                this.cancel = true;
            }
        }
    }

    public boolean getCancel() {
        return this.cancel;
    }
}
