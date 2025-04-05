import Interfaces.JobListener;

class JobLogger implements JobListener {
    public void onJobStatusChanged(String jobId, String status) {
        System.out.println("Job " + jobId + " is now " + status);
    }
}