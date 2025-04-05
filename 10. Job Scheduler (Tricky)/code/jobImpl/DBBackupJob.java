package jobImpl;

import Interfaces.Job;

public class DBBackupJob implements Job {
    private String jobId;
    private int priority;
    
    public DBBackupJob(String jobId, int priority) {
        this.jobId = jobId;
        this.priority = priority;
    }
    
    public void execute() { System.out.println("Executing DBBackupJob: " + jobId); }
    public String getJobId() { return jobId; }
    public int getPriority() { return priority; }

    @Override
    public void run() {
        execute();
    }
} 

