import java.util.concurrent.PriorityBlockingQueue;

import Interfaces.Job;

public class JobQueue {
    private final PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>(10, 
        (j1, j2) -> Integer.compare(j2.getPriority(), j1.getPriority()));

    public void addJob(Job job) {
        queue.add(job);
    }

    public Job getNextJob() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    public boolean removeJob(Job job) {
        return queue.remove(job);
    }
}