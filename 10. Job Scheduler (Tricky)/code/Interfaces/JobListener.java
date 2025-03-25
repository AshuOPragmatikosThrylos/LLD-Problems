package Interfaces;

// Observer Pattern (Job Status Notifications)
public interface JobListener {
    void onJobStatusChanged(String jobId, String status);
}
