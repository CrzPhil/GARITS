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

	/**
	 * Method to create a backup of the current database state.
	 * For backing up & restoration we use mysqldump, which comes with MySQL.
	 * This has to be installed on the Admin's system in order to handle backups.
	 * @return If backup was successful
	 */
	public boolean backupDatabase() {
		// Current directory
		String cdir = System.getProperty("user.dir");
		String filename = java.time.LocalDate.now() + "-backup.sql";

		try {
			String[] cmd = {"cmd", "/c", "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe", "-uGARITS", "-pG@R!T$$$", "-h176.58.124.119", "GARITS", ">", cdir + "\\src\\Database\\Backups\\" + filename};
			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.redirectErrorStream(true);
			Process pr = pb.start();

			pr.waitFor();
			// 0 means success, anything else means it failed
			return pr.exitValue() == 0;

		} catch (Exception e) {
			// Linux/Mac specific; MySQL has to be installed.
			String[] cmd = {"/bin/sh", "-c", "mysqldump", "-uGARITS", "-pG@R!T$$$", "-h176.58.124.119", "GARITS", ">", cdir + "/src/Database/Backups/" + filename};

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

	/**
	 * Method to restore the database to a previous backup's state.
	 * For restoration we use mysql.
	 * This has to be installed on the Admin's system in order to handle backups.
	 * @return If rollback was successful
	 */
	public boolean restoreDatabase(String filePath) {
		try {
			String[] cmd = {"cmd", "/c", "\"C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe\"", "-uGARITS", "-pG@R!T$$$", "-h176.58.124.119", "GARITS", "<", filePath};

			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.redirectErrorStream(true);
			Process pr = pb.start();

			pr.waitFor();
			// 0 means success, anything else means it failed
			return pr.exitValue() == 0;

		} catch (Exception e) {
			// Linux/Mac specific; MySQL has to be installed.
			String[] cmd = {"/bin/sh", "-c", "mysqldump", "-uGARITS", "-pG@R!T$$$", "-h176.58.124.119", "GARITS", "<", filePath};
			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.redirectErrorStream(true);

			try {
				Process pr = pb.start();
				pr.waitFor();
				// 0 means success, anything else means it failed
				return pr.exitValue() == 0;
			} catch (IOException | InterruptedException ex) {
				ex.printStackTrace();
				return false;
			}
		}
	}
}
