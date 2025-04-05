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
import java.util.concurrent.locks.ReentrantLock;

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
    private final ReentrantLock queueLock = new ReentrantLock();

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

    public void addDependency(String jobId, String dependentJobId) {
        dependencies.computeIfAbsent(jobId, k -> new ArrayList<>()).add(dependentJobId);
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

    public boolean isJobCompleted(String jobId) {
        return jobStatus.getOrDefault(jobId, false);
    }

    public void scheduleJob(Job job, long delay, TimeUnit unit) {
        executorService.schedule(() -> {
            queueLock.lock();
            try {
                jobQueue.addJob(job);
            } finally {
                queueLock.unlock();
            }
        }, delay, unit);
    }

    public void startExecution(ExecutionStrategy strategy) {
        JobExecutor executor = new JobExecutor(strategy, jobQueue, dependencies, completedJobs, listeners);
        new Thread(executor::start).start();
        // Thread executionThread = new Thread(executor::start);
        // executionThread.setDaemon(true); // Allows main thread to exit when the Priority Queue is empty
        // executionThread.start();
    }
}