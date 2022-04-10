package Customers;

public class Vehicle {

	private String registrationNumber;
	private String make;
	private String model;
	private String engSerial;
	private String colour;
	private String motDate;
	private String chassisNumber;
	// Equivalent to Foreign Key; Owner of vehicle
	private Customer customer;


	public Vehicle(String registrationNumber, String make, String model, String engSerial, String chassisNumber, String colour,   String motDate, Customer customer) {
		this.registrationNumber = registrationNumber;
		this.make = make;
		this.model = model;
		this.engSerial = engSerial;
		this.colour = colour;
		this.motDate = motDate;
		this.chassisNumber = chassisNumber;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return make + " " + model + " " + colour + " " + registrationNumber;
	}

	// Getters and Setters

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	/**
	 * 
	 * @param registrationNumber
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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



	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public String getEngSerial() {
		return engSerial;
	}

	public void setEngSerial(String engSerial) {
		this.engSerial = engSerial;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getMotDate() {
		return motDate;
	}

	public void setMotDate(String motDate) {
		this.motDate = motDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}