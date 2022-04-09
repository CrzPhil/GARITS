import Job.Job;
import Job.Job_Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class main {

    public static void main(String[] args)  {
/*        Job_Controller c = new Job_Controller();
        Job[] jobs = c.getJobs();
        for (Job job : jobs) {
            System.out.println(job);
        }*/
        ViewJobsGUI.main();
        // ItemSearchGUI.main();
    }

}

