package executionStrategyImpl;

import java.util.concurrent.TimeUnit;

import Interfaces.ExecutionStrategy;
import Interfaces.Job;

public class DelayedExecutionStrategy implements ExecutionStrategy {
    private long delay;
    private TimeUnit unit;

    public DelayedExecutionStrategy(long delay, TimeUnit unit) {
        this.delay = delay;
        this.unit = unit;
    }

    @Override
    public void execute(Job job) {
        try {
            unit.sleep(delay);
            job.execute();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

