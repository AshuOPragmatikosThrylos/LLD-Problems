package jobImpl;

import Interfaces.Job;

public class EmailJob implements Job {
    private String jobId;

    public EmailJob(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String getJobId() {
        return jobId;
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