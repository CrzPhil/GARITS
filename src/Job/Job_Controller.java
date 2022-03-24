package Job;

import Job.*;

import java.util.Date;

public class Job_Controller implements I_Job {

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

}