package Interfaces;

public interface Job extends Runnable {
    String getJobId();
    int getPriority();
    void execute();
}

