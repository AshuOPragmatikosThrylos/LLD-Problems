Handling deadlocks?

support for cron jobs - requires separate logic

What's included?
recurring jobs
ImmediateExecution, Delayed Execution - Strategies for JobExecutor

priority should be handled separately from execution strategies
JobExecutor should respect priority ordering while deciding which job to execute next
Execution strategies (ImmediateExecution, DelayedExecution) should only dictate execution behavior after a job has been picked

JobScheduler adds jobs in PQ
JobExecutor picks and executes jobs from PQ


Future Improvement:
1) ExecutionStrategy passed along at the time of job creation itself
Pro: More flexible and dynamic if jobs differ a lot
Con: Jobs now have more responsibility (violation of SRP — Single Responsibility Principle)

2) Involve PQ in recurring jobs