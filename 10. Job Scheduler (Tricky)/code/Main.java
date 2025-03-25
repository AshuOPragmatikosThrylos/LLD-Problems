import java.util.concurrent.TimeUnit;
import Interfaces.Job;

public class Main {
    public static void main(String[] args) {
        JobScheduler scheduler = JobScheduler.getInstance();
        scheduler.addListener(new JobLogger());

        Job emailJob = JobFactory.createJob("email", "job1");
        Job backupJob = JobFactory.createJob("backup", "job2");

        scheduler.addDependency("job2", "job1"); // job2 depends on job1
        scheduler.scheduleJob(emailJob, 0, TimeUnit.SECONDS);
        scheduler.scheduleJob(backupJob, 0, TimeUnit.SECONDS); // Schedule normally 

        Job recurringJob = JobFactory.createJob("email", "recurring1");
        scheduler.scheduleRecurringJob(recurringJob, 5, 10, TimeUnit.SECONDS, 30);
    }
}
