package Job;

public class Job {

	private long jobID;
	private string name;
	private boolean status;
	private date startDate;
	private date finishDate;
	private double price;
	private string jobDetails;
	private spare_Parts partsNeed;
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
	public Job(long jobID, string name, boolean status, date startDate, date finishDate, double price, string jobDetails, SparePart partsNeed, double duration) {
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

	public string getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(string name) {
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

	public date getStartDate() {
		return this.startDate;
	}

	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(date startDate) {
		this.startDate = startDate;
	}

	public date getFinishDate() {
		return this.finishDate;
	}

	/**
	 * 
	 * @param finishDate
	 */
	public void setFinishDate(date finishDate) {
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

	public string getJobDetails() {
		return this.jobDetails;
	}

	/**
	 * 
	 * @param jobDetails
	 */
	public void setJobDetails(string jobDetails) {
		this.jobDetails = jobDetails;
	}

	public void partsAvaliable() {
		// TODO - implement Job.partsAvaliable
		throw new UnsupportedOperationException();
	}

}