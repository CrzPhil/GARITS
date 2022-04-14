package Customers;

import Database.Database_Controller;
import Job.Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_CustomerHelper extends Database_Controller {

	public SQL_CustomerHelper() {
		this.conn = super.connectToDb();
	}

	/**
	 * Get all customers
	 * @return Customer[] all customers
	 */
	public Customer[] getCustomers() {
		Customer[] out = null;

		String sizequr = "SELECT COUNT(*) AS Count FROM Customers";
		String qur = "SELECT * FROM Customers";

		try {
			PreparedStatement pSt = conn.prepareStatement(sizequr);
			ResultSet rs = pSt.executeQuery();

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new Customer[size];

			rs = pSt.executeQuery(qur);

			int i = 0;

			while (rs.next()) {
				out[i] = new Customer(
						rs.getInt("customerID"),
						rs.getString("firstName"),
						rs.getString("lastName"),
						rs.getString("address"),
						rs.getString("telephone"),
						rs.getString("email"),
						rs.getString("fax"),
						rs.getInt("discount"),
						false,
						Customer.plans[0]
				);
				++i;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	/**
	 * Create Customer
	 * @param fname fname
	 * @param lname lname
	 * @param address address
	 * @param telephone telephone
	 * @param email email
	 * @param fax fax
	 * @param discount discount
	 * @return Whether creation was successful
	 */
	public boolean createCustomer(String fname, String lname, String address, String telephone, String email, String fax, int discount) {
		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("INSERT INTO Customers(firstName, lastName, address, telephone, email, fax, discount)" + " VALUES(?, ?, ?, ?, ?, ?, ?)");
			pSt.setString(1, fname);
			pSt.setString(2, lname);
			pSt.setString(3, address);
			pSt.setString(4, telephone);
			pSt.setString(5, email);
			pSt.setString(6, fax);
			pSt.setInt(7, discount);
			pSt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Delete customer by ID
	 * @param customerID staffID
	 * @return Whether deletion was successful
	 */
	public boolean deleteCustomer(long customerID) {

		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("DELETE FROM Customers WHERE customerID = ?");
			pSt.setLong(1, customerID);
			pSt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Update values for a customer (by customerID)
	 * @param fname fname
	 * @param lname lname
	 * @param address address
	 * @param telephone telephone
	 * @param email email
	 * @param fax fax
	 * @param discount discount
	 * @param customerID customer ID to change
	 * @return whether update was successful
	 */
	public boolean updateCustomer(String fname, String lname, String address, String telephone, String email, String fax, int discount, long customerID) {
		try {
			//SQL sanitization to prevent SQL injection attacks
			PreparedStatement pSt;
			pSt = conn.prepareStatement("UPDATE Customers SET firstName = ?, lastName = ?, address = ?, telephone = ?, email = ?, fax = ?, discount = ? WHERE customerID = ?");
			pSt.setString(1, fname);
			pSt.setString(2, lname);
			pSt.setString(3, address);
			pSt.setString(4, telephone);
			pSt.setString(5, email);
			pSt.setString(6, fax);
			pSt.setInt(7, discount);
			pSt.setLong(8, customerID);
			pSt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}