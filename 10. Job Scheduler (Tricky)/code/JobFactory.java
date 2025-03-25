import Interfaces.Job;
import jobImpl.*;

// Factory Pattern
class JobFactory {
    public static Job createJob(String type, String jobId) {
        switch (type.toLowerCase()) {
            case "email": return new EmailJob(jobId);
            case "backup": return new DBBackupJob(jobId);
            default: throw new IllegalArgumentException("Invalid job type");
        }
    }
}