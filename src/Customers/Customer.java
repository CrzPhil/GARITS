package Customers;

import Accounts.*;

import java.util.HashMap;

public class Customer extends User {

	private long customerID;
	private String contactNumber;

	// Valued customers have Variable/Flexible discount plans
	private boolean valuedCustomer;

	private String discountPlan;
	static private final String[] plans = {"Fixed", "Variable", "Flexible"};

	// Collection of Customer's vehicles (regNo, Vehicle)
	private HashMap<String, Vehicle> vehicles;


	/**
	 * 
	 * @param contactNo
	 * @param valuedCustomer
	 */
	public Customer(long customerID, String contactNo, boolean valuedCustomer) {
		super();
		this.customerID = customerID;
		this.valuedCustomer = valuedCustomer;
	}


	public long getCustomerID() {
		return this.customerID;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	/**
	 * 
	 * @param contactNumber
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public boolean getValuedCustomer() {
		return this.valuedCustomer;
	}

	public HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * 
	 * @param valuedCustomer
	 */
	public void setValuedCustomer(boolean valuedCustomer) {
		this.valuedCustomer = valuedCustomer;
	}
}