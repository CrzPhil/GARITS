package Customers;

import Database.Database_Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public Vehicle[] getVehicles(Customer customer) {
		Vehicle[] out = null;
		String sizequr = String.format("SELECT COUNT(*) AS Count FROM Vehicles WHERE CustomercustomerID = %d", customer.getCustomerID());
		String qur = String.format("SELECT * FROM Vehicles WHERE CustomercustomerID = %d", customer.getCustomerID());

		try {
			PreparedStatement pSt = conn.prepareStatement(sizequr);
			ResultSet rs = pSt.executeQuery();

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new Vehicle[size];

			rs = pSt.executeQuery(qur);

			int i = 0;

			while (rs.next()) {
				out[i] = new Vehicle(
						rs.getString("registrationNo"),
						rs.getString("make"),
						rs.getString("model"),
						rs.getString("engSerial"),
						rs.getString("chassisNo"),
						rs.getString("colour"),
						String.valueOf(rs.getDate("MoTDate")),
						customer);
				++i;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	// Checks whether a Vehicle with a given reg No exists in the database
	public boolean vehicleExists(String regNo) {
		String qur = String.format("SELECT COUNT(*) AS Count FROM Vehicles WHERE registrationNo = '%s'", regNo);
		boolean exists = false;
		try {
			PreparedStatement pSt = conn.prepareStatement(qur);
			ResultSet rs = pSt.executeQuery();
			rs.next();

			if (rs.getInt("Count") == 1) {
				exists = true;
			}

			rs.close();
			pSt.close();

			return exists;
		} catch (SQLException e) {
			e.printStackTrace();
			return exists;
		}
	}

	public boolean createVehicle(Vehicle vehicle) {
		// Format date correctly (DD/MM/YYYY)
		String datePart = "STR_TO_DATE('" + vehicle.getMotDate() + "', \"%d/%m/%Y\")";

		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("INSERT INTO Vehicles (registrationNo, make, model, engSerial, chassisNo, colour, MoTDate, CustomercustomerID)" + " VALUES (?,?,?, ?, ?, ?, "+ datePart+",?)");
			pSt.setString(1, vehicle.getRegistrationNumber());
			pSt.setString(2, vehicle.getMake());
			pSt.setString(3, vehicle.getModel());
			pSt.setString(4, vehicle.getEngSerial());
			pSt.setString(5, vehicle.getChassisNumber());
			pSt.setString(6, vehicle.getColour());
			pSt.setLong(7, vehicle.getCustomer().getCustomerID());
			pSt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return false;
		}
	}

	public boolean deleteVehicle(String reNo) {
		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("DELETE FROM Vehicles WHERE registrationNo = ?");
			pSt.setString(1, reNo);
			pSt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateVehicle(String regNo, String make, String model, String engSerial, String chassisNo, String colour, String motDate, long customerID) {
		String datePart = "STR_TO_DATE(\"" + motDate + "\", \"%Y-%m-%d\")";
		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("UPDATE Vehicles SET registrationNo = ?, make = ?, model = ?, engSerial = ?, chassisNo = ?, colour = ?, MoTDate = " + datePart + " WHERE CustomercustomerID = ?");
			pSt.setString(1, regNo);
			pSt.setString(2, make);
			pSt.setString(3, model);
			pSt.setString(4, engSerial);
			pSt.setString(5, chassisNo);
			pSt.setString(6, colour);
			pSt.setLong(7, customerID);
			System.out.println(pSt);

			pSt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}