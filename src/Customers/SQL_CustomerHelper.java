package Customers;

import Database.Database_Controller;
import Job.Job;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_CustomerHelper extends Database_Controller {

	public SQL_CustomerHelper() { this.conn = super.connectToDb();}

	/**
	 * 
	 * @param obj
	 * @param command
	 */
	public String generateQuery(Object obj, String command) {
		this.query = command;
		return this.query;
	}

	public Customer[] getCustomers() {
		Customer[] out = null;

		String sizequr = "SELECT COUNT(*) AS Count FROM Customers";
		String qur = "SELECT * FROM Customers";

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sizequr);

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new Customer[size];

			rs = st.executeQuery(qur);

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

	public boolean createCustomer(String fname, String lname, String address, String telephone, String email, String fax, int discount) {
		String qur = String.format("INSERT INTO Customers(firstName, lastName, address, telephone, email, fax, discount)" +
				" VALUES('%s','%s','%s','%s','%s','%s',%d)", fname, lname, address, telephone, email, fax,discount);
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(qur);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}