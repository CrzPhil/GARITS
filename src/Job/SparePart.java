package Job;

public class SparePart {

	// ID when used in unison with a Job
	private int partID;
	// Code identifier of a part
	private String partCode;
	private String type;
	private String manufacturer;
	private String name;
	private int year;
	private int stock;
	private double price;
	private int threshold;

	/**
	 *
	 * @param partCode
	 * @param type
	 * @param name
	 * @param year
	 */
	public SparePart(String partCode, String name, String manufacturer, String type, int year, int stock, double price, int threshold) {
		this.type = type;
		this.name = name;
		this.year = year;
		this.manufacturer = manufacturer;
		this.stock = stock;
		this.price = price;
		this.partCode = partCode;
		this.threshold = threshold;
	}

	// Second constructor for when used in unison with a job.
	public SparePart(int partID, String partCode, String name, String manufacturer, String type, int year, int stock, double price) {
		this.partID = partID;
		this.type = type;
		this.name = name;
		this.year = year;
		this.manufacturer = manufacturer;
		this.stock = stock;
		this.price = price;
		this.partCode = partCode;
	}

	// This is how spare parts will be displayed in the JLists
	@Override
	public String toString() {
		return this.name + " " + this.manufacturer + " " +
				this.type + " " + this.year + " ￡" + this.price + " Stock: " + this.stock;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
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
		return new String[]{this.partCode, this.name, this.manufacturer, this.type, String.valueOf(this.year), String.valueOf(this.stock), String.valueOf(this.price), String.valueOf(this.threshold)};
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

	public String getPartCode() {
		return this.partCode;
	}

	/**
	 * 
	 * @param partCode
	 */
	public void setPartCode(String partCode) {
		this.partCode = partCode;
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

	public int getPartID() {
		return partID;
	}
}