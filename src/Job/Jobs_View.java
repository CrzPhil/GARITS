package src.Job;

import java.util.Date;

public class Jobs_View {

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
	public Job.Job createJob(long jobID, String name, boolean status, Date startDate, Date finishDate, double price, String jobDetails, Job.SparePart partsNeed, double duration) {
		// TODO - implement Jobs_View.createJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public Job.Job getJob(long jobID) {
		// TODO - implement Jobs_View.getJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param partID
	 * @param type
	 * @param name
	 * @param purchaseDate
	 */
	public Job.SparePart createSparePart(long partID, String type, String name, Date purchaseDate) {
		// TODO - implement Jobs_View.createSparePart
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param partID
	 */
	public void modifySparePart(long partID) {
		// TODO - implement Jobs_View.modifySparePart
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param partID
	 */
	public boolean deleteSparePart(long partID) {
		// TODO - implement Jobs_View.deleteSparePart
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public void modifyJob(long jobID) {
		// TODO - implement Jobs_View.modifyJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public boolean deleteJob(long jobID) {
		// TODO - implement Jobs_View.deleteJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bookingID
	 * @param date
	 * @param jobType
	 */
	public Job.Booking createBooking(long bookingID, Date date, String jobType) {
		// TODO - implement Jobs_View.createBooking
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bookinID
	 */
	public void modifyBooking(long bookinID) {
		// TODO - implement Jobs_View.modifyBooking
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bookingID
	 */
	public boolean deleteBooking(long bookingID) {
		// TODO - implement Jobs_View.deleteBooking
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param partID
	 */
	public boolean orderSparePart(long partID) {
		// TODO - implement Jobs_View.orderSparePart
		throw new UnsupportedOperationException();
	}

}