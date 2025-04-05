import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import Interfaces.ExecutionStrategy;
import Interfaces.Job;
import Interfaces.JobListener;

class JobExecutor {
    private final ExecutionStrategy strategy;
    private final JobQueue jobQueue;
    private final Map<String, List<String>> dependencies;
    private final Set<String> completedJobs;
    private final List<JobListener> listeners;
    private static final int MAX_RETRIES = 3;
    private final ReentrantLock lock = new ReentrantLock();

    public JobExecutor(ExecutionStrategy strategy, JobQueue jobQueue, Map<String, List<String>> dependencies,
            Set<String> completedJobs, List<JobListener> listeners) {
        this.strategy = strategy;
        this.jobQueue = jobQueue;
        this.dependencies = dependencies;
        this.completedJobs = completedJobs;
        this.listeners = listeners;
    }

    public void start() {
        while (true) {
            Job job = null;
            lock.lock();
            try {
                if (!jobQueue.isEmpty()) {
                    job = jobQueue.getNextJob();
                    if (job != null && !canExecute(job)) {
                        jobQueue.addJob(job); // add back if can't execute as all dependencies are not yet executed
                        job = null;
                    }
                } else 
                    break;
            } finally {
                lock.unlock();
            }

            if (job != null) {
                notifyListeners(job.getJobId(), "STARTED");
                executeWithDependencies(job, 1);
                notifyListeners(job.getJobId(), "COMPLETED");
            }
        }
    }

    private boolean canExecute(Job job) {
        if (!dependencies.containsKey(job.getJobId())) {
            return true;
        }
        for (String dep : dependencies.get(job.getJobId())) {
            if (!completedJobs.contains(dep)) {
                return false;
            }
        }
        return true;
    }

    private void executeWithDependencies(Job job, int attempt) {
        try {
            strategy.execute(job);
            System.out.println("Job " + job.getJobId() + " executed successfully");
            completedJobs.add(job.getJobId());
        } catch (Exception e) {
            if (attempt < MAX_RETRIES) {
                int delay = (int) Math.pow(2, attempt);
                System.out.println("Job " + job.getJobId() + " failed. Retrying in " + delay + " seconds...");
                ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                scheduler.schedule(() -> executeWithDependencies(job, attempt + 1), delay, TimeUnit.SECONDS);
            } else {
                System.out.println(
                        "Job " + job.getJobId() + " failed permanently. Rolling back and adding back to queue.");
                try {
                    lock.lock();
                    jobQueue.addJob(job);
                } finally {
                    lock.unlock();
                }
                notifyListeners(job.getJobId(), "FAILED");
            }
        }
    }

    private void notifyListeners(String jobId, String status) {
        for (JobListener listener : listeners) {
            listener.onJobStatusChanged(jobId, status);
        }
    }
}