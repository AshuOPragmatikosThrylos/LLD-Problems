package jobImpl;

import Interfaces.Job;

public class DBBackupJob implements Job {
    private String jobId;
    
    public DBBackupJob(String jobId) {
        this.jobId = jobId;
    }
    
    public void execute() { System.out.println("Executing DBBackupJob: " + jobId); }
    public String getJobId() { return jobId; }

    @Override
    public void run() {
        execute();
    }
}