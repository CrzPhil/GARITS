package Payment;

public class Payment_Collection {

	private int index = 0;

	public int getIndex() {
		return this.index;
	}

	/**
	 * 
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	public Payment_Collection() {
		// TODO - implement Payment_Collection.Payment_Collection
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param paymentID
	 * @param paymentType
	 * @param priceOfJob
	 */
	public Payment addItem(long paymentID, String paymentType, double priceOfJob) {
		// TODO - implement Payment_Collection.addItem
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param paymentID
	 */
	public boolean removeItem(long paymentID) {
		// TODO - implement Payment_Collection.removeItem
		throw new UnsupportedOperationException();
	}

}