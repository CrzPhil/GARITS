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

	public Job[] getJobs() {
		Job[] out = null;

		String sizequr = "SELECT COUNT(DISTINCT vehicleType) AS Count FROM SpareParts";
		String qur = String.format("SELECT DISTINCT * FROM Jobs");

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(qur);

			// Get count for returned rows
			rs.next();
			int size = rs.getInt("Count");
			rs.close();

			// We add one entry for the default Select value in the GUI, in case the user wants to search
			out = new Job[size];

			// Get spare parts
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