package Customers;

public class Vehicle {

	private string numberPlate;
	private string model;
	private string name;
	private string vehicleDescription;

	public string getNumberPlate() {
		return this.numberPlate;
	}

	/**
	 * 
	 * @param numberPlate
	 */
	public void setNumberPlate(string numberPlate) {
		this.numberPlate = numberPlate;
	}

	public string getModel() {
		return this.model;
	}

	/**
	 * 
	 * @param model
	 */
	public void setModel(string model) {
		this.model = model;
	}

	public string getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(string name) {
		this.name = name;
	}

	public string getVehicleDescription() {
		return this.vehicleDescription;
	}

	/**
	 * 
	 * @param vehicleDescription
	 */
	public void setVehicleDescription(string vehicleDescription) {
		this.vehicleDescription = vehicleDescription;
	}

	/**
	 * 
	 * @param plateNo
	 * @param model
	 * @param name
	 * @param desc
	 */
	public Vehicle(string plateNo, string model, string name, string desc) {
		// TODO - implement Vehicle.Vehicle
		throw new UnsupportedOperationException();
	}

}