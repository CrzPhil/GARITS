package Database;

import java.io.IOException;
import java.sql.*;

public class Database_Controller implements I_Database {

	protected String query;
	protected static Connection conn;

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
			conn.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	// Method to create a backup of the current database state
	public boolean backupDatabase() {
		// Current directory
		String cdir = System.getProperty("user.dir");
		String filename = java.time.LocalDate.now() + "-backup.sql";

		try {
			// TODO: Windows/Version-specific
			String cmd = "C:/Program Files/MySQL/MySQL Server 8.0/mysqldump.exe -uGARITS -pG@R!T$$$ -h176.58.124.119 GARITS > " +
					cdir + "/src/Database/Backups/" + filename;
			Runtime rt = Runtime.getRuntime();
			rt.exec(cmd);
			return true;
		} catch (Exception e) {
			// Linux/Mac specific; MySQL has to be installed.
			String cmd = "mysqldump -uGARITS -pG@R!T$$$ -h176.58.124.119 GARITS > " +
					cdir + "/src/Database/Backups/" + filename;
			Runtime rt = Runtime.getRuntime();
			try {
				rt.exec(cmd);
				return true;
			} catch (IOException ex) {
				ex.printStackTrace();
				return false;
			}
		}
	}

	// Restore/Rollback database to file
	public boolean restoreDatabase(String filePath) {
		// Current directory
		String cdir = System.getProperty("user.dir");

		try {
			// TODO: Windows/Version-specific
			String cmd = "C:/Program Files/MySQL/MySQL Server 8.0/mysqldump.exe -uGARITS -pG@R!T$$$ -h176.58.124.119 GARITS < " + filePath;
			Runtime rt = Runtime.getRuntime();
			rt.exec(cmd);
			return true;
		} catch (Exception e) {
			// Linux/Mac specific; MySQL has to be installed.
			String cmd = "mysqldump -uGARITS -pG@R!T$$$ -h176.58.124.119 GARITS < " + filePath;
			Runtime rt = Runtime.getRuntime();
			try {
				rt.exec(cmd);
				return true;
			} catch (IOException ex) {
				ex.printStackTrace();
				return false;
			}
		}
	}
}