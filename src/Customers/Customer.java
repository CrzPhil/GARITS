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
	 * @param regularCustomer
	 * @param valuedCustomer
	 */
	public Customer(long ID, String contactNo, boolean regularCustomer, boolean valuedCustomer) {
		super();
		regularCustomer = false;
		valuedCustomer = false;
	}

	public double getDiscountPlan() {
		//end price can be multiplied bt the values below for discounts depending on customer status.
		if (regularCustomer == true && valuedCustomer == true){
			return 0.8;
		}else if (regularCustomer == true){
			return 0.95;
		}else if (valuedCustomer == true){
			return 0.9;
		}else{
			return 1;
		}
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