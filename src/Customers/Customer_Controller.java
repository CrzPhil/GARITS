package Customers;

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
		return vehicleHelper.addVehicle(vehicle);
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

	public boolean createCustomer(String fname, String lname, String address, String telephone, String email, String fax, int discount) {
		return customerHelper.createCustomer(fname, lname, address, telephone, email, fax, discount);
	}
}