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

	public Job[] sendData(String jobType, float duration, String dates, String parts, String motNO, int mileage, float price, String requiredParts, String additionalInfo){
		String sendJob = String.format("INSERT INTO Jobs (jobType, duration, dates, parts, motNo, mileage, price, requiredParts, additionalInfo)");
		String sendValues = String.format(" VALUES ('%s', '%f', '%s', '%s', '%s', '%d', '%f', '%s', '%s')", jobType, duration, dates, parts, motNO, mileage, price, requiredParts, additionalInfo);
		Job[] out = null;

		try {
			Statement st = conn.createStatement();
			System.out.println("cas");
			st.executeUpdate(sendJob + sendValues);

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return out;
	}


}