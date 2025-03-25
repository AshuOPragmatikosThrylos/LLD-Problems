package Interfaces;

public interface Job extends Runnable {
    String getJobId();
    void execute();
}