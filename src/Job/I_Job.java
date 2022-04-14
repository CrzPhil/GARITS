package Job;

import Job.*;

import java.time.LocalDate;
import java.util.Date;

public interface I_Job {

    abstract void createJob(String jobType, float duration, String dates, String motNo, int mileage, String additionalInfo, String completionStatus, String regNo);

    abstract Job[] getJobs();

    abstract Job[] getCompletedJobs();

    abstract int getJobID(String jobType, float duration, String dates, String motNo, int mileage, String additionalInfo, String completionStatus, String regNo);

    abstract SparePart[] getAllParts();

    abstract SparePart[] getSpecificParts(String regNo);

    abstract void updateStock(int stock, String partCode);

    abstract SparePart[] getJobParts(int jobID);

    abstract void addToJob(int jobID, String partID);

    abstract void deleteJobPart(int partID);

}