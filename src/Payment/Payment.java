package Payment;

public class Payment {

	private long paymentID;
	private String paymentType;
	private double priceOfJob;

	public long getPaymentID() {
		return this.paymentID;
	}

	/**
	 * 
	 * @param paymentID
	 */
	public void setPaymentID(long paymentID) {
		this.paymentID = paymentID;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	/**
	 * 
	 * @param paymentType
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getPriceOfJob() {
		return this.priceOfJob;
	}

	/**
	 * 
	 * @param paymentID
	 * @param paymentType
	 * @param priceOfJob
	 */
	public Payment(long paymentID, String paymentType, double priceOfJob) {
		// TODO - implement Payment.Payment
		throw new UnsupportedOperationException();
	}

}