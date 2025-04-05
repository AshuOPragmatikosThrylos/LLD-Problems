package jobImpl;

import Interfaces.Job;

public class EmailJob implements Job {
    private String jobId;
    private int priority;

    public EmailJob(String jobId, int priority) {
        this.jobId = jobId;
        this.priority = priority;
    }

    @Override
    public String getJobId() {
        return jobId;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void execute() {
        System.out.println("Executing Email Job: " + jobId);
    }

    @Override
    public void run() {
        execute();
    }
}
