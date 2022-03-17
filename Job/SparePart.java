package Job;

public class SparePart {

	private long partID;
	private string type;
	private string name;
	private date purchaseDate;

	/**
	 * 
	 * @param partID
	 * @param type
	 * @param name
	 * @param purchaseDate
	 */
	public SparePart(long partID, string type, string name, date purchaseDate) {
		// TODO - implement SparePart.SparePart
		throw new UnsupportedOperationException();
	}

	public boolean orderPart() {
		// TODO - implement SparePart.orderPart
		throw new UnsupportedOperationException();
	}

	public boolean reorderPart() {
		// TODO - implement SparePart.reorderPart
		throw new UnsupportedOperationException();
	}

	public boolean usePart() {
		// TODO - implement SparePart.usePart
		throw new UnsupportedOperationException();
	}

	public void enterPartInfo() {
		// TODO - implement SparePart.enterPartInfo
		throw new UnsupportedOperationException();
	}

	public stock checkStock() {
		// TODO - implement SparePart.checkStock
		throw new UnsupportedOperationException();
	}

	public long getPartID() {
		return this.partID;
	}

	/**
	 * 
	 * @param partID
	 */
	public void setPartID(long partID) {
		this.partID = partID;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(string type) {
		this.type = type;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(string name) {
		this.name = name;
	}

	/**
	 * 
	 * @param purchaseDate
	 */
	public void setPurchaseDate(date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}