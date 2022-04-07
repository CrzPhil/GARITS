package Job;

import Database.Database_Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_JobHelper extends Database_Controller {

	public SQL_JobHelper() {
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

	public static Job[] sendData(String jobType, double duration, String dates, String parts, String motNO, double mileage, double price){
		String sendJob = String.format("INSERT INTO Jobs (jobType, duration, dates, parts, motNo, mileage, price)");
		String sendValues = String.format("VALUES ,'%s', '%f', '%s', '%s', '%s', '%d', '%f'");
		Job[] out = null;

		try {
			Statement st = conn.createStatement();


		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return out;
	}


}