package Customers;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer_Controller implements I_Customers {

	private final SQL_VehicleHelper vehicleHelper = new SQL_VehicleHelper();
	private final SQL_CustomerHelper customerHelper = new SQL_CustomerHelper();

	/**
	 * 
	 * @param customerID
	 * @param contactNumber
	 * @param regularCustomer
	 * @param valuedCustomer
	 */
	public Customers.Customer createCustomer(long customerID, String contactNumber, boolean regularCustomer, boolean valuedCustomer) {
		// TODO - implement Customer_Controller.createCustomer
		throw new UnsupportedOperationException();
	}

	public Customers.Customer getCustomer() {
		// TODO - implement Customer_Controller.getCustomer
		throw new UnsupportedOperationException();
	}

	@Override
	public Vehicle createVehicle() {
		return null;
	}

	public boolean vehicleExists(String regNo) {
		return vehicleHelper.vehicleExists(regNo);
	}

	public boolean createVehicle(Vehicle vehicle) {
		return vehicleHelper.createVehicle(vehicle);
	}

	public Customer[] getCustomers() {
		return customerHelper.getCustomers();
	}


	public Customers.Vehicle getVehicle() {
		// TODO - implement Customer_Controller.getVehicle
		throw new UnsupportedOperationException();
	}

	public Customers.MOT_Reminder createReminder() {
		// TODO - implement Customer_Controller.createReminder
		throw new UnsupportedOperationException();
	}

	public Customers.MOT_Reminder getReminder() {
		// TODO - implement Customer_Controller.getReminder
		throw new UnsupportedOperationException();
	}


	// Pattern checker
	private boolean checkRegex(String regex, String text) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		return m.matches();
	}

	// Generic email filter
	public boolean validateEmail(String email) {
		String emailRegex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		return checkRegex(emailRegex, email);
	}

	// Phone filter
	public boolean validatePhone(String phone) {
		String phoneRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
		return checkRegex(phoneRegex, phone);
	}

	// Simple discount matcher
	public boolean validateDiscount(String discount) {
		String rateRegex = "^\\d+$";
		return checkRegex(rateRegex, discount);
	}

	// Method to validate user input
	public boolean digestInfo(String phone, String email, String discount, String fax) {
		if (!validateEmail(email)) {
			JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
			return false;
		}

		if (!validatePhone(phone)) {
			JOptionPane.showMessageDialog(null, "Please enter a valid phone number.");
			return false;
		}

		if (!validateDiscount(discount)) {
			JOptionPane.showMessageDialog(null, "Please enter a valid discount integer.");
			return false;
		}

		return true;
	}

	public boolean createCustomer(String fname, String lname, String address, String telephone, String email, String fax, int discount) {
		return customerHelper.createCustomer(fname, lname, address, telephone, email, fax, discount);
	}

	public boolean updateCustomer(String fname, String lname, String address, String telephone, String email, String fax, int discount, long customerID) {
		return customerHelper.updateCustomer(fname, lname, address, telephone, email, fax, discount, customerID);
	}

	public boolean deleteCustomer(Customer customer) {
		return customerHelper.deleteCustomer(customer.getCustomerID());
	}

	public Vehicle[] getVehicles(Customer customer) {
		return vehicleHelper.getVehicles(customer);
	}

	public boolean deleteVehicle(String regNo) {
		return vehicleHelper.deleteVehicle(regNo);
	}

	public boolean updateVehicle(String oldRegNo, String regNo, String make, String model, String engSerial, String chassisNo, String colour, String motDate, long customerID) {
		return vehicleHelper.updateVehicle(oldRegNo, regNo, make, model, engSerial, chassisNo, colour, motDate, customerID);
	}
}