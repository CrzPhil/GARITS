package Customers;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller for anything customer-related
 * Creating / Modifying / Deleting customers happens here.
 */
public class Customer_Controller implements I_Customers {

	private final SQL_VehicleHelper vehicleHelper = new SQL_VehicleHelper();
	private final SQL_CustomerHelper customerHelper = new SQL_CustomerHelper();

	/**
	 * Check whether a vehicle exists in DB by regNo
	 * @param regNo Check DB for this registration Number (Primary Key)
	 * @return whether it exists.
	 */
	public boolean vehicleExists(String regNo) {
		return vehicleHelper.vehicleExists(regNo);
	}

	/**
	 * Create a vehicle in DB by vehicle object
	 * @param vehicle Object to create in DB
	 * @return Whether create was successful
	 */
	public boolean createVehicle(Vehicle vehicle) {
		return vehicleHelper.createVehicle(vehicle);
	}

	/**
	 * Get All customers
	 * @return Array of customers
	 */
	public Customer[] getCustomers() {
		return customerHelper.getCustomers();
	}

	/**
	 * Pattern checker
	 * @param regex Regex to apply
	 * @param text Text to apply regex on
	 * @return Boolean: Whether match was found in text
	 */
	private boolean checkRegex(String regex, String text) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		return m.matches();
	}

	/**
	 * Email regex filter.
	 * @param email email to check.
	 * @return Boolean: whether a match was found in the text.
	 */
	public boolean validateEmail(String email) {
		String emailRegex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		return checkRegex(emailRegex, email);
	}

	/**
	 * Validate phone
	 * @param phone phone number to validate
	 * @return Boolean: whether match was found.
	 */
	public boolean validatePhone(String phone) {
		String phoneRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
		return checkRegex(phoneRegex, phone);
	}

	/**
	 * Match discount to valid number
	 * @param discount text to match
	 * @return boolean
	 */
	public boolean validateDiscount(String discount) {
		String rateRegex = "^\\d+$";
		return checkRegex(rateRegex, discount);
	}

	/**
	 * Validate user input using regex functions
	 * @param phone phone number
	 * @param email email
	 * @param discount discount
	 * @param fax fax number
	 * @return Boolean: whether info is valid
	 */
	public boolean digestInfo(String phone, String email, String discount, String fax) {

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

	/**
	 * Create a customer in the DB
	 * @param fname First Name
	 * @param lname Last Name
	 * @param address Address
	 * @param telephone Telephone
	 * @param email Email
	 * @param fax Fax
	 * @param discount Custom Discount
	 * @return Whether customer was created successfully
	 */
	public boolean createCustomer(String fname, String lname, String address, String telephone, String email, String fax, int discount) {
		return customerHelper.createCustomer(fname, lname, address, telephone, email, fax, discount);
	}

	/**
	 * Update Customer by customerID
	 * @param fname First Name
	 * @param lname Last Name
	 * @param address Address
	 * @param telephone Telephone
	 * @param email Email
	 * @param fax Fax
	 * @param discount Custom Discount
	 * @param customerID ID of customer to change in DB
	 * @return Whether change was successful
	 */
	public boolean updateCustomer(String fname, String lname, String address, String telephone, String email, String fax, int discount, long customerID) {
		return customerHelper.updateCustomer(fname, lname, address, telephone, email, fax, discount, customerID);
	}

	/**
	 * Delete customer by Object
	 * @param customer Customer Object
	 * @return Whether delete was successful
	 */
	public boolean deleteCustomer(Customer customer) {
		return customerHelper.deleteCustomer(customer.getCustomerID());
	}

	/**
	 * Get all vehicles assigned to a customer
	 * @param customer Customer Object
	 * @return Vehicle[] array of assigned vehicles
	 */
	public Vehicle[] getVehicles(Customer customer) {
		return vehicleHelper.getVehicles(customer);
	}

	/**
	 * Delete Vehicle by Registration Number
	 * @param regNo Registration Number
	 * @return Whether delete was successful
	 */
	public boolean deleteVehicle(String regNo) {
		return vehicleHelper.deleteVehicle(regNo);
	}

	/**
	 * Update Vehicle details
	 * @param oldRegNo Old Registration number to use in WHERE clause
	 * @param regNo New Registration number
	 * @param make New make
	 * @param model New model
	 * @param engSerial new eng serial
	 * @param chassisNo new chassis No
	 * @param colour new colour
	 * @param motDate new mot date
	 * @param customerID Customer's ID
	 * @return Whether update was successful
	 */
	public boolean updateVehicle(String oldRegNo, String regNo, String make, String model, String engSerial, String chassisNo, String colour, String motDate, long customerID) {
		return vehicleHelper.updateVehicle(oldRegNo, regNo, make, model, engSerial, chassisNo, colour, motDate, customerID);
	}
}