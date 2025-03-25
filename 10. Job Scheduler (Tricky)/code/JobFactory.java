import Interfaces.Job;
import jobImpl.*;

// Factory Pattern
class JobFactory {
    public static Job createJob(String type, String jobId, int priority) {
        switch (type.toLowerCase()) {
            case "email": return new EmailJob(jobId, priority);
            case "backup": return new DBBackupJob(jobId, priority);
            default: throw new IllegalArgumentException("Invalid job type");
        }
    }
}