package Database;

import java.sql.*;

public class Database_Controller implements I_Database {

	protected String query;
	protected Connection conn;

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

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

	public Connection connectToDb() {
		try {
			// IP + Database name
			String url = "jdbc:mysql://176.58.124.119:3306/GARITS";

			// Username + Password
			conn = DriverManager.getConnection(url, "GARITS", "G@R!T$$$");

			return conn;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return null;
	}

	public void closeConnection() {
		try {
			this.conn.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
}