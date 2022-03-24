package Database;

import Database.*;

import java.sql.*;

public class Database_Controller implements I_Database {

	private String query;
	private Connection conn;

	public String getQuery() {
		return this.query;
	}

	/**
	 * 
	 * @param query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * 
	 * @param obj
	 * @param command
	 */
	public String generateQuery(Object obj, String command) {
		// TODO - implement Database_Controller.generateQuery
		throw new UnsupportedOperationException();
	}

	public void read() {
		// TODO - implement Database_Controller.read
		throw new UnsupportedOperationException();
	}

	public boolean write() {
		// TODO - implement Database_Controller.write
		throw new UnsupportedOperationException();
	}

	public void connectToDb() {
		try {
			// IP + Database name
			String url = "jdbc:mysql://176.58.124.119:3306/GARITS";

			// Username + Password
			conn = DriverManager.getConnection(url, "GARITS", "G@R!T$$$");

			test();

			conn.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}


	private void test() {
		System.out.println("Test output:");
		String query = "SELECT customerID, firstName, lastName FROM Customer";

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int s = rs.getInt("customerID");
				String f = rs.getString("firstName");
				String l = rs.getString("lastName");
				System.out.println(s + " " + f + " " + l);
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
}