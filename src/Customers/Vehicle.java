package Customers;

public class Vehicle {

	private String numberPlate;
	private String model;
	private String name;
	private String vehicleDescription;

	public String getNumberPlate() {
		return this.numberPlate;
	}

	/**
	 * 
	 * @param numberPlate
	 */
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public String getModel() {
		return this.model;
	}

	/**
	 * 
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getVehicleDescription() {
		return this.vehicleDescription;
	}

	/**
	 * 
	 * @param vehicleDescription
	 */
	public void setVehicleDescription(String vehicleDescription) {
		this.vehicleDescription = vehicleDescription;
	}

	/**
	 * 
	 * @param plateNo
	 * @param model
	 * @param name
	 * @param desc
	 */
	public Vehicle(String plateNo, String model, String name, String desc) {
		// TODO - implement Vehicle.Vehicle
		throw new UnsupportedOperationException();
	}

}