package Job;

import java.util.Date;

public class Job_Controller implements I_Job {
	private final SQL_JobHelper jobHelper = new SQL_JobHelper();
	private final SQL_PartsHelper partsHelper = new SQL_PartsHelper();

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
	public Job createJob(long jobID, String name, boolean status, Date startDate, Date finishDate, double price, String jobDetails, SparePart partsNeed, double duration) {
		// TODO - implement Job_Controller.createJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public Job getJob(long jobID) {
		// TODO - implement Job_Controller.getJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param partID
	 * @param type
	 * @param name
	 * @param purchaseDate
	 */
	public SparePart createSparePart(long partID, String type, String name, Date purchaseDate) {
		// TODO - implement Job_Controller.createSparePart
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param partID
	 */
	public void modifySparePart(long partID) {
		// TODO - implement Job_Controller.modifySparePart
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param partID
	 */
	public boolean deleteSparePart(long partID) {
		// TODO - implement Job_Controller.deleteSparePart
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public void modifyJob(long jobID) {
		// TODO - implement Job_Controller.modifyJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public boolean deleteJob(long jobID) {
		// TODO - implement Job_Controller.deleteJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bookingID
	 * @param date
	 * @param jobType
	 */
	public Booking createBooking(long bookingID, Date date, String jobType) {
		// TODO - implement Job_Controller.createBooking
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bookingID
	 */
	public void modifyBooking(long bookingID) {
		// TODO - implement Job_Controller.modifyBooking
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bookingID
	 */
	public boolean deleteBooking(long bookingID) {
		// TODO - implement Job_Controller.deleteBooking
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param partID
	 */
	public boolean orderSparePart(long partID) {
		// TODO - implement Job_Controller.orderSparePart
		throw new UnsupportedOperationException();
	}

	public Job[] getJobs() {
		return jobHelper.getJobs();
	}

	public int getJobID(String jobType, float duration, String dates, String parts, String motNo, int mileage, float price, String additionalInfo, String completionStatus) {
		return jobHelper.getJobID(jobType, duration, dates, parts, motNo, mileage, price, additionalInfo, completionStatus);
	}

	public String[] getPartNames() {
		return partsHelper.getPartNames();
	}

	public SparePart[] getAllParts() {
		return partsHelper.getAllParts();
	}

	public void updateStock(int stock, String partID) {
		partsHelper.updateStock(stock, partID);
	}

	// Send job creation data
	public void sendData(String jobType, float duration, String dates, String parts, String motNo, int mileage, float price, String additionalInfo, String completionStatus) {
		jobHelper.sendData(jobType, duration, dates, parts, motNo, mileage, price, additionalInfo, "Incomplete");
	}

	// Get all parts assigned to a job
	public SparePart[] getJobParts(int jobID) {
		return partsHelper.getJobParts(jobID);
	}
	// Create a job-part entry in the Job_SpareParts Table
	public void addToJob(int jobID, String partID) {
		jobHelper.addToJob(jobID, partID);
	}
}