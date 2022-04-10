package Customers;

import Accounts.*;

import java.util.HashMap;

public class Customer {

	private long customerID;
	private String contactNumber;
	private String name;
	private String address;
	private String telephone;
	private String email;
	private String fax;
	private int discount;
	static public final String[] plans = {"Fixed", "Variable", "Flexible"};
	// Collection of Customer's vehicles (regNo, Vehicle)
	private HashMap<String, Vehicle> vehicles;
	// Valued customers have Variable/Flexible discount plans
	private boolean valuedCustomer;
	private String discountPlan;

	public Customer(long customerID, String firstName, String lastName, String address, String telephone, String email, String fax, int discount, boolean valuedCustomer, String discountPlan) {
		this.customerID = customerID;
		this.name = firstName + " " + lastName;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.fax = fax;
		this.discount = discount;
		this.valuedCustomer = valuedCustomer;
		this.discountPlan = discountPlan;
	}

	@Override
	public String toString() {
		return this.name;
	}

	// Getters and Setters

	public long getCustomerID() {
		return customerID;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(HashMap<String, Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public boolean isValuedCustomer() {
		return valuedCustomer;
	}

	public void setValuedCustomer(boolean valuedCustomer) {
		this.valuedCustomer = valuedCustomer;
	}

	public String getDiscountPlan() {
		return discountPlan;
	}

	public void setDiscountPlan(String discountPlan) {
		this.discountPlan = discountPlan;
	}
}