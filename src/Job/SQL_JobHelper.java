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
		String sendJob = "INSERT INTO Jobs (jobType, duration, dates, parts, motNo, mileage, price, requiredParts, additionalInfo)";
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

	// Get all jobs from database
	public Job[] getJobs() {
		Job[] out = null;

		String sizequr = "SELECT COUNT(*) AS Count FROM Jobs";
		String qur = "SELECT * FROM Jobs";

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sizequr);

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			out = new Job[size];

			// Get Job
			rs = st.executeQuery(qur);

			int i = 0;

			while (rs.next()) {
				out[i] = new Job(
						rs.getInt("jobID"),
						rs.getString("jobType"),
						rs.getFloat("duration"),
						rs.getString("dates"),
						rs.getString("parts"),
						rs.getString("motNo"),
						rs.getInt("mileage"),
						rs.getFloat("price"),
						rs.getString("requiredParts"),
						rs.getString("additionalInfo")
				);
				i++;
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		this.closeConnection();
		return out;
	}
}