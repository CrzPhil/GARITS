package Job;

import java.util.Date;

public class SparePartsCollection {

	private int index = 0;
	private int partsInStock;
	private String partLocation;

	public String getPartLocation() {
		return this.partLocation;
	}

	/**
	 * 
	 * @param partLocation
	 */
	public void setPartLocation(String partLocation) {
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
	public SparePart addItem(long partID, String type, String name, Date purchaseDate) {
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