package Job;

import java.util.Date;

public class Job {

	private long jobID;
	private long customerID;
	private String jobType;
	private Date startDate;
	private Date finishDate;
	private double price;
	private String motNO;
	private String parts;
	private double duration;
	private double mileage;

	/**
	 *
	 * @param jobID
	 * @param customerID
	 * @param jobType
	 * @param startDate
	 * @param finishDate
	 * @param price
	 * @param motNO
	 * @param parts
	 * @param duration
	 * @param mileage
	 */
	public Job() {
		// TODO - implement Job.Job
		System.out.println("Default Constructor");
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



	public void partsAvaliable() {
		// TODO - implement Job.partsAvaliable
		throw new UnsupportedOperationException();
	}
	public long getJobID() {
		return jobID;
	}

	public void setJobID(long jobID) {
		this.jobID = jobID;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getMotNO() {
		return motNO;
	}

	public void setMotNO(String motNO) {
		this.motNO = motNO;
	}

	public String getParts() {
		return parts;
	}

	public void setParts(String parts) {
		this.parts = parts;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

}