package Job;

import java.util.Date;

public class Booking {

	private long bookingID;
	private Date date;
	private String jobType;

	public Booking(long bookingID, Date date, String jobType) {
		// TODO - implement Booking.Booking
		throw new UnsupportedOperationException();
	}

	public long getBookingID() {
		return this.bookingID;
	}

	public void setBookingID(long bookingID) {
		this.bookingID = bookingID;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getJobType() {
		return this.jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

}