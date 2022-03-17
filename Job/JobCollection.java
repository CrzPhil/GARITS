package Job;

public class JobCollection {

	private int index = 0;

	public JobCollection() {
		// TODO - implement JobCollection.JobCollection
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param name
	 * @param status
	 * @param startDate
	 * @param finishDate
	 * @param price
	 * @param jobDetails
	 * @param partsNeeded
	 * @param duration
	 */
	public Job addItem(long jobID, string name, boolean status, date startDate, date finishDate, double price, string jobDetails, SparePart partsNeeded, double duration) {
		// TODO - implement JobCollection.addItem
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 */
	public boolean removeItem(long jobID) {
		// TODO - implement JobCollection.removeItem
		throw new UnsupportedOperationException();
	}

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

}