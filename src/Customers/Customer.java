package Customers;

import Accounts.*;

public class Customer extends User {

	private long customerID;
	private String contactNumber;
	private boolean regularCustomer;
	private boolean valuedCustomer;

	/**
	 * 
	 * @param ID
	 * @param contactNo
	 * @param regular
	 * @param valued
	 */
	public Customer(long ID, String contactNo, boolean regular, boolean valued) {
		// TODO - implement Customer.Customer
		throw new UnsupportedOperationException();
	}

	public double getDiscountPlan() {
		// TODO - implement Customer.getDiscountPlan
		throw new UnsupportedOperationException();
	}

	public long getCustomerID() {
		return this.customerID;
	}

	/**
	 * 
	 * @param customerID
	 */
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
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

	public boolean getRegularCustomer() {
		return this.regularCustomer;
	}

	/**
	 * 
	 * @param regularCustomer
	 */
	public void setRegularCustomer(boolean regularCustomer) {
		this.regularCustomer = regularCustomer;
	}

	public boolean getValuedCustomer() {
		return this.valuedCustomer;
	}

	/**
	 * 
	 * @param valuedCustomer
	 */
	public void setValuedCustomer(boolean valuedCustomer) {
		this.valuedCustomer = valuedCustomer;
	}

}