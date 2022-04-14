package Job;

import java.util.Date;

/**
 * Controller for jobs.
 */
public class Job_Controller implements I_Job {
	private final SQL_JobHelper jobHelper = new SQL_JobHelper();
	private final SQL_PartsHelper partsHelper = new SQL_PartsHelper();

	/**
	 * Get all Jobs
	 * @return Array of all jobs -> Job[]
	 */
	public Job[] getJobs() {
		return jobHelper.getJobs();
	}

	/**
	 * Get all completed jobs
	 * @return Array of all completed jobs -> Job[]
	 */
	public Job[] getCompletedJobs() {
		return jobHelper.getCompletedJobs();
	}

	/**
	 * Get ID of a newly generated job.
	 * @param jobType type
	 * @param duration duration
	 * @param dates date
	 * @param motNo MOT Number
	 * @param mileage Mileage
	 * @param additionalInfo Tasks
	 * @param completionStatus Complete / Incomplete
	 * @param regNo Registration Number
	 * @return int -> jobID
	 */
	public int getJobID(String jobType, float duration, String dates, String motNo, int mileage, String additionalInfo, String completionStatus, String regNo) {
		return jobHelper.getJobID(jobType, duration, dates, motNo, mileage, additionalInfo, completionStatus, regNo);
	}

	/**
	 * Get All spare parts
	 * @return Array of spare parts
	 */
	public SparePart[] getAllParts() {
		return partsHelper.getAllParts();
	}

	/**
	 * Get all spare parts relating to the Vehicle type/make
	 * @param regNo Registration Number
	 * @return Spare Part array
	 */
	public SparePart[] getSpecificParts(String regNo) {
		return partsHelper.getSpecificParts(regNo);
	}

	/**
	 * Update Stock of an item
	 * @param stock New stock amount
	 * @param partCode Code of the item
	 */
	public void updateStock(int stock, String partCode) {
		partsHelper.updateStock(stock, partCode);
	}

	/**
	 * Create a new job -> Default to incomplete status
	 * @param jobType Type
	 * @param duration Duration
	 * @param dates Date
	 * @param motNo Mot No
	 * @param mileage Mileage
	 * @param additionalInfo Tasks
	 * @param completionStatus Status -> Incomplete
	 * @param regNo Registration No of vehicle
	 */
	public void createJob(String jobType, float duration, String dates, String motNo, int mileage, String additionalInfo, String completionStatus, String regNo) {
		jobHelper.createJob(jobType, duration, dates, motNo, mileage, additionalInfo, "Incomplete", regNo);
	}

	/**
	 * Get all parts assigned to a job
	 * @param jobID ID of Job
	 * @return Spare Part array
	 */
	public SparePart[] getJobParts(int jobID) {
		return partsHelper.getJobParts(jobID);
	}

	/**
	 * Create a job-part entry in the Job_SpareParts Table
	 * @param jobID ID of Job
	 * @param partID ID of Part
	 */
	public void addToJob(int jobID, String partID) {
		jobHelper.addToJob(jobID, partID);
	}

	/**
	 * Delete part from job
	 * @param partID ID of Part
	 */
	public void deleteJobPart(int partID) {
		partsHelper.deleteJobPart(partID);
	}

}