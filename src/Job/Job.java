package Job;

import java.util.Date;

public class Job {

	private long jobID;
	private String name;
	private boolean status;
	private Date startDate;
	private Date finishDate;
	private double price;
	private String jobDetails;
	private SparePartsCollection partsNeed;
	private double duration;

	/**
	 * 
	 * @param jobID
	 * @param name
	 * @param status
	 * @param startDate
	 * @param finishDate
	 * @param price
	 * @param jobDetails
	 * @param partsNeed
	 * @param duration
	 */
	public Job(long jobID, String name, boolean status, Date startDate, Date finishDate, double price, String jobDetails, SparePart partsNeed, double duration) {
		// TODO - implement Job.Job
		throw new UnsupportedOperationException();
	}

	public void update_on_job() {
		// TODO - implement Job.update_on_job
		throw new UnsupportedOperationException();
	}

	public void show_job_status() {
		// TODO - implement Job.show_job_status
		throw new UnsupportedOperationException();
	}

	public long getJobID() {
		return this.jobID;
	}

	/**
	 * 
	 * @param jobID
	 */
	public void setJobID(long jobID) {
		this.jobID = jobID;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public boolean getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return this.finishDate;
	}

	/**
	 * 
	 * @param finishDate
	 */
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public double getPrice() {
		return this.price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	public String getJobDetails() {
		return this.jobDetails;
	}

	/**
	 * 
	 * @param jobDetails
	 */
	public void setJobDetails(String jobDetails) {
		this.jobDetails = jobDetails;
	}

	public void partsAvaliable() {
		// TODO - implement Job.partsAvaliable
		throw new UnsupportedOperationException();
	}

}