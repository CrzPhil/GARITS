package Accounts;

import Database.Database_Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_UserHelper extends Database_Controller {

	public SQL_UserHelper() {
		this.conn = super.connectToDb();
	}

	/**
	 * 
	 * @param obj
	 * @param command
	 */
	public String generateQuery(Object obj, String command) {
		this.query = command;
		return this.query;
	}

	public void getUser(String fname, String lname) {
		System.out.println("Querying user " + fname + " " + lname + " ... ");
		String qur = "SELECT * FROM Staff WHERE firstName LIKE \'" + fname + "\' OR lastName LIKE \'" + lname + "\'";

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(qur);
			while (rs.next()) {
				int s = rs.getInt("staffID");
				String f = rs.getString("firstName");
				String l = rs.getString("lastName");
				String r = rs.getString("role");
				String u = rs.getString("username");
				String p = rs.getString("password");
				int h = rs.getInt("hourlyRate");
				System.out.println("");
				System.out.println(f + " " + l + " " + r + " " + u + " " + p + " " + h);
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

}