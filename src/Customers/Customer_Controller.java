package Customers;

import Customers.*;
import Job.SQL_JobHelper;

public class Customer_Controller implements I_Customers {

	private final SQL_VehicleHelper helper = new SQL_VehicleHelper();
	private final SQL_JobHelper jobHelper = new SQL_JobHelper();

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
		return helper.vehicleExists(regNo);
	}

	public boolean createVehicle(Vehicle vehicle) {
		return helper.addVehicle(vehicle);
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

}