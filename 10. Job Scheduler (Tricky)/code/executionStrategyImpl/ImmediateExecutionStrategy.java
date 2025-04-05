package executionStrategyImpl;

import Interfaces.ExecutionStrategy;
import Interfaces.Job;

public class ImmediateExecutionStrategy implements ExecutionStrategy {
    public void execute(Job job) { job.execute(); }
}