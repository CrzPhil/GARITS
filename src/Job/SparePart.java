package Job;

import java.util.Date;

public class SparePart {

	private long partID;
	private String type;
	private String name;
	private Date purchaseDate;

	/**
	 * 
	 * @param partID
	 * @param type
	 * @param name
	 * @param purchaseDate
	 */
	public SparePart(long partID, String type, String name, Date purchaseDate) {
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
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param purchaseDate
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}