package Job;

public class Booking {

	private long bookingID;
	private date date;
	private string jobType;

	/**
	 * 
	 * @param bookingID
	 * @param date
	 * @param jobType
	 */
	public Booking(long bookingID, date date, string jobType) {
		// TODO - implement Booking.Booking
		throw new UnsupportedOperationException();
	}

	public long getBookingID() {
		return this.bookingID;
	}

	/**
	 * 
	 * @param bookingID
	 */
	public void setBookingID(long bookingID) {
		this.bookingID = bookingID;
	}

	public date getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(date date) {
		this.date = date;
	}

	public string getJobType() {
		return this.jobType;
	}

	/**
	 * 
	 * @param jobType
	 */
	public void setJobType(string jobType) {
		this.jobType = jobType;
	}

}