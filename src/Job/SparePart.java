package Job;

import java.util.Date;

public class SparePart {

	private String partID;
	private String type;
	private String manufacturer;
	private String name;
	private int year;
	private int stock;
	private double price;

	/**
	 *
	 * @param partID
	 * @param type
	 * @param name
	 * @param year
	 */
	public SparePart(String partID, String name, String manufacturer, String type, int year, int stock, double price) {
		this.type = type;
		this.name = name;
		this.year = year;
		this.manufacturer = manufacturer;
		this.stock = stock;
		this.price = price;
		this.partID = partID;
	}

	// This is how spare parts will be displayed in the JLists
	@Override
	public String toString() {
		return this.name + " " + this.manufacturer + " " +
				this.type + " " + this.year + " ￡" + this.price + " Stock: " + this.stock;
	}

	public String getType() {
		return type;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	// Used when displaying Spare Parts in GUI, when creating the JTable
	public String[] toData() {
		return new String[]{this.partID, this.name, this.manufacturer, this.type, String.valueOf(this.year), String.valueOf(this.stock), String.valueOf(this.price)};
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

	public SparePartsCollection checkStock() {
		// TODO - implement SparePart.checkStock
		throw new UnsupportedOperationException();
	}

	public String getPartID() {
		return this.partID;
	}

	/**
	 * 
	 * @param partID
	 */
	public void setPartID(String partID) {
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
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

}