package Job;

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
	public Job createJob(long jobID, string name, boolean status, date startDate, date finishDate, double price, string jobDetails, SparePart partsNeed, double duration) {
		// TODO - implement Jobs_View.createJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public Job getJob(long jobID) {
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
	public SparePart createSparePart(long partID, string type, string name, date purchaseDate) {
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
	public Booking createBooking(long bookingID, date date, string jobType) {
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