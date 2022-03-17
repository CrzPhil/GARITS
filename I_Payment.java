import Payment.*;

public interface I_Payment {

	/**
	 * 
	 * @param paymentID
	 * @param paymentType
	 * @param priceOfJob
	 */
	abstract Payment createPayment(long paymentID, string paymentType, double priceOfJob);

	/**
	 * 
	 * @param paymentID
	 */
	abstract Payment getPayment(long paymentID);

	/**
	 * 
	 * @param paymentID
	 */
	abstract boolean deletePayment(long paymentID);

}