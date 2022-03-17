import Job.*;

public interface I_Job {

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
	abstract Job createJob(long jobID, string name, boolean status, date startDate, date finishDate, double price, string jobDetails, SparePart partsNeed, double duration);

	/**
	 * 
	 * @param jobID
	 */
	abstract Job getJob(long jobID);

	/**
	 * 
	 * @param partID
	 * @param type
	 * @param name
	 * @param purchaseDate
	 */
	abstract SparePart createSparePart(long partID, string type, string name, date purchaseDate);

	/**
	 * 
	 * @param partID
	 */
	abstract void modifySparePart(long partID);

	/**
	 * 
	 * @param partID
	 */
	abstract boolean deleteSparePart(long partID);

	/**
	 * 
	 * @param jobID
	 */
	abstract void modifyJob(long jobID);

	/**
	 * 
	 * @param jobID
	 */
	abstract boolean deleteJob(long jobID);

	/**
	 * 
	 * @param bookingID
	 * @param date
	 * @param jobType
	 */
	abstract Booking createBooking(long bookingID, date date, string jobType);

	/**
	 * 
	 * @param bookingID
	 */
	abstract void modifyBooking(long bookingID);

	/**
	 * 
	 * @param bookingID
	 */
	abstract boolean deleteBooking(long bookingID);

	/**
	 * 
	 * @param partID
	 */
	boolean orderSparePart(long partID);

}