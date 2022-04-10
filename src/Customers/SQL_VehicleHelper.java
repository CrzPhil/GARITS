package Customers;

import Database.Database_Controller;
import Job.Job;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_VehicleHelper extends Database_Controller {

	public SQL_VehicleHelper() { this.conn = super.connectToDb();}

	/**
	 * 
	 * @param obj
	 * @param command
	 */
	public String generateQuery(Object obj, String command) {
		this.query = command;
		return this.query;
	}

	// Return vehicle(s) corresponding to customer
	public Vehicle[] getVehicles(long customerID) {
		Vehicle[] out = null;

		return out;
	}

	// Checks whether a Vehicle with a given reg No exists in the database
	public boolean vehicleExists(String regNo) {
		String qur = String.format("SELECT COUNT(*) AS Count FROM Vehicles WHERE registrationNo = '%s'", regNo);
		boolean exists = false;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(qur);
			rs.next();

			if (rs.getInt("Count") == 1) {
				exists = true;
			}

			rs.close();
			st.close();

			return exists;
		} catch (SQLException e) {
			e.printStackTrace();
			return exists;
		}
	}

	public boolean addVehicle(Vehicle vehicle) {
		String addVehicle = "INSERT INTO Vehicles (registrationNo, make, model, engSerial, chassisNo, colour, MoTDate, CustomercustomerID)";
		// Format date correctly (DD/MM/YYYY)
		String datePart = "STR_TO_DATE('" + vehicle.getMotDate() + "', \"%d/%m/%Y\")";
		String sendValues = String.format(" VALUES ('%s','%s','%s','%s','%s','%s', %s,%d)",
				vehicle.getRegistrationNumber(),
				vehicle.getMake(),
				vehicle.getModel(),
				vehicle.getEngSerial(),
				vehicle.getChassisNumber(),
				vehicle.getColour(),
				datePart,
				vehicle.getCustomer().getCustomerID());

		try {
			System.out.println(addVehicle + sendValues);
			Statement st = conn.createStatement();
			st.executeUpdate(addVehicle + sendValues);
			st.close();
			return true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return false;
		}
	}

}