import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import Interfaces.ExecutionStrategy;
import Interfaces.Job;
import Interfaces.JobListener;
import executionStrategyImpl.DelayedExecutionStrategy;
import executionStrategyImpl.ImmediateExecutionStrategy;

// BillPugh Singleton
public class JobScheduler {

    private static class Holder {
        private static final JobScheduler INSTANCE = new JobScheduler();
    }

    public static JobScheduler getInstance() {
        return Holder.INSTANCE;
    }

    private ScheduledExecutorService executorService;
    private Map<String, List<String>> dependencies;
    private Set<String> completedJobs;
    private List<JobListener> listeners;
    private JobQueue jobQueue;
    private final Map<String, ScheduledFuture<?>> recurringJobs = new ConcurrentHashMap<>();
    private final Map<String, Boolean> jobStatus = new ConcurrentHashMap<>();

    private JobScheduler() {
        executorService = Executors.newScheduledThreadPool(5);
        dependencies = new HashMap<>();
        completedJobs = new HashSet<>();
        listeners = new ArrayList<>();
        jobQueue = new JobQueue();
    }

    public void addListener(JobListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String jobId, String status) {
        for (JobListener listener : listeners) {
            listener.onJobStatusChanged(jobId, status);
        }
    }

    public void addDependency(String jobId, String dependentJobId) {
        dependencies.computeIfAbsent(jobId, k -> new ArrayList<>()).add(dependentJobId);
    }

    public void scheduleJob(Job job, long delay, TimeUnit unit) {
        ExecutionStrategy strategy = (delay > 0) ? new DelayedExecutionStrategy(delay, unit)
                : new ImmediateExecutionStrategy();
        JobExecutor executor = new JobExecutor(strategy);

        jobQueue.addJob(job);

        executorService.schedule(() -> {
            Job nextJob = jobQueue.getNextJob();
            if (nextJob != null) {
                executeWithDependencies(nextJob, executor);
            }
        }, delay, unit);
    }

    public void scheduleRecurringJob(Job job, long initialDelay, long period, TimeUnit unit, long stopAfterSeconds) {
        ScheduledFuture<?> future = executorService.scheduleAtFixedRate(job::execute, initialDelay, period, unit);
        recurringJobs.put(job.getJobId(), future); // Track the job

        executorService.schedule(() -> {
            System.out.println("Stopping recurring job: " + job.getJobId());
            future.cancel(false); // Cancel recurring job execution
            recurringJobs.remove(job.getJobId()); // Remove from tracking

            if (recurringJobs.isEmpty()) {
                executorService.shutdown(); // Shutdown if no jobs are left
            }
        }, stopAfterSeconds, TimeUnit.SECONDS);
    }

    private void executeWithDependencies(Job job, JobExecutor executor) {
        if (dependencies.containsKey(job.getJobId()) && !dependencies.get(job.getJobId()).isEmpty()) {
            for (String dep : dependencies.get(job.getJobId())) {
                while (!completedJobs.contains(dep)) { // Wait for the dependency to finish
                    System.out.println("Waiting for dependent job: " + dep);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        System.out.println("Executing " + job.getJobId());
        notifyListeners(job.getJobId(), "STARTED");
        executor.executeJob(job, 1);
        jobStatus.put(job.getJobId(), true);
        completedJobs.add(job.getJobId());
        notifyListeners(job.getJobId(), "COMPLETED");
    }

    public boolean isJobCompleted(String jobId) {
        return jobStatus.getOrDefault(jobId, false);
    }
}
