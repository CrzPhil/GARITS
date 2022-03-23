package Job;

public class Booking {

	private long bookingID;
	private date date;
	private String jobType;

	/**
	 * 
	 * @param bookingID
	 * @param date
	 * @param jobType
	 */
	public Booking(long bookingID, date date, String jobType) {
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

	public String getJobType() {
		return this.jobType;
	}

	/**
	 * 
	 * @param jobType
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

}