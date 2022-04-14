package Payment;

public class Payment {

	private long paymentID;
	private String paymentType;
	private double priceOfJob;

	public Payment(long paymentID, String paymentType, double priceOfJob) {
		// TODO - implement Payment.Payment
		throw new UnsupportedOperationException();
	}

	// Getters and Setters
	public long getPaymentID() {
		return this.paymentID;
	}

	public void setPaymentID(long paymentID) {
		this.paymentID = paymentID;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getPriceOfJob() {
		return this.priceOfJob;
	}

}