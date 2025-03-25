import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Interfaces.ExecutionStrategy;
import Interfaces.Job;

class JobExecutor {
   private ExecutionStrategy strategy;

    public JobExecutor(ExecutionStrategy strategy) {
        this.strategy = strategy;
    }

    private static final int MAX_RETRIES = 3;

    public void executeJob(Job job, int attempt) {
        try {
            strategy.execute(job);
            System.out.println("Job " + job.getJobId() + " executed successfully");
        } catch (Exception e) {
            if (attempt < MAX_RETRIES) {
                int delay = (int) Math.pow(2, attempt); // Retry Mechanism (Exponential Backoff)
                System.out.println("Job " + job.getJobId() + " failed. Retrying in " + delay + " seconds...");
                ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                scheduler.schedule(() -> executeJob(job, attempt + 1), delay, TimeUnit.SECONDS);
            } else {
                System.out.println("Job " + job.getJobId() + " failed permanently after retries.");
            }
        }
    }
}