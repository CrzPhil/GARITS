package Job;

public class SparePartsCollection {

	private int index = 0;
	private int partsInStock;
	private string partLocation;

	public string getPartLocation() {
		return this.partLocation;
	}

	/**
	 * 
	 * @param partLocation
	 */
	public void setPartLocation(string partLocation) {
		this.partLocation = partLocation;
	}

	public int getPartsInStock() {
		return this.partsInStock;
	}

	/**
	 * 
	 * @param partsInStock
	 */
	public void setPartsInStock(int partsInStock) {
		this.partsInStock = partsInStock;
	}

	public SparePartsCollection stockReport() {
		// TODO - implement SparePartsCollection.stockReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param partID
	 * @param type
	 * @param name
	 * @param purchaseDate
	 */
	public SparePart addItem(long partID, string type, string name, date purchaseDate) {
		// TODO - implement SparePartsCollection.addItem
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param partID
	 */
	public boolean removeItem(long partID) {
		// TODO - implement SparePartsCollection.removeItem
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